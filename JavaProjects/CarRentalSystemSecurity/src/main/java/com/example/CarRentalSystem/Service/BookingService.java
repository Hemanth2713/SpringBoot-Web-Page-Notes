package com.example.CarRentalSystem.Service;

import com.example.CarRentalSystem.Model.Booking;
import com.example.CarRentalSystem.Model.Car;
import com.example.CarRentalSystem.Model.Payment;
import com.example.CarRentalSystem.Repository.BookingRepository;
import com.example.CarRentalSystem.Repository.CarRepository;
import com.example.CarRentalSystem.Repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public Booking createBooking(Booking booking) {
        logger.info("Creating booking with startDate: {}, endDate: {}, carId: {}",
                booking.getStartDate(), booking.getEndDate(),
                booking.getCar() != null ? booking.getCar().getCarId() : null);

        // Validate car and carId
        if (booking.getCar() == null || booking.getCar().getCarId() == null) {
            logger.error("Car or carId is null");
            throw new IllegalArgumentException("Car cannot be null for booking.");
        }

        // Fetch car from repository to ensure all fields are populated
        Optional<Car> carOptional = carRepository.findById(booking.getCar().getCarId());
        if (!carOptional.isPresent()) {
            logger.error("Car with ID {} not found", booking.getCar().getCarId());
            throw new IllegalArgumentException("Car with ID " + booking.getCar().getCarId() + " not found.");
        }

        Car car = carOptional.get();
        booking.setCar(car);

        // Validate pricePerDay
        if (car.getPricePerDay() <= 0.0) {
            logger.error("Invalid pricePerDay: {} for carId: {}", car.getPricePerDay(), car.getCarId());
            throw new IllegalArgumentException("Car price per day must be greater than zero.");
        }

        // Validate dates
        if (booking.getStartDate() == null || booking.getEndDate() == null) {
            logger.error("Start date or end date is null");
            throw new IllegalArgumentException("Start date and end date cannot be null.");
        }

        // Calculate the number of days (inclusive of start date, exclusive of end date)
        long durationMillis = booking.getEndDate().getTime() - booking.getStartDate().getTime();
        long durationDays = TimeUnit.DAYS.convert(durationMillis, TimeUnit.MILLISECONDS);

        // Ensure at least 1-day charge and handle same-day bookings
        durationDays = Math.max(durationDays, 1);

        logger.info("Calculated duration: {} days for startDate: {}, endDate: {}",
                durationDays, booking.getStartDate(), booking.getEndDate());

        // Calculate total amount
        double totalAmount = car.getPricePerDay() * durationDays;
        booking.setTotalAmount(totalAmount);

        logger.info("Calculated totalAmount: {} (pricePerDay: {} * duration: {})",
                totalAmount, car.getPricePerDay(), durationDays);

        // Set initial booking status to Pending
        booking.setBookingStatus(Booking.BookingStatus.PENDING);

        // Save booking
        Booking savedBooking = bookingRepository.save(booking);
        logger.info("Saved booking with bookingId: {}, totalAmount: {}",
                savedBooking.getBookingId(), savedBooking.getTotalAmount());

        // Create Payment for the booking
        Payment payment = new Payment();
        payment.setBooking(savedBooking);
        payment.setAmountPaid(0.0); // Initialize to 0, not totalAmount
        payment.setPaymentMethod(Payment.PaymentMethod.None);
        payment.setPaymentStatus(Payment.PaymentStatus.Pending);
        paymentRepository.save(payment);

        logger.info("Created payment with paymentId: {}, amountPaid: 0.0", payment.getPaymentId());

        // Link Payment to Booking
        savedBooking.setPayment(payment);
        Booking finalBooking = bookingRepository.save(savedBooking);

        logger.info("Final booking saved with bookingId: {}, totalAmount: {}",
                finalBooking.getBookingId(), finalBooking.getTotalAmount());

        return finalBooking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        return booking.orElse(null);
    }

    @Transactional
    public Booking updateBookingById(Long bookingId, Booking bookingDetails) {
        logger.info("Updating booking with bookingId: {}", bookingId);

        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setUser(bookingDetails.getUser());
            if (bookingDetails.getCar() != null && bookingDetails.getCar().getCarId() != null) {
                Optional<Car> carOptional = carRepository.findById(bookingDetails.getCar().getCarId());
                if (!carOptional.isPresent()) {
                    logger.error("Car with ID {} not found", bookingDetails.getCar().getCarId());
                    throw new IllegalArgumentException("Car with ID " + bookingDetails.getCar().getCarId() + " not found.");
                }
                booking.setCar(carOptional.get());
            }
            booking.setStartDate(bookingDetails.getStartDate());
            booking.setEndDate(bookingDetails.getEndDate());
            booking.setBookingStatus(bookingDetails.getBookingStatus());

            // Recalculate total amount based on updated dates and car
            if (booking.getCar() != null && booking.getStartDate() != null && booking.getEndDate() != null) {
                if (booking.getCar().getPricePerDay() <= 0.0) {
                    logger.error("Invalid pricePerDay: {} for carId: {}",
                            booking.getCar().getPricePerDay(), booking.getCar().getCarId());
                    throw new IllegalArgumentException("Car price per day must be greater than zero.");
                }
                long durationMillis = booking.getEndDate().getTime() - booking.getStartDate().getTime();
                long durationDays = TimeUnit.DAYS.convert(durationMillis, TimeUnit.MILLISECONDS);
                durationDays = Math.max(durationDays, 1);

                double totalAmount = booking.getCar().getPricePerDay() * durationDays;
                booking.setTotalAmount(totalAmount);

                logger.info("Updated totalAmount: {} (pricePerDay: {} * duration: {})",
                        totalAmount, booking.getCar().getPricePerDay(), durationDays);
            }

            Booking updatedBooking = bookingRepository.save(booking);
            logger.info("Updated booking with bookingId: {}, totalAmount: {}",
                    updatedBooking.getBookingId(), updatedBooking.getTotalAmount());
            return updatedBooking;
        }
        return null;
    }

    @Transactional
    public boolean deleteById(Long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
            logger.info("Deleted booking with bookingId: {}", bookingId);
            return true;
        }
        logger.warn("Booking with bookingId: {} not found for deletion", bookingId);
        return false;
    }
}
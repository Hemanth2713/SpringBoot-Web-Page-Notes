package PrepInsta;

public class PrimeNumberOrNot {
    public static void main(String[] args) {
        checkPrimeUsingIteration(23);
        checkPrimeUsingBreakCondition(17);
        checkPrimeUsingHalfRange(33);
        checkPrimeUsingSquareRoot(29);
        checkPrimeUsingOddIterations(29);
        checkPrimeUsingRecursion(37, 2);
    }

    // Method 1: Simple iterative solution
    static void checkPrimeUsingIteration(int num) {
        // Negative numbers, 0 and 1 are not prime
        if (num < 2) {
            System.out.println("The given number " + num + " is not prime");
            return;
        }
        int count = 0;
        // Checking the number of divisors between [1, n]
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                count++;
            }
        }
        // If count of divisors is greater than 2, then it's not prime
        System.out.println(count > 2 ? num + " is Not a Prime Number" : num + " is a Prime Number");
    }

    // Method 2: Optimization by break condition
    static void checkPrimeUsingBreakCondition(int num) {
        boolean isPrime = true;
        // 0, 1, and negative numbers are not prime
        if (num < 2) {
            isPrime = false;
        } else {
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        System.out.println(isPrime ? num + " is a Prime Number" : num + " is Not a Prime Number");

        // Time Complexity: O(N)
        // Space Complexity: O(1)
        // This program is better than the previous version because:
        // - Condition for 0, 1, and negative numbers is checked earlier
        // - Loop runs between [2, n-1] rather than [1, n]
    }

    // Method 3: Optimization by n/2 iterations
    static void checkPrimeUsingHalfRange(int num) {
        boolean isPrime = true;

        // 0, 1, and negative numbers are not prime
        if (num < 2) {
            isPrime = false;
        } else {
            // Running loop till n is wasteful because for any number n, the numbers
            // in the range (n/2 + 1, n) won't be divisible anyways.
            for (int i = 2; i <= num / 2; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        String result = isPrime ? "Prime" : "Not Prime";
        System.out.println("The number " + num + " is : " + result);

        // Time Complexity: O(N)
        // Space Complexity: O(1)
        // This program is better than the previous version as:
        // - Loops runs between [2, n/2] rather than [2, n-1]
    }

    // Method 4: Optimization by √n
    static void checkPrimeUsingSquareRoot(int num) {
        boolean isPrime = true;

        // 0, 1, and negative numbers are not prime
        if (num < 2) {
            isPrime = false;
        } else {
            // If a number n is not a prime, it can be factored into two factors a and b:
            // n = a * b

            /* Now, a and b can't be both greater than the square root of n,
               since then the product a * b would be greater than sqrt(n) * sqrt(n) = n.
               So in any factorization of n, at least one of the factors must be smaller
               than the square root of n, and if we can't find any factors less than or equal to
               the square root, n must be a prime. */
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        String result = isPrime ? "Prime" : "Not Prime";
        System.out.println("The number " + num + " is : " + result);

        // Time Complexity: O(N)
        // Space Complexity: O(1)
        // This program is better than the previous version as:
        // - Loop runs between [2, √n] rather than [2, n/2]
    }

    // Method 5: Optimization by skipping even iteration
    static void checkPrimeUsingOddIterations(int num) {
        if (num <= 1) {
            System.out.println(num + " is Not a Prime Number");
            return;
        }

        // Special case as 2 is the only even number that is prime
        if (num == 2) {
            System.out.println(num + " is a Prime Number");
            return;
        }

        // Check if n is a multiple of 2, thus all these won't be prime
        if (num % 2 == 0) {
            System.out.println(num + " is Not a Prime Number");
            return;
        }

        // If not, then just check the odd numbers up to √n
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                System.out.println(num + " is Not a Prime Number");
                return;
            }
        }

        System.out.println(num + " is a Prime Number");
    }

    // Method 6: Basic Recursion technique
    static boolean checkPrimeUsingRecursion(int num, int i) {
        // 0, 1, and negative numbers are not prime
        if (num < 2) {
            System.out.println(num + " is Not a Prime Number");
            return false;
        }

        // If this satisfies then it's prime as we have completed recursion from 2 to n
        if (i == num) {
            System.out.println(num + " is a Prime Number");
            return true;
        }

        // Base cases
        if (num % i == 0) {
            System.out.println(num + " is Not a Prime Number");
            return false;
        }

        i += 1;
        return checkPrimeUsingRecursion(num, i);
    }
}

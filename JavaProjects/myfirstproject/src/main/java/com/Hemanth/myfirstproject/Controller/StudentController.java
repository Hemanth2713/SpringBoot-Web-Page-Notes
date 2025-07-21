package com.Hemanth.myfirstproject.Controller;

import com.Hemanth.myfirstproject.Entity.StudentEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Indicates that this class is a REST controller, which means it will handle HTTP requests and responses in a RESTful way.
@RequestMapping("/studentDetails")
// The @RequestMapping annotation is used to map HTTP requests to handler methods of MVC and REST controllers.
// In this case, it sets a base path for the controller: localhost:8080/studentDetails.

// GET, POST, PUT, DELETE Mappings explained below:

// @GetMapping is used to handle HTTP GET requests, typically used for fetching data from the server.
// For example, the method annotated with @GetMapping("/studentData") will respond to GET requests at:
// localhost:8080/studentDetails/studentData. It will return a list of all student entries in this case.

// @PostMapping is used to handle HTTP POST requests, typically used for creating new resources on the server.
// The method annotated with @PostMapping will respond to POST requests at:
// localhost:8080/studentDetails, allowing the creation of new student entries by sending data in the request body.

// @PutMapping is used to handle HTTP PUT requests, typically used for updating an existing resource on the server.
// The method annotated with @PutMapping("/{id}") will respond to PUT requests at:
// localhost:8080/studentDetails/{id}, where {id} is the ID of the student to be updated.
// This will update the student entry with the provided data in the request body.

// @DeleteMapping is used to handle HTTP DELETE requests, typically used for deleting a resource on the server.
// The method annotated with @DeleteMapping("/{id}") will respond to DELETE requests at:
// localhost:8080/studentDetails/{id}, where {id} is the ID of the student to be deleted.
// It will remove the student entry from the system based on the provided ID.

public class StudentController {

    private Map<Long, StudentEntry> studentEntries = new HashMap<>();

    // Methods inside a Controller class should be public so that they can be accessed and invoked by the Spring Framework or external HTTP requests.

    @GetMapping("/studentData")
    // The @GetMapping annotation maps HTTP GET requests onto specific handler methods.
    // This method will handle requests to: localhost:8080/studentdetails/studentdata and return a list of all student entries.
    public List<StudentEntry> getAll() {
        return new ArrayList<>(studentEntries.values()); // Return all student entries as a list.
    }

    @PostMapping
    // The @PostMapping annotation maps HTTP POST requests onto specific handler methods.
    // This method will handle POST requests to localhost:8080/studentdetails.
    // In Postman, we select POST method and provide data in the body of the request (in JSON format).
    public boolean createEntry(@RequestBody StudentEntry student) {
        // @RequestBody tells Spring to bind the incoming HTTP request body (in JSON format) to a Java object.
        // In this case, we expect the incoming data to be a JSON representation of a StudentEntry object.
        studentEntries.put(student.getStudID(), student); // Save the student entry using their student ID as the key.
        return true; // Return true indicating that the entry has been created successfully.
    }

    // Example: If we use @PostMapping or any other HTTP method annotations like @GetMapping twice without a specific endpoint,
    // Spring will not know which method to call as both methods would handle the same request path.

    // Additional HTTP Methods (PUT, DELETE):

    @PutMapping("/{id}")
    // The @PutMapping annotation maps HTTP PUT requests onto a specific handler method.
    // This method will handle PUT requests to: localhost:8080/studentdetails/{id}, where {id} is the student's ID.
    // It's typically used for updating an existing resource. Here we would update a student's entry based on their ID.
    public boolean updateEntry(@PathVariable Long id, @RequestBody StudentEntry student) {
        if (studentEntries.containsKey(id)) {
            studentEntries.put(id, student); // Update the existing student entry with the new data.
            return true; // Return true indicating that the entry has been updated.
        }
        return false; // Return false if the student entry doesn't exist.
    }

    @DeleteMapping("/{id}")
    // The @DeleteMapping annotation maps HTTP DELETE requests onto a specific handler method.
    // This method will handle DELETE requests to: localhost:8080/studentdetails/{id}, where {id} is the student's ID.
    // It's typically used for deleting an existing resource.
    public boolean deleteEntry(@PathVariable Long id) {
        if (studentEntries.containsKey(id)) {
            studentEntries.remove(id); // Remove the student entry with the given ID.
            return true; // Return true indicating that the entry has been deleted.
        }
        return false; // Return false if the student entry doesn't exist.
    }

}

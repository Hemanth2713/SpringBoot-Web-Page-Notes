package com.restPractice.jobs;

import com.restPractice.jobs.model.JobPostSecurity;
import com.restPractice.jobs.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {

    //Problem is every time you run this application, we will get a new password.
    //What if we can have our own username and password and how do we configure that?

    //We have to go to our application.properties file and we need to specify the username and password property
    //spring.security.user.name=hemanth
    //spring.security.user.password=1234
    @Autowired
    private JobService service;

    @GetMapping("hello")
    //Spring framework behind the scene work with servlets, the spring web which we are using
    public String greet(HttpServletRequest request){//Here we are using HttpServletRequest because it has a method called session.
        return "Hello World "+request.getSession().getId();
    }

    @GetMapping("jobPosts")

    public  List<JobPostSecurity> getAllJobs(){
        return service.getAllJobPosts();
    }

    @GetMapping("jobPost/{postId}")
    //Path variable is used to accept a value from user he wants to enter i.e user wants a particular data

    //@GetMapping(path="jobPosts",produces={"application/json"})
    //Now doing this, what we are saying is , this particular method will only return the JSON, not XML.

    public JobPostSecurity getJob(@PathVariable("postId") int postid){
        return service.getJob(postid);

    }

    //I want search something.We are getting a value based on the postId. But I want to go with a text now.
    //Basically  I want to pass a keyword and it should search in that particular data.
    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPostSecurity> searchByKeyword(@PathVariable("keyword") String keyword){
       return service.search(keyword);
    }

    //@ResponseBody --> When you want to return data we use response body

    @PostMapping("jobPost")
    //When you want to send data to the server or as a server if you want to accept data, you have to use request body.
    public JobPostSecurity addJob(@RequestBody JobPostSecurity jobPostSecurity){//If we use void means it will just add the Job details.
         service.addJob(jobPostSecurity);
        // return jobPost; //This is not a good way of returning the data
         //If we use JobPost object in method and in return added job post details it will print data after adding it
        return service.getJob(jobPostSecurity.getPostId());//Here we are mentioning the job id which we are entering.
        //This will make sure that you have already stored the data somewhere
    }

    @PutMapping("jobPost")
    public JobPostSecurity updateJob(@RequestBody JobPostSecurity jobPostSecurity){
        service.updateJob(jobPostSecurity);
        return service.getJob(jobPostSecurity.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable int postId){
        service.deleteJob(postId);
        return "Deleted";

    }

    //
    @GetMapping("load")
    public String loadData(){
        service.load();
        return  "Success";
    }
}

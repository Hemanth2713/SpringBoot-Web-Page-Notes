package com.restPractice.jobs;

import com.restPractice.jobs.model.JobPost;
import com.restPractice.jobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {
    @Autowired
    private JobService service;

//    @GetMapping("/jobPosts")
//
//    public  List<JobPost> getAllJobs(){
//
//        return service.getAllJobPosts();
//    }
    @GetMapping("jobPosts")
    public List<JobPost> getAllJobs(){
        return service.getAllJobPosts();
    }


    @GetMapping("jobPost/{postId}")
    //Path variable is used to accept a value from user he wants to enter i.e user wants a particular data

    //@GetMapping(path="jobPosts",produces={"application/json"})
    //Now doing this, what we are saying is , this particular method will only return the JSON, not XML.

    public JobPost getJob(@PathVariable("postId") int postid){
        return service.getJob(postid);

    }

    //I want search something.We are getting a value based on the postId. But I want to go with a text now.
    //Basically  I want to pass a keyword and it should search in that particular data.
    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
       return service.search(keyword);
    }

//    @GetMapping("/jobPosts/{keyword1}/{keyword2}")
//    public List<JobPost> searchByMultipleKeywords(
//            @PathVariable("keyword1") String keyword1,
//            @PathVariable("keyword2") String keyword2) {
//
//        // Combine the two keywords and search using both in the service
//        return service.searchMultipleKeywords(keyword1, keyword2);
//    }
    //@ResponseBody --> When you want to return data we use response body

    @PostMapping("jobPost")
    //When you want to send data to the server or as a server if you want to accept data, you have to use request body.
    public JobPost  addJob(@RequestBody JobPost jobPost){//If we use void means it will just add the Job details.
         service.addJob(jobPost);
        // return jobPost; //This is not a good way of returning the data
         //If we use JobPost object in method and in return added job post details it will print data after adding it
        return service.getJob(jobPost.getPostId());//Here we are mentioning the job id which we are entering.
        //This will make sure that you have already stored the data somewhere
    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
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

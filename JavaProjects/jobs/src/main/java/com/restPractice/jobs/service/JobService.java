package com.restPractice.jobs.service;


import com.restPractice.jobs.model.JobPost;
import com.restPractice.jobs.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
	@Autowired
	public JobRepo repo;
	
	
	
	//method to return all JobPosts
	public List<JobPost> getAllJobPosts() {
		return repo.getAllJobPosts();

		
	}

	
	
	
	// method to add a jobPost
	public void addJob(JobPost jobPost) {
		 repo.addJobPost(jobPost);
	
	}


	public JobPost getJob(int postid) {
		return repo.getJob(postid);
	}

	public void updateJob(JobPost jobPost) {
		repo.updateJob(jobPost);
	}

	public void deleteJob(int postId) {
		repo.deleteJob(postId);
	}
}

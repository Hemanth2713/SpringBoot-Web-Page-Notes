package com.restPractice.jobs.service;


import com.restPractice.jobs.model.JobPost;
import com.restPractice.jobs.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

	@Autowired
	public JobRepo repo;
	
	
	
	//method to return all JobPosts
	public List<JobPost> getAllJobPosts() {
		return repo.findAll();
	}
	
	// method to add a jobPost
	public void addJob(JobPost jobPost) {
		 repo.save(jobPost);
	
	}

	public JobPost getJob(int postid) {
		//return repo.findById(postid)//If we are using this there some issue i.e, it might return optional
		return repo.findById(postid).orElse(new JobPost());//So we are using or else(if the find by id is empty in that case you can return a new job post object
	}

	public void updateJob(JobPost jobPost) {
		repo.save(jobPost);
	}

	public void deleteJob(int postId) {
		repo.deleteById(postId);
	}

	public void load() {
		List<JobPost>  jobs = new ArrayList<>(List.of(
				new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
						List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
				new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
						List.of("HTML", "CSS", "JavaScript", "React")),
				new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
						List.of("Python", "Machine Learning", "Data Analysis")),
				new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
						List.of("Networking", "Cisco", "Routing", "Switching")),
				new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
						List.of("iOS Development", "Android Development", "Mobile App"))
		));

		repo.saveAll(jobs);//When you call this method for the first time or whenever you call this method,it will try to save this entire list in database.
	}

	public List<JobPost> search(String keyword) {
		return repo.findByPostProfileContainingOrPostDescContaining(keyword,keyword);
	}
}

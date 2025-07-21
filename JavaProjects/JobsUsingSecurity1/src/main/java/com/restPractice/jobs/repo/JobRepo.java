package com.restPractice.jobs.repo;

import java.util.List;

import com.restPractice.jobs.model.JobPostSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepo  extends JpaRepository<JobPostSecurity,Integer> {

    List<JobPostSecurity> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);
}

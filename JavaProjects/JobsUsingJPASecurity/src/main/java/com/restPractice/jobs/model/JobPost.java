package com.restPractice.jobs.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class JobPost {
	@Id
	private int postId;
	private String postProfile;
	private String postDesc;
	private Integer reqExperience;
	private List<String> postTechStack;

	// Custom toString method if you want to format it
	@Override
	public String toString() {
		return String.format("{\"postId\": %d, \"postProfile\": \"%s\", \"postDesc\": \"%s\", \"reqExperience\": %d, \"postTechStack\": %s}",
				postId, postProfile, postDesc, reqExperience, postTechStack);
	}
}

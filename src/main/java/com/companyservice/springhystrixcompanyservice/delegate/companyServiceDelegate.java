package com.companyservice.springhystrixcompanyservice.delegate;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class companyServiceDelegate {
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "callcompanyServiceAndGetData_Fallback")
	public String callcompanyServiceAndGetData(String student) {
		System.out.println("Getting School details for " + student);
		String response = restTemplate
				.exchange("http://localhost:8081/student-service/getStudentDetailsForSchool/abcschool"
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<String>() {
			}, student).getBody();

		System.out.println("Response Received as " + response + " -  " + new Date());

		return "NORMAL FLOW !!! - School Name -  " + student + " :::  Student Details " + response + " -  " + new Date();
	}
	
	@SuppressWarnings("unused")
	private String callcompanyServiceAndGetData_Fallback(String student) {
		System.out.println("Student Service is down!!! fallback route enabled...");
		return "CIRCUIT BREAKER ENABLED!!!No Response From Student Service at this moment. Service will be back shortly - " + new Date();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

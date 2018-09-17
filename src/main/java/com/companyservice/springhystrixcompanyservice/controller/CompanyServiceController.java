package com.companyservice.springhystrixcompanyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.companyservice.springhystrixcompanyservice.delegate.companyServiceDelegate;

@RestController
public class CompanyServiceController {
	
	@Autowired
	companyServiceDelegate studentServiceDelegate;

	@RequestMapping(value = "/getStudentDetails/{studentname}", method = RequestMethod.GET)
	public String getStudents(@PathVariable String studentname) {
		System.out.println("Going to call student service to get data!");
		return studentServiceDelegate.callcompanyServiceAndGetData(studentname);
	}
	
}

package com.journaldev.spring.jdbc.controller;
 
import java.util.List; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.spring.jdbc.model.Employee;

/**
 * Handles requests for the Employee JDBC Service.
 */
@Controller
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	 
	@RequestMapping(value = "/rest/emps", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployees(
			HttpServletRequest request,
			HttpServletResponse response			
			) {
		logger.info("Start getAllEmployees.");
		
		SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tx = session.beginTransaction();
		List<Employee> empList =  session.createCriteria(Employee.class).list();
		tx.commit();
		 
		
		return empList;
	}

}

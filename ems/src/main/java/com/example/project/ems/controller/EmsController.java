package com.example.project.ems.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.project.ems.model.Employee;
import com.example.project.ems.service.EmployeeService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class EmsController {
	@Autowired
	EmployeeService emp;
	String username;
	String password;


	@RequestMapping("/")
	public ModelAndView Login() {
		return new ModelAndView("loginPage");
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView LoginAuth(HttpServletRequest request) {
		username = request.getParameter("usrname");
		password = request.getParameter("psw");
		Employee theEmployee = emp.findByUsernameAndPassword(username, password);
		if (theEmployee == null) {


			return new ModelAndView("loginPage");
		} else if(theEmployee.getRole().equals("ADMIN")) {

			return new ModelAndView("redirect:/admin");

		}
		else if(theEmployee.getRole().equals("admin")) {

			return new ModelAndView("redirect:/admin");

		}

		else if(theEmployee.getRole().equals("Admin")) {

			return new ModelAndView("redirect:/admin");

		}
		else
		{   
			return new ModelAndView("redirect:/employee/"+theEmployee.getEmpId()+"/dashboard");
		}

	}

	@RequestMapping("/admin")
	public ModelAndView m1()
	{
		return new ModelAndView("admin");
	}

	@RequestMapping("/logout")
	public ModelAndView m3(){
		return new ModelAndView("redirect:/");
	}

}

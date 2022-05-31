package com.example.project.ems.service;

import java.util.List;
import org.springframework.data.domain.Page;

import com.example.project.ems.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	Employee saveEmployee(Employee employee);
	Employee getEmployeeById(int id);
	public Employee findByUsernameAndPassword(String username, String password);
	Page<Employee> findPaginated(int pageNo,int pageSize);

}

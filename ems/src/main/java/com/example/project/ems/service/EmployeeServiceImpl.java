package com.example.project.ems.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.project.ems.dao.EmployeeDao;
import com.example.project.ems.model.Employee;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public Employee findByUsernameAndPassword(String username, String password) {
		return employeeDao.findByUsernameAndPassword(username, password);
	}


	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo-1,pageSize);
		return this.employeeDao.findAll(pageable);
	}



	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		//System.out.print("inside");
		System.out.print(employeeDao.findAll());
		return employeeDao.findAll();
	}

	@Override
	public Employee saveEmployee(Employee employee)
	{
		return employeeDao.save(employee);
	}
	@Override
	public Employee getEmployeeById(int id) {
		Optional<Employee> optional =employeeDao.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	public void deleteById(int id){
		employeeDao.deleteById(id);
		return;
	}


}



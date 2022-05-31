package com.example.project.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.ems.dao.DepartmentDao;
import com.example.project.ems.model.Department;

@Service
public class DepartmentDaoService {

	@Autowired
	DepartmentDao departmentDao;

	public Department insertDepartment(Department department){
		return departmentDao.save(department);
	}

	public Department updateDepartment(Department department){
		return departmentDao.save(department);
	}

	public List<Department> getAllDepartments(){
		return departmentDao.findAll();
	}

	public Department getDepartmentById(int id){
		return departmentDao.getById(id);
	}

	public void deleteById(int id){
		departmentDao.deleteById(id);
	}
}

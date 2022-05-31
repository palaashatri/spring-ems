package com.example.project.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.ems.model.Employee;


@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer> {
    public Employee findByUsernameAndPassword(String username, String password);    
}

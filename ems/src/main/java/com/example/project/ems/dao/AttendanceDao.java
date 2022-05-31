package com.example.project.ems.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.ems.model.Attendance;
@Repository
public interface AttendanceDao extends JpaRepository<Attendance,Integer>{
    
    List<Attendance> findAttendanceByEmpId(int empId);
}

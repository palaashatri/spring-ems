package com.example.project.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.ems.dao.AttendanceDao;
import com.example.project.ems.model.Attendance;

import java.util.List;

@Service
public class AttendanceDaoService {
    @Autowired
    AttendanceDao attendanceDao;
    public Attendance insertAttendance(Attendance attendance){
       return attendanceDao.save(attendance);
    }

    public Attendance updateAttendance(Attendance attendance){
        return attendanceDao.save(attendance);
    }

    public List<Attendance> getAllAttendance(){
        return attendanceDao.findAll();
    }

    public Attendance getAttendanceById(int id){
        return attendanceDao.getById(id);
    }
    public void deleteById(int id){
        attendanceDao.deleteById(id);
        return;
    }
}

package com.example.project.ems.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "attendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int attId;
	private int empId;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date inTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date outTime;
	private String  status = "Pending";

	public Attendance() {
	}
	public Attendance(int attId, int empId, Date inTime, Date outTime, String status) {
		this.attId = attId;
		this.empId = empId;
		this.inTime = inTime;
		this.outTime = outTime;
		this.status = status;
	}
	public int getAttId() {
		return attId;
	}
	public void setAttId(int attId) {
		this.attId = attId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Attendance [attId=" + attId + ", empId=" + empId + ", inTime=" + inTime + ", outTime=" + outTime
				+ ", status=" + status + "]";
	}


}

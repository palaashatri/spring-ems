package com.example.project.ems.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


@Entity

public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int empId;
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String username;
	private String password;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfJoining;
	private String gender;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String role;
	private int departmentId;
	@Column(length=45,nullable=true)
	private String photo;
	private String isActive;

	public Employee() {
		super();
	}
	public Employee(int empId, String firstName, String lastName, String username, String password,
			LocalDate dateOfJoining, String gender, LocalDate dateOfBirth, String role, int departmentId, String photo,
			String isActive) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.dateOfJoining = dateOfJoining;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.departmentId = departmentId;
		this.photo = photo;
		this.isActive = isActive;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Transient
	public String getPhotoImagePath(){
		if(photo == null || empId==0 ) return null;
		return "/employee-photos/" + empId + "/" + photo;
	}


	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", dateOfJoining=" + dateOfJoining + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", role=" + role + ", departmentId=" + departmentId + ", photo="
				+ photo + ", isActive=" + isActive + "]";
	}




}

package com.example.project.ems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int departmentId;
    private String departmentName;
    private String departmentLocation;

    
    public Department() {
    }
    
    public Department(int departmentId, String departmentName, String departmentLocation) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentLocation = departmentLocation;
    }

    public int getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getDepartmentLocation() {
        return departmentLocation;
    }
    public void setDepartmentLocation(String departmentLocation) {
        this.departmentLocation = departmentLocation;
    }
    @Override
    public String toString() {
        return "Department [departmentId=" + departmentId + ", departmentLocation=" + departmentLocation
                + ", departmentName=" + departmentName + "]";
    }
    
    
}

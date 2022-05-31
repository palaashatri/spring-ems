<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.ltts.project.ems.model.*, com.ltts.project.ems.dao.*,java.util.*,com.ltts.project.ems.service.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee</title>
</head>
<body>
    <h1>Employee Profile Page</h1>
    <%
        EmployeeDaoService service =  (EmployeeDaoService) request.getAttribute("service");
        int id = (int) request.getAttribute("id");
        Employee em = service.getEmployeeById(id);
    %>

    <div>
        <div>
            <img src="<%=em.getEmpPhoto()%>">
        </div>
        <ul>
            <li>Employee Id : <span><%=em.getEmpId() %></span></li>
            <li>Employee Name : <span><%=em.getFirstName()%> <%=em.getLastName()%></span></li>
            <li>Employee Username : <span><%=em.getUserName()%></span></li>
            <li>Employee Date of Joining : <span><%=em.getDateOfJoining()%></span></li>
            <li>Employee Gender : <span><%=em.getGender()%></span></li>
            <li>Employee Date of Birth : <span><%=em.getDateOfBirth()%></span></li>
            <li>Employee Role : <span><%=em.getRole()%></span></li>
            <li>Department : <span><%=em.getDepartmentId()%></span></li>
        </ul>
        <button><a href="updateEmployees">Update Profile</a></button>
        <button><a href="/employees/<%=em.getEmpId()%>/setInactive"> set Inactive</a></button>
    </div>
</body>
</html>
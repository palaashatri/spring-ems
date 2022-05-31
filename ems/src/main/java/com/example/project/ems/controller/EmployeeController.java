package com.example.project.ems.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.ems.dao.AttendanceDao;
import com.example.project.ems.model.Attendance;
import com.example.project.ems.model.Employee;
import com.example.project.ems.service.AttendanceDaoService;
import com.example.project.ems.service.EmployeeService;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
public class EmployeeController {
	static List<String> rolesList=null;
	static{
		rolesList=new ArrayList<>();
		rolesList.add("SDE 1");
		rolesList.add("SDE 2");
		rolesList.add("SDE 3");
		rolesList.add("TESTER");
		rolesList.add("QA");
		rolesList.add("MANAGER");
		rolesList.add("CONSULTANT");
		rolesList.add("HR");
		rolesList.add("EXECUTIVE");
		rolesList.add("ADMIN");

	}


	@Autowired
	AttendanceDaoService attendance_service;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	AttendanceDao ad;



	// =========admin routes=============

	// show all employees
	@GetMapping("/admin/employees") 

	public String viewHomePage(Model model)
	{
		// shows employee repository. distributes employees into 2 tables > active and inactive
		model.addAttribute("listEmployees",employeeService.getAllEmployees());
		System.out.print(employeeService.getAllEmployees());
		return findPaginated(1, model);
	}


	// create new employee
	@GetMapping("/admin/employees/new")
	public String showNewEmployeeForm(Model model) {
		// create new employee
		// create model attribute to bind form data

		Employee employee = new Employee();
		model.addAttribute("rolesList",rolesList);
		model.addAttribute("employee", employee);
		return "new_employee";
	}

	// new employee post
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee,@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		//save employee to db
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		employee.setPhoto(fileName);
		Employee savedEmployee= employeeService.saveEmployee(employee);
		String uploadDir ="./employee-photos/" + savedEmployee.getEmpId();

		Path uploadPath = Paths.get(uploadDir);

		if(!Files.exists(uploadPath)){
			Files.createDirectories(uploadPath);

		}

		try(InputStream inputStream =multipartFile.getInputStream()){
			Path filePath=uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath());
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
		}
		catch(IOException e) {
			throw new IOException("Could not save uploaded file: "+ fileName);
		}
		return "redirect:/admin/employees";
	}


	// view employee details 
	@GetMapping("/admin/employees/{id}")
	public String employeeProfile(@PathVariable(value = "id")int id, Model model){
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "employee_profile";
	}

	// update employee details
	@GetMapping("/admin/employees/{id}/update")
	public String showFormForUpdate(@PathVariable ( value = "id") int id, Model model) {

		// fetch employee data from db, put them into form and update them into form
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("rolesList",rolesList);
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	//update employee handler
	@PostMapping("/updateEmployee")
	public String updateHandler(@ModelAttribute("employee") Employee employee,Model m,@RequestParam("fileImage") MultipartFile multipartFile) throws IOException{

		try{
			//getting old employee details
			Employee oldEmployee=employeeService.getEmployeeById(employee.getEmpId());

			//image
			if(!multipartFile.isEmpty()){
				//file work
				//rewrite
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				employee.setPhoto(fileName);
				Employee savedEmployee= employeeService.saveEmployee(employee);
				String uploadDir ="./employee-photos/" + savedEmployee.getEmpId();

				Path uploadPath = Paths.get(uploadDir);

				if(!Files.exists(uploadPath)){
					Files.createDirectories(uploadPath);

				}

				try(InputStream inputStream =multipartFile.getInputStream()){
					Path filePath=uploadPath.resolve(fileName);
					System.out.println(filePath);
					System.out.println(filePath.toFile().getAbsolutePath());
					System.out.println("above is path");
					Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
				}
				catch(IOException e) {
					throw new IOException("Could not save uploaded file: "+ fileName);
				}
			}
			else{
				employee.setPhoto(oldEmployee.getPhoto());
			}
			employeeService.saveEmployee(employee);

		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Employee id: "+employee.getEmpId());
		System.out.println("Employee Name: "+employee.getFirstName());
		return "redirect:/admin/employees";
	}

	// set inactive employee
	@GetMapping("/admin/employees/{id}/setInactive")
	public String setInactive(@PathVariable(value = "id") int id){

		// set employee state as inactive
		Employee employee = employeeService.getEmployeeById(id);
		employee.setIsActive("false");
		employeeService.saveEmployee(employee);
		return "redirect:/admin/employees";
	}

	// set active employee
	@GetMapping("/admin/employees/{id}/setActive")
	public String setActive(@PathVariable(value = "id") int id){

		// set employee state as inactive
		Employee employee = employeeService.getEmployeeById(id);
		employee.setIsActive("true");
		employeeService.saveEmployee(employee);
		return "redirect:/admin/employees";
	}
	// ========attendance dashboard===========
	@GetMapping("/admin/attendance") 
	public String attendance(Model model)
	{
		model.addAttribute("listAttendance",attendance_service.getAllAttendance());
		System.out.print(attendance_service.getAllAttendance());
		return "attendance";
	}


	@GetMapping("/admin/attendance/new")
	public String newAttendanceForm(Model model){
		Attendance attendance = new Attendance();
		model.addAttribute("attendance", attendance);
		return "new_attendance";
	}
	@PostMapping("/saveAttendance")
	public String saveAttendance(@ModelAttribute("attendance") Attendance attendance)  {
		//save attendance to db  
		Attendance savedAttendance= attendance_service.insertAttendance(attendance);
		return "redirect:/admin/attendance/new";
	}

	@GetMapping("/admin/attendance/{id}/accept")
	public String acceptAttendance(@PathVariable(value = "id") int id)
	{
		Attendance attendance=attendance_service.getAttendanceById(id);
		attendance.setStatus("Approved");
		attendance_service.updateAttendance(attendance);
		return "redirect:/admin/attendance";


	}

	@GetMapping("/admin/attendance/{id}/reject")
	public String rejectAttendance(@PathVariable(value = "id") int id)
	{
		Attendance attendance=attendance_service.getAttendanceById(id);
		attendance.setStatus("Rejected");
		attendance_service.updateAttendance(attendance);
		return "redirect:/admin/attendance";
	}



	//===============Employee routes====================
	// view employee dashboard
	@GetMapping("/employee/{id}/dashboard")
	public String employeeDashboard(@PathVariable(value = "id")int id, Model model){
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "employeeDashboard";
	}


	//update employee from dashboard
	@GetMapping("/employee/{id}/update")
	public String updateEmpDashboard(@PathVariable ( value = "id") int id, Model model) {

		// fetch employee data from db, put them into form and update them into form
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("rolesList",rolesList);
		model.addAttribute("employee", employee);
		return "update_employee_dashboard";
	}

	//Emp update

	//update employee handler from dashboard
	@PostMapping("/updateEmployeeDashboard")
	public String updateEmployeeDashboard(@ModelAttribute("employee") Employee employee,Model m,@RequestParam("fileImage") MultipartFile multipartFile) throws IOException{

		try{
			//getting old employee details
			Employee oldEmployee=employeeService.getEmployeeById(employee.getEmpId());
			
			//image
			if(!multipartFile.isEmpty()){
				//file work
				//rewrite
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				employee.setPhoto(fileName);
				Employee savedEmployee= employeeService.saveEmployee(employee);
				String uploadDir ="./employee-photos/" + savedEmployee.getEmpId();

				Path uploadPath = Paths.get(uploadDir);

				if(!Files.exists(uploadPath)){
					Files.createDirectories(uploadPath);
				}

				try(InputStream inputStream =multipartFile.getInputStream()){
					Path filePath=uploadPath.resolve(fileName);
					System.out.println(filePath);
					System.out.println(filePath.toFile().getAbsolutePath());
					System.out.println("above is path");
					Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
				}
				catch(IOException e) {
					throw new IOException("Could not save uploaded file: "+ fileName);
				}
			}
			else{
				employee.setPhoto(oldEmployee.getPhoto());
			}
			employeeService.saveEmployee(employee);

		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Employee id: "+employee.getEmpId());
		System.out.println("Employee Name: "+employee.getFirstName());
		return "redirect:/employee/"+employee.getEmpId()+"/dashboard";
	}

	@GetMapping("/employeeDashboard")
	public String employeeDashboard(Model model){

		return "employeeDashboard";
	}

	@GetMapping("/employee/{id}/regularise")
	public String regulariseAttendance(@PathVariable (value = "id") int id, Model model){
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		Attendance attendance = new Attendance();
		model.addAttribute("attendance", attendance);
		return "regulariseAttendance";
	}


	@GetMapping("/employee/{id}/attendance")
	public String findAttendanceByEmpId(@PathVariable(value="id") int id, Model model){
		List<Attendance> attendances=ad.findAttendanceByEmpId(id);
		model.addAttribute("attendances", attendances);
		return "viewAllRequests";
	}

	@PostMapping("/saveAttendanceDashboard")
	public String saveAttendanceDashboard(@ModelAttribute("attendance") Attendance attendance)  {
		//save attendance to db  
		Attendance savedAttendance= attendance_service.insertAttendance(attendance);
		return "redirect:/employee/"+savedAttendance.getEmpId()+"/attendance";
	}

	//Pagination

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;

		Page < Employee > page = employeeService.findPaginated(pageNo, pageSize);
		List < Employee > listEmployees = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmployees", listEmployees);
		return "employees";
	}


}
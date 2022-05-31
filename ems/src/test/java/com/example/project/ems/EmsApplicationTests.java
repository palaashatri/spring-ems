package com.example.project.ems;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.ems.controller.EmployeeController;
import com.example.project.ems.controller.EmsController;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class EmsApplicationTests {

	@Autowired
	EmployeeController ec;	

	@Autowired 
	EmsController emsc;


	@Test
	void contextLoads() {
		assertThat(ec).isNotNull();
		assertThat(emsc).isNotNull();
	}

	

}

package com.example.project.ems;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment =WebEnvironment.RANDOM_PORT)
public class HttpRequestPort {
    @LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;


    @Test
    public void testHomePage(){
		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/admin/employees",String.class)).contains("employees");
    }

    

    
}

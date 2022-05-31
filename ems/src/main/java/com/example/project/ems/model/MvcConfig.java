package com.example.project.ems.model;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//path="./employee-photos/"
@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path employeeUploadDir=Paths.get("./employee-photos");
		String employeeUploadPath=employeeUploadDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/employee-photos/**").addResourceLocations("file:/"+employeeUploadPath+"/");    
    }    
}

package com.example.demo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	ModelMapperConfig modelMapper(){
		
		ModelMapperConfig modelmapper = new ModelMapperConfig();
		return  modelmapper;
		
		
		
		
	}

}

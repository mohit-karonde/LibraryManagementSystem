package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter


public class BookDto {
	
	// We did not use modeMapper in this project.

	
    private String bookName;
	
	private String description;
	
	private Boolean isAvailable;
	
	private String  author;

}

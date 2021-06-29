package com.example.demo;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"dob"})
public class User {
	private Integer id;
	@Size(min = 2,message = "Name must have more then 1 chars")
	private String name;
	@Past(message = "DOB can't be future date")
	private Date dob;

}

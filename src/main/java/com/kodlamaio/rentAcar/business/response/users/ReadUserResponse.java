package com.kodlamaio.rentAcar.business.response.users;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadUserResponse {
	private int id;
	private String name;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate birthDate;
}
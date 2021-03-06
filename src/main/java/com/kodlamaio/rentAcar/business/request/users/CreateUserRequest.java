package com.kodlamaio.rentAcar.business.request.users;


import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

	@Size(min = 2)
	@NotBlank
	@NotEmpty
	@NotNull
	private String firstName;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 2)
	private String lastName;


	@NotNull
	private int birthDate;

	
	@NotBlank
	@NotEmpty
	@NotNull
	@Email
	private String email;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 4, max = 16)
	private String password;

}
package com.kodlamaio.rentAcar.business.request.corporateCustomer;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {

	@NotBlank
	@NotNull
	private String taxNumber;

	@NotBlank
	@NotNull
	private String corporateNumber;

	@Email
	private String email;

	@NotBlank
	@NotNull
	private String password;
	}

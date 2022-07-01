package com.kodlamaio.rentAcar.business.request.address;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {
	@NotEmpty
	@NotNull
	private String address;
	private int userId;
	private int addressType;

}
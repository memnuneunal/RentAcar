package com.kodlamaio.rentAcar.business.response.address;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadAddressResponse {
	private String address;
	private int userId;
	private int addressType;

}
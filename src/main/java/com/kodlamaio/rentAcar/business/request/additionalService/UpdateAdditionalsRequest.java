package com.kodlamaio.rentAcar.business.request.additionalService;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalsRequest {
	private int id;
	private int rentalId;
	private int additionalItemId;

}
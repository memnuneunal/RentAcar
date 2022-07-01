package com.kodlamaio.rentAcar.business.request.additionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalsRequest {
	private int additionalItemId;
	private int rentalId;
}
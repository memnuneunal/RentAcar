package com.kodlamaio.rentAcar.business.request.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
	public class UpdateCarRequest {
	private int id;
	private String description;
	private double dailyPrice;
	private Double kilometer;
	private String carPlate;
	private int brandId;
	private int colorId;
}

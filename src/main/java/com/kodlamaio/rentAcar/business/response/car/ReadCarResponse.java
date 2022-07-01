package com.kodlamaio.rentAcar.business.response.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadCarResponse {
	private int id;
	private String description;
	private String licensePlate;
	private double dailyPrice;
	private int kilometer;
	private int state;
	private int brand_id;
	private int color_id;
	private int carScore;
}

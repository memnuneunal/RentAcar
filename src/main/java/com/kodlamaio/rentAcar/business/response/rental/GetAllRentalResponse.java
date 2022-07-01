package com.kodlamaio.rentAcar.business.response.rental;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRentalResponse {
	private int id;
	private LocalDate pickUpdate;
	private LocalDate returnDate;
	private double totalDays;
	private double totalPrice;
	private int carId;
	private int pickUpCityId;
	private int returnCityId;
	private int userId;

}

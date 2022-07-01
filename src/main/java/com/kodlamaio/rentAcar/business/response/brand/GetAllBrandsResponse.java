package com.kodlamaio.rentAcar.business.response.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBrandsResponse {
	private int id;
	private String name;
}

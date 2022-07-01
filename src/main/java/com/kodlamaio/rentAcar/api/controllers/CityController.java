package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.CityServices;
import com.kodlamaio.rentAcar.business.request.cities.CreateCityRequest;
import com.kodlamaio.rentAcar.business.request.cities.DeleteCityRequest;
import com.kodlamaio.rentAcar.business.request.cities.UpdateCityRequest;
import com.kodlamaio.rentAcar.business.response.cities.GetCityResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;


@RestController
@RequestMapping("/api/cities")
public class CityController<GetAllCitiesResponse> {
	private CityServices cityService;

	
	public CityController(CityServices cityService) {
		super();
		this.cityService = cityService;
	}


	@PostMapping("/add")
	public Result add(@RequestBody CreateCityRequest createCityRequest) {
	return this.cityService.add(createCityRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateCityRequest updateCityRequest) {
	return this.cityService.update(updateCityRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCityRequest deleteCityRequest) {
	return this.cityService.delete(deleteCityRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<GetCityResponse> getById(int id) {
		return this.cityService.getById(id);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllCitiesResponse>> getAll() {
	return this.cityService.getAll();
	}

}

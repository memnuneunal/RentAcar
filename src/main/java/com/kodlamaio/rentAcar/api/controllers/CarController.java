package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.CarServices;
import com.kodlamaio.rentAcar.business.request.car.CreateCarRequest;
import com.kodlamaio.rentAcar.business.request.car.UpdateCarRequest;
import com.kodlamaio.rentAcar.business.response.car.GetAllCarsResponse;
import com.kodlamaio.rentAcar.business.response.car.ReadCarResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

@RestController
@RequestMapping("api/cars")
public class CarController {
	@Autowired
	private CarServices carServices;

	

	@PostMapping("/add")
	public Result add(@RequestBody CreateCarRequest createcarRequest) {
		return carServices.add(createcarRequest);
	}
	@PostMapping("/update")
	
	public Result update(@RequestBody UpdateCarRequest updatecarRequest) {
		return carServices.update(updatecarRequest);
	}
	@PostMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return carServices.delete(id);
	}
	
	@GetMapping("/getById")
	public DataResult<ReadCarResponse> findById(@RequestParam int id) {
		return this.carServices.findById(id);
		
	}
	@GetMapping("/getAll")
	public DataResult<List<GetAllCarsResponse>> getAll()
	{
		return this.carServices.getAll();
	}
	
}

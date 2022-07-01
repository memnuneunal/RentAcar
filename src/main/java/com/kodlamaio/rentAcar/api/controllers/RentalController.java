package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.RentalServices;
import com.kodlamaio.rentAcar.business.request.rental.CreateRentalRequest;
import com.kodlamaio.rentAcar.business.request.rental.UpdateRentalRequest;
import com.kodlamaio.rentAcar.business.response.rental.GetAllRentalResponse;
import com.kodlamaio.rentAcar.business.response.rental.ReadRentalResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

@RestController
@RequestMapping("/api/rental")
public class RentalController<GetRentalResponse> {
	
	private RentalServices rentalService;


	@PostMapping("/add")
	public Result add(@RequestBody CreateRentalRequest createRentalRequest) {

		return this.rentalService.add(createRentalRequest);

	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);
	}

	@DeleteMapping("/{id}")
	public Result delete(@RequestBody int id) {
		return this.rentalService.delete(id);

	}

	@GetMapping("/getById")
	public DataResult<ReadRentalResponse> getById(@RequestParam int id) {
		return this.rentalService.findById(id);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllRentalResponse>> getAll() {
		return this.rentalService.getAll();
	}

}

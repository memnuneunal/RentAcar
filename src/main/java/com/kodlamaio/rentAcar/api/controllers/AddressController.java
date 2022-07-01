package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.AddressService;
import com.kodlamaio.rentAcar.business.request.address.CreateAddressRequest;
import com.kodlamaio.rentAcar.business.request.address.UpdateAddressRequest;
import com.kodlamaio.rentAcar.business.response.address.GetAllAddressResponse;
import com.kodlamaio.rentAcar.business.response.address.ReadAddressResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
@RestController
@RequestMapping("api/address")
public class AddressController {

	private AddressService addressService;
	
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateAddressRequest createAddressRequest) {
		return this.addressService.add(createAddressRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateAddressRequest updateAddressRequest) {
		return this.addressService.update(updateAddressRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody int id) {
		return this.addressService.delete(id);
	}
	
	@GetMapping("getById")
	public DataResult<ReadAddressResponse> getById(@RequestParam int id) {
		return this.addressService.getById(id);
	}
	
	@GetMapping("getAll")
	public DataResult<List<GetAllAddressResponse>> getAll(){
		return this.addressService.getAll();
	}
	
	@GetMapping("getAllBillAddress")
	public DataResult<List<GetAllAddressResponse>> getAllBillAddress(@RequestParam int userId, int addressType){
		return this.addressService.getAllBillAddress(userId, addressType);
	}
	
	@GetMapping("getAllContactAddress")
	public DataResult<List<GetAllAddressResponse>> getAllContactAddress(@RequestParam int userId, int addressType){
		return this.addressService.getAllContactAddress(userId, addressType);
	}
}
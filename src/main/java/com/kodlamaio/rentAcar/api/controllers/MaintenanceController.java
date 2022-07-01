package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.MaintenanceServices;
import com.kodlamaio.rentAcar.business.request.maintenance.CreateMaintenanceRequest;
import com.kodlamaio.rentAcar.business.request.maintenance.DeleteMaintenanceRequest;
import com.kodlamaio.rentAcar.business.request.maintenance.UpdateMaintenanceRequest;
import com.kodlamaio.rentAcar.business.response.maintenance.GetAllMaintenanceResponse;
import com.kodlamaio.rentAcar.business.response.maintenance.ReadMaintenanceResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

@RequestMapping("/api/maintenance")
@RestController
public class MaintenanceController {
	@Autowired
	MaintenanceServices maintenanceServices;


	@PostMapping("/add")
	public Result add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) {
		return this.maintenanceServices.add(createMaintenanceRequest);

	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteMaintenanceRequest deleteMaintenanceRequest) {
		return this.maintenanceServices.delete(deleteMaintenanceRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateMaintenanceRequest updateMaintenanceRequest) {
		return this.maintenanceServices.update(updateMaintenanceRequest);
	}

	@GetMapping("/getById")
	public DataResult<ReadMaintenanceResponse> getById(@RequestParam int id) {
		return this.maintenanceServices.getById(id);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllMaintenanceResponse>> getAll() {
		return this.maintenanceServices.getAll();
	}
}
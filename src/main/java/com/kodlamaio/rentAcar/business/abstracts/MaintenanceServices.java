package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.business.request.maintenance.CreateMaintenanceRequest;
import com.kodlamaio.rentAcar.business.request.maintenance.DeleteMaintenanceRequest;
import com.kodlamaio.rentAcar.business.request.maintenance.UpdateMaintenanceRequest;
import com.kodlamaio.rentAcar.business.response.maintenance.GetAllMaintenanceResponse;
import com.kodlamaio.rentAcar.business.response.maintenance.ReadMaintenanceResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface MaintenanceServices {
	Result add(CreateMaintenanceRequest createMaintenanceRequest);
	//boolean IsCarInMaintanance (int carId) ;
	//Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);

	Result update(UpdateMaintenanceRequest updateMaintenanceRequest);

	Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);

	

	DataResult<List<GetAllMaintenanceResponse>> getAll();

	DataResult<ReadMaintenanceResponse> getById(int id);
}

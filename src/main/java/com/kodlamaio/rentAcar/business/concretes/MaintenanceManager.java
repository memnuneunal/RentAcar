package com.kodlamaio.rentAcar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.Car;
import com.kodlamaio.rentAcar.Entities.concretes.Maintenance;
import com.kodlamaio.rentAcar.business.abstracts.MaintenanceServices;
import com.kodlamaio.rentAcar.business.request.maintenance.CreateMaintenanceRequest;
import com.kodlamaio.rentAcar.business.request.maintenance.DeleteMaintenanceRequest;
import com.kodlamaio.rentAcar.business.request.maintenance.UpdateMaintenanceRequest;
import com.kodlamaio.rentAcar.business.response.maintenance.GetAllMaintenanceResponse;
import com.kodlamaio.rentAcar.business.response.maintenance.ReadMaintenanceResponse;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.MaintenanceRepository;
@Service
public class MaintenanceManager implements MaintenanceServices {
    

	private MaintenanceRepository maintenanceRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	
	

	@Autowired
	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository,
			ModelMapperService modelMapperService) {
		super();
		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		Car car = this.carRepository.findById(createMaintenanceRequest.getCar_id());
		car.setId(createMaintenanceRequest.getCar_id());
		//car.setCarPlate(2);
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);

		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("ADDED.MAINTENANCE");
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		Maintenance maintenance = this.modelMapperService.forRequest().map(deleteMaintenanceRequest, Maintenance.class);
		this.maintenanceRepository.delete(maintenance);
		return new SuccessResult("MAINTENANCE.DELETED");
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		Car car = this.carRepository.findById(updateMaintenanceRequest.getCarId());
		car.setId(updateMaintenanceRequest.getCarId());
		
		LocalDate localDate = LocalDate.now();

		if (localDate.equals(updateMaintenanceRequest.getDateReturned())) {
			//car.setCarPlate(1);;
		}
		
		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		this.maintenanceRepository.save(maintenance);

		return new SuccessResult("MAINTENANCE.UPDATED");
	}



	/*@Override
	public DataResult <List<GetAllMaintenanceResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();
		List<GetAllMaintenanceResponse> response = maintenances.stream()
				.map(maintenance -> modelMapperService.forResponse().map(maintenance, GetAllMaintenanceResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllMaintenanceResponse>>(response);
	}*/


	@Override
	public DataResult<ReadMaintenanceResponse> getById(int id) {
		Optional<Maintenance> maintenance = Optional.ofNullable(this.maintenanceRepository.findById(id));
		ReadMaintenanceResponse response = this.modelMapperService.forResponse().map(maintenance, ReadMaintenanceResponse.class);
		return new SuccessDataResult<ReadMaintenanceResponse>(response);
	}

	@Override
	public DataResult<List<GetAllMaintenanceResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();
		List<GetAllMaintenanceResponse> response = maintenances.stream()
				.map(maintenance -> modelMapperService.forResponse().map(maintenance, GetAllMaintenanceResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllMaintenanceResponse>>(response);
	}

	

}

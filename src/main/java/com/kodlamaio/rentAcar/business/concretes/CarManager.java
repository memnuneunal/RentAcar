package com.kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.Car;
import com.kodlamaio.rentAcar.business.abstracts.CarServices;
import com.kodlamaio.rentAcar.business.request.car.CreateCarRequest;
import com.kodlamaio.rentAcar.business.request.car.UpdateCarRequest;
import com.kodlamaio.rentAcar.business.response.car.GetAllCarsResponse;
import com.kodlamaio.rentAcar.business.response.car.ReadCarResponse;
import com.kodlamaio.rentAcar.core.Utilies.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
@Service
public class CarManager implements CarServices {
	@Autowired

	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService) {
		super();
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		checkIfCarPlateExist(createCarRequest.getCarPlate());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);	
		//checkNumberOfCars(car.getBrand().getId());
		//car.setCarPlate(1);;
		this.carRepository.save(car);
		return new SuccessResult("CAR.ADDED");
	
	}
	
	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		//car.setState(1);
		this.carRepository.save(car);
		return new SuccessResult("CAR.UPDATED");

	}
	
	@Override
	public Result delete(int id) {	
		checkIfCarExistById(id);
		this.carRepository.deleteById(id);
		return new SuccessResult("CAR.DELETED");
	}

	@Override
	public DataResult<List<GetAllCarsResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> response = cars.stream()
				.map(car -> modelMapperService.forResponse().map(car, GetAllCarsResponse.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllCarsResponse>>(response);
	}

	@Override
	public DataResult<ReadCarResponse> findById(int id) {
		checkIfCarExistById(id);
		Car car = this.carRepository.findById(id);
		ReadCarResponse response = this.modelMapperService.forResponse().map(car, ReadCarResponse.class);
		return new SuccessDataResult<ReadCarResponse>(response);
	
	}
	
	private void checkNumberOfCars(int brandId) {
		List<Car> existsCars = this.carRepository.findByBrandId(brandId);
		if(existsCars.size() > 5) 
			throw new BusinessException("CAR.NUMBER.IS.HIGHER.THAN.FIVE");		
	}
	
	
	private void checkIfCarPlateExist(String carPlate) {
		Car car = carRepository.findBycarPlate(carPlate);
		if (car != null)
			throw new BusinessException("LICENSE.PLATE.EXIST");
	}

	private void checkIfCarExistById(int id) {
		if (!(this.carRepository.existsById(id))) 
			throw new BusinessException("CAR.IS.NOT.EXIST");
	}

}

package com.kodlamaio.rentAcar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.Car;
import com.kodlamaio.rentAcar.Entities.concretes.Rental;
import com.kodlamaio.rentAcar.Entities.concretes.Users;
import com.kodlamaio.rentAcar.business.abstracts.RentalServices;
import com.kodlamaio.rentAcar.business.request.rental.CreateRentalRequest;
import com.kodlamaio.rentAcar.business.request.rental.UpdateRentalRequest;
import com.kodlamaio.rentAcar.business.response.rental.GetAllRentalResponse;
import com.kodlamaio.rentAcar.business.response.rental.ReadRentalResponse;
import com.kodlamaio.rentAcar.core.Utilies.adapters.abstracts.FindexCheckService;
import com.kodlamaio.rentAcar.core.Utilies.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.UsersRepository;


@Service
public class RentalManager implements RentalServices{

	private RentalRepository rentalRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private CityRepository cityRepository;
	private UsersRepository userRepository;
	

	public RentalManager(RentalRepository rentalRepository, CarRepository carRepository,
			ModelMapperService modelMapperService, CityRepository cityRepository, UsersRepository userRepository) {
		super();
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
		this.cityRepository = cityRepository;
		this.userRepository = userRepository;
	}


	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		//
		checkIfCarState(createRentalRequest.getCarId());
		checkDateToRentACar(createRentalRequest.getPickUpDate(), createRentalRequest.getReturnDate());
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		
		int diffDate = (int) ChronoUnit.DAYS.between(rental.getPickupDate(), rental.getReturnedDate());
		rental.setTotalDays(diffDate);
		
		Car car = this.carRepository.findById(createRentalRequest.getCarId());
		double totalPrice = calculateTotalPrice(rental, car.getDailyPrice());
		car.setState(3);
		
		rental.setTotalPrice(totalPrice);

		Users userId = userRepository.findById(createRentalRequest.getUserId());
		
		
		
		//checkFindexMinValue(userId.getIdentityNumber(), car.getCarScore());
		this.rentalRepository.save(rental);
		
		return new SuccessResult("RENTAL.ADDED");
	}
	




	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		Car car = carRepository.findById(updateRentalRequest.getCarId());
		car.setId(updateRentalRequest.getCarId());
		
		LocalDate date = LocalDate.now();

		this.rentalRepository.save(rental);
		return new SuccessResult("RENTAL.UPDATED");
	}

	
	@Override
	public Result delete(int id) {
		this.rentalRepository.deleteById(id);
		return new SuccessResult("RENTAL.DELETED");
	}
	



	@Override
	public DataResult<List<GetAllRentalResponse>> getAll() {
		
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalResponse> response = rentals.stream()
				.map(rental ->this.modelMapperService.forResponse()
						.map(rental, GetAllRentalResponse.class)).collect(Collectors.toList());			
		return new SuccessDataResult<List<GetAllRentalResponse>>(response);
	}
	
	/*private void checkFindexMinValue(String identityNumber, int carScore) {
		if(findexCheckService.CheckFindexScore(identityNumber) < carScore) {
			throw new BusinessException("RENTAL.NOT.ADDED.FINDEXPOINT.INSUFFICIENT");
		}
	}*/
	private void checkIfCarState(int id) {
		Car cars = this.carRepository.findById(id);
		if (cars.getState() == 2 || cars.getState() == 3) {
			throw new BusinessException("CAR.IS.NOT.AVAIBLE");
		}
	}

	private void checkDateToRentACar(LocalDate pickUpDate, LocalDate returnDate) {
		if (!pickUpDate.isBefore(returnDate) || pickUpDate.isBefore(LocalDate.now())) {
			throw new BusinessException("PICKUPDATE.AND.RETURNDATE.ERROR");
		}
	}

	private double isDiffReturnCityFromPickUpCity(int pickUpCity, int returnCity) {
		if (pickUpCity != returnCity) {
			return 750.0;
		}
		return 0;
	}

	private double calculateTotalPrice(Rental rental, double dailyPrice) {
		double days = rental.getTotalDays();
		double totalDailyPrice =  days * dailyPrice;
		double diffCityPrice =  isDiffReturnCityFromPickUpCity(rental.getPickUpCity().getId(), rental.getReturnCity().getId());
		double totalPrice = totalDailyPrice + diffCityPrice;
		return totalPrice;
	}




	@Override
	public DataResult<ReadRentalResponse> findById(int id) {
		Rental rental = this.rentalRepository.findById(id);
		ReadRentalResponse response = this.modelMapperService.forResponse().map(rental, ReadRentalResponse.class);
		return new SuccessDataResult<ReadRentalResponse>(response);
	}
	
	




	
}

package com.kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.City;
import com.kodlamaio.rentAcar.business.abstracts.CityServices;
import com.kodlamaio.rentAcar.business.request.cities.CreateCityRequest;
import com.kodlamaio.rentAcar.business.request.cities.DeleteCityRequest;
import com.kodlamaio.rentAcar.business.request.cities.UpdateCityRequest;
import com.kodlamaio.rentAcar.business.response.cities.GetAllCitiesResponse;
import com.kodlamaio.rentAcar.business.response.cities.GetCityResponse;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CityRepository;

@Service
public class CityManager implements CityServices {
	
	private CityRepository cityRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CityManager(CityRepository cityRepository, ModelMapperService modelMapperService) {
		super();
		this.cityRepository = cityRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) {
		City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityRepository.save(city);
		return new SuccessResult("CITY.ADDED");
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityRepository.save(city);
		return new SuccessResult("CITY.UPDATED");
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {
		City city = this.modelMapperService.forRequest().map(deleteCityRequest, City.class);
		this.cityRepository.delete(city);
		return new SuccessResult("CITY.DELETED");
	}

	@Override
	public DataResult<GetCityResponse> getById(int id) {
		City city = this.cityRepository.findById(id);
		GetCityResponse response =  this.modelMapperService.forResponse().map(city,GetCityResponse.class);
		return new SuccessDataResult<GetCityResponse>(response, "CITY.GETTED");
	}

	@Override
	public DataResult<List<GetAllCitiesResponse>> getAll() {
		List<City> cities = this.cityRepository.findAll();
		List<GetAllCitiesResponse> response = cities.stream()
				.map(city -> this.modelMapperService.forResponse().map(cities, GetAllCitiesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCitiesResponse>>(response, "CITIES.LISTED");
	}




/*
	@Override
	public City findById(int id) {
		return cityRepository.findById(id).get();
	}*/


}

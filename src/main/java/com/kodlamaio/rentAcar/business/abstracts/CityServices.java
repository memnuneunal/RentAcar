package com.kodlamaio.rentAcar.business.abstracts;


import java.util.List;

import com.kodlamaio.rentAcar.business.request.cities.CreateCityRequest;
import com.kodlamaio.rentAcar.business.request.cities.DeleteCityRequest;
import com.kodlamaio.rentAcar.business.request.cities.UpdateCityRequest;
import com.kodlamaio.rentAcar.business.response.cities.GetAllCitiesResponse;
import com.kodlamaio.rentAcar.business.response.cities.GetCityResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface CityServices<City> {


	Result add(CreateCityRequest createCityRequest);
	Result update(UpdateCityRequest updateCityRequest);
	Result delete(DeleteCityRequest deleteCityRequest);
	DataResult<GetCityResponse> getById(int id);
	DataResult<List<GetAllCitiesResponse>> getAll();

}

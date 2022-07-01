package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.Entities.concretes.IndividualCustomer;
import com.kodlamaio.rentAcar.business.request.individualCustomer.CreateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.business.request.individualCustomer.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentAcar.business.request.individualCustomer.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.business.response.indivualCustomer.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentAcar.business.response.indivualCustomer.ReadIndividualCustomerResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface IndividualCustomerService {
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);

	DataResult<ReadIndividualCustomerResponse> getById(int id);

	DataResult<List<GetAllIndividualCustomersResponse>> getAll();

	public IndividualCustomer getByIndividualCustomerId(int id);

}

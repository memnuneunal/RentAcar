package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.Entities.concretes.CorporateCustomer;
import com.kodlamaio.rentAcar.business.request.corporateCustomer.CreateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.business.request.corporateCustomer.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentAcar.business.request.corporateCustomer.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.kodlamaio.rentAcar.business.response.corporateCustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface CorporateCustomerService {
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);

	DataResult<ReadCorporateCustomerResponse> getById(int id);

	DataResult<List<GetAllCorporateCustomerResponse>> getAll();

	public CorporateCustomer getByCorporateCustomerId(int id);

}

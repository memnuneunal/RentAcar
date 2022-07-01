package com.kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.CorporateCustomer;
import com.kodlamaio.rentAcar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentAcar.business.request.corporateCustomer.CreateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.business.request.corporateCustomer.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentAcar.business.request.corporateCustomer.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.kodlamaio.rentAcar.business.response.corporateCustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentAcar.core.Utilies.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CorporateCustomerRepository;
@Service
public class CorporateCustomerManager implements CorporateCustomerService{
	@Autowired
	private CorporateCustomerRepository corporateCustomerRepository;
	@Autowired
	private ModelMapperService modelMapperService;

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		checkIfCorporateCustomerExistsById(updateCorporateCustomerRequest.getCorporateCustomerId());
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		checkIfCorporateCustomerExistsById(deleteCorporateCustomerRequest.getCorporateCustomerId());
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(deleteCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.delete(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.DELETED");
	}

	@Override
	public DataResult<ReadCorporateCustomerResponse> getById(int id) {
		CorporateCustomer corporateCustomer = checkIfCorporateCustomerExistsById(id);
		ReadCorporateCustomerResponse readCorporateCustomerResponse = this.modelMapperService.forResponse()
				.map(corporateCustomer, ReadCorporateCustomerResponse.class);
		return new SuccessDataResult<ReadCorporateCustomerResponse>(readCorporateCustomerResponse);

	}

	@Override
	public DataResult<List<GetAllCorporateCustomerResponse>> getAll() {
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerRepository.findAll();
		List<GetAllCorporateCustomerResponse> response = corporateCustomers.stream()
				.map(corporateCustomer -> this.modelMapperService.forResponse().map(corporateCustomer,
						GetAllCorporateCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCorporateCustomerResponse>>(response);
	}

	@Override
	public CorporateCustomer getByCorporateCustomerId(int id) {
		return checkIfCorporateCustomerExistsById(id);
	}

	private CorporateCustomer checkIfCorporateCustomerExistsById(int id) {
		CorporateCustomer currentCorporateCustomer;
		try {
			currentCorporateCustomer = this.corporateCustomerRepository.findById(id).get();

		} catch (Exception e) {
			throw new BusinessException("CORPORATE.CUSTOMER.NOT.EXISTS");
		}
		return currentCorporateCustomer;

	}


	

}

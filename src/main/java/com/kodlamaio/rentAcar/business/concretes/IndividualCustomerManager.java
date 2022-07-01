package com.kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.IndividualCustomer;
import com.kodlamaio.rentAcar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentAcar.business.abstracts.UserCheckService;
import com.kodlamaio.rentAcar.business.abstracts.UserService;
import com.kodlamaio.rentAcar.business.request.individualCustomer.CreateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.business.request.individualCustomer.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentAcar.business.request.individualCustomer.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.business.response.indivualCustomer.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentAcar.business.response.indivualCustomer.ReadIndividualCustomerResponse;
import com.kodlamaio.rentAcar.core.Utilies.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.IndividualCustomerRepository;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	private IndividualCustomerRepository individualCustomerRepository;
	private ModelMapperService modelMapperService;
	private UserCheckService userCheckService;

	@Autowired
	public IndividualCustomerManager(IndividualCustomerRepository individualCustomerRepository,
			ModelMapperService modelMapperService, UserCheckService userCheckService) {
		this.individualCustomerRepository = individualCustomerRepository;
		this.modelMapperService = modelMapperService;
		this.userCheckService = userCheckService;
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		checkIfIndividualCustomerExistsByNationalityId(createIndividualCustomerRequest.getNationality());
		checkIfIndividualCustomerExitsByEmail(createIndividualCustomerRequest.getEmail());
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(createIndividualCustomerRequest, IndividualCustomer.class);
		checkIfRealIndividualCustomer(individualCustomer);
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		checkIfIndividualCustomerExitsByEmail(updateIndividualCustomerRequest.getEmail());
		checkIfIndividualCustomerExistsById(updateIndividualCustomerRequest.getIndividualCustomerId());
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(updateIndividualCustomerRequest, IndividualCustomer.class);

		setIndividualCustomer(individualCustomer, updateIndividualCustomerRequest.getIndividualCustomerId());

		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		checkIfIndividualCustomerExistsById(deleteIndividualCustomerRequest.getIndividualCustomerId());
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(deleteIndividualCustomerRequest, IndividualCustomer.class);

		this.individualCustomerRepository.delete(individualCustomer);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.DELETED");
	}

	@Override
	public DataResult<ReadIndividualCustomerResponse> getById(int id) {
		IndividualCustomer individualCustomer = checkIfIndividualCustomerExistsById(id);
		ReadIndividualCustomerResponse readIndividualCustomerResponse = this.modelMapperService.forResponse()
				.map(individualCustomer, ReadIndividualCustomerResponse.class);
		return new SuccessDataResult<ReadIndividualCustomerResponse>(readIndividualCustomerResponse);
	}

	@Override
	public DataResult<List<GetAllIndividualCustomersResponse>> getAll() {
		List<IndividualCustomer> individualCustomers = this.individualCustomerRepository.findAll();
		List<GetAllIndividualCustomersResponse> response = individualCustomers.stream()
				.map(individualCustomer -> this.modelMapperService.forResponse().map(individualCustomer,
						GetAllIndividualCustomersResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomersResponse>>(response);
	}

	@Override
	public IndividualCustomer getByIndividualCustomerId(int id) {
		return checkIfIndividualCustomerExistsById(id);
	}

	private IndividualCustomer checkIfIndividualCustomerExistsById(int id) {
		IndividualCustomer currentIndividualCustomer;
		try {
			currentIndividualCustomer = this.individualCustomerRepository.findById(id).get();

		} catch (Exception e) {
			throw new BusinessException("INDIVIDUAL.CUSTOMER.NOT.EXISTS");
		}
		return currentIndividualCustomer;

	}

	private void checkIfIndividualCustomerExistsByNationalityId(String nationality) {
		IndividualCustomer currentNationalityId = this.individualCustomerRepository.findByNationality(nationality);
		if (currentNationalityId != null) {
			throw new BusinessException("INDIVIDUAL.CUSTOMER.EXİST");
		}

	}

	private void checkIfRealIndividualCustomer(IndividualCustomer individualCustomer) {
		if (!this.userCheckService.checkIfRealPerson(individualCustomer)) {

			throw new BusinessException("COULD.NOT.INDIVIDUAL.CUSTOMER.ADDED");
		}
	}

	private void setIndividualCustomer(IndividualCustomer individualCustomer, int id) {
		IndividualCustomer tempIndividualCustomer = this.individualCustomerRepository.findById(id).get();

		individualCustomer.setFirstName(tempIndividualCustomer.getFirstName());
		individualCustomer.setLastName(tempIndividualCustomer.getLastName());
		individualCustomer.setBirthDate(tempIndividualCustomer.getBirthDate());

	}

	private void checkIfIndividualCustomerExitsByEmail(String email) {
		IndividualCustomer individualCustomer = this.individualCustomerRepository.findByEmail(email);
		if (individualCustomer != null) {
			throw new BusinessException("INDIVIDUAL.CUSTOMER.EXITS");
		}
	}

}
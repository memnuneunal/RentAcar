package com.kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.OrderedAdditionalItems;
import com.kodlamaio.rentAcar.Entities.concretes.Rental;
import com.kodlamaio.rentAcar.business.abstracts.AdditionalService;
import com.kodlamaio.rentAcar.business.request.additionalService.CreateAdditionalsRequest;
import com.kodlamaio.rentAcar.business.request.additionalService.UpdateAdditionalsRequest;
import com.kodlamaio.rentAcar.business.response.additional.GetAllAdditionalsResponse;
import com.kodlamaio.rentAcar.business.response.additionalitems.GetAllAdditionalResponse;
import com.kodlamaio.rentAcar.business.response.additionalitems.ReadAdditionalResponse;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AdditionalRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.RentalRepository;
@Service
public class AdditionalManager implements AdditionalService{
	
	private AdditionalRepository additionalRepository;
	private AdditionalItemRepository additionalItemRepository;
	private ModelMapperService modelMapperService;
	private RentalRepository rentalRepository;

	
    @Autowired
    public AdditionalManager(AdditionalRepository additionalRepository,
			AdditionalItemRepository additionalItemRepository, ModelMapperService modelMapperService,
			RentalRepository rentalRepository) {
		super();
		this.additionalRepository = additionalRepository;
		this.additionalItemRepository = additionalItemRepository;
		this.modelMapperService = modelMapperService;
		this.rentalRepository = rentalRepository;
	}

	@Override
	public Result add(CreateAdditionalsRequest createAdditionalRequest) {
        OrderedAdditionalItems additional = this.modelMapperService.forRequest().map(createAdditionalRequest, OrderedAdditionalItems.class);
		//
		int rentalTotalDays = this.rentalRepository.findById(createAdditionalRequest.getRentalId()).getTotalDays();
		additional.setTotalDays(rentalTotalDays);
		
		double additionalItemPrice = this.additionalItemRepository.findById(createAdditionalRequest.getAdditionalItemId()).get().getPrice();
		double totalPrice = calculateTotalPriceAdditional(rentalTotalDays, additionalItemPrice);
		additional.setTotalPrice(totalPrice);
		
		this.additionalRepository.save(additional);
		return new SuccessResult("ADDITIONAL.ADDED");
	}

	

	@Override
	public Result update(UpdateAdditionalsRequest updateAdditionalRequest) {
		OrderedAdditionalItems additional = this.modelMapperService.forRequest()
				.map(updateAdditionalRequest, OrderedAdditionalItems.class);
		
		Rental rental = this.rentalRepository.findById(updateAdditionalRequest.getRentalId());
		int rentalTotalDays = rental.getTotalDays();
		additional.setTotalDays(rentalTotalDays);
		
		double additionalItemPrice = this.additionalItemRepository.findById(updateAdditionalRequest.getAdditionalItemId()).get().getPrice();
		double totalPrice = calculateTotalPriceAdditional(rentalTotalDays, additionalItemPrice);
		additional.setTotalPrice(totalPrice);
		
		this.additionalRepository.save(additional);		
		return new SuccessResult("ADDITIONALSERVICE.UPDATED");
	}

	@Override
	public Result delete(int id) {
		this.additionalRepository.deleteById(id);
		return new SuccessResult("ADDITIONAL.DELETED");
	}

	@Override
	public DataResult<ReadAdditionalResponse>findById(int id) {
        Optional<OrderedAdditionalItems> additional =  this.additionalRepository.findById(id);
		
		ReadAdditionalResponse response = this.modelMapperService.forResponse()
				.map(additional, ReadAdditionalResponse.class);
		return new SuccessDataResult<ReadAdditionalResponse>(response, "GET.ADDITIONALSERVICE");   
	}
	
	@Override
	public DataResult<List<GetAllAdditionalResponse>> getAll() {
		List<OrderedAdditionalItems> getAllAdditional = this.additionalRepository.findAll();
		
		List<GetAllAdditionalsResponse> response = getAllAdditional.stream()
				.map(additionalService->this.modelMapperService.forResponse()
						.map(getAllAdditional, GetAllAdditionalsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult(response,"GET.ALL.ADDITIONALSERVICE");
	}
	
	private double calculateTotalPriceAdditional(int days, double price) {
		return days*price;
	}





	
}

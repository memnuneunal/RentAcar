package com.kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.AdditionalItems;

import com.kodlamaio.rentAcar.business.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentAcar.business.request.additionalItem.CreateAdditionalItemsRequest;
import com.kodlamaio.rentAcar.business.request.additionalItem.UpdateAdditionalItemsRequest;
import com.kodlamaio.rentAcar.business.response.additionalitems.GetAllAdditionalResponse;
import com.kodlamaio.rentAcar.business.response.additionalitems.ReadAdditionalResponse;
import com.kodlamaio.rentAcar.core.Utilies.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AdditionalItemRepository;
@Service
public class OrderedAdditionalItemManager implements OrderedAdditionalItemService{
	
	private AdditionalItemRepository additionalItemRepository;
	private ModelMapperService modelMapperService;
	
	
	
    @Autowired
	public OrderedAdditionalItemManager(AdditionalItemRepository additionalItemRepository,
			ModelMapperService modelMapperService) {
		super();
		this.additionalItemRepository = additionalItemRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalItemsRequest createAdditionalItemRequest) {
		checkIfItemExistsByName(createAdditionalItemRequest.getName());
		AdditionalItems additionalItem = this.modelMapperService.forRequest().map(createAdditionalItemRequest, AdditionalItems.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("ADDITIONALITEM.ADDED");
	}

	@Override
	public Result update(UpdateAdditionalItemsRequest updateAdditionalItemRequest) {
		checkIfItemExistsByName(updateAdditionalItemRequest.getName());
		AdditionalItems additionalItem = this.modelMapperService.forRequest().map(updateAdditionalItemRequest, AdditionalItems.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("ITEM.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.additionalItemRepository.deleteById(id);
		return new SuccessResult("ADDITIONALITEM.DELETED");
	}

	@Override
	public DataResult<ReadAdditionalResponse> getById(int id) {
			AdditionalItems additionalItem = this.additionalItemRepository.findById(id).get();
			
		ReadAdditionalResponse response = this.modelMapperService.forResponse().map(additionalItem, ReadAdditionalResponse.class);
			return new SuccessDataResult<ReadAdditionalResponse>(response);
	}

	@Override
	public DataResult<List<GetAllAdditionalResponse>> getAll() {
		List<AdditionalItems> additionalItems = additionalItemRepository.findAll();
		List<GetAllAdditionalResponse> response = additionalItems.stream()
				.map(additionalServiceItem -> this.modelMapperService.forResponse()
						.map(additionalServiceItem, GetAllAdditionalResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalResponse>>(response);
	}

	private void checkIfItemExistsByName(String name) {
		AdditionalItems additionalItem = this.additionalItemRepository.findByName(name);
		if (additionalItem != null) {
			throw new BusinessException("ADDITIONALITEM.EXISTS");
		}
	}


	
}

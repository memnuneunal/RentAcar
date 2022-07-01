package com.kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.Color;
import com.kodlamaio.rentAcar.business.abstracts.ColorServices;
import com.kodlamaio.rentAcar.business.request.colors.CreateColorRequest;
import com.kodlamaio.rentAcar.business.request.colors.UpdateColorRequest;
import com.kodlamaio.rentAcar.business.response.color.GetAllColorResponse;
import com.kodlamaio.rentAcar.business.response.color.ReadColorResponse;
import com.kodlamaio.rentAcar.core.Utilies.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.ColorRepository;
@Service
public class ColorManager implements ColorServices{

	
	private ColorRepository colorRepository;
	
	private ModelMapperService modelMapperService;
	

	@Autowired
	public ColorManager(ColorRepository colorRepository,ModelMapperService modelMapperService) {
		this.colorRepository = colorRepository;
		this.modelMapperService=modelMapperService;
	}


	@Override
	public Result add(CreateColorRequest createColorRequest) {
		checkIfColorExistsByName(createColorRequest.getName());
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.ADDED");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.UPDATED");
	}

	@Override
	public Result delete(int id) {
		this.colorRepository.deleteById(id);
		return new SuccessResult("COLOR.DELETED") ;
	}

	@Override
	public DataResult<ReadColorResponse> findById(int id) {
		Optional<Color> color = this.colorRepository.findById(id);
		ReadColorResponse response  = this.modelMapperService.forResponse().map(color, ReadColorResponse.class);
		return new SuccessDataResult<ReadColorResponse>(response);
	}

	@Override
	public DataResult <List<GetAllColorResponse>> getAll() {
		List<Color> colors = this.colorRepository.findAll();
		
		List<GetAllColorResponse> response = colors.stream()
				.map(color -> modelMapperService.forResponse().map(color, GetAllColorResponse.class))
				.collect(Collectors.toList());;
		
		return new SuccessDataResult<List<GetAllColorResponse>>(response);
	}

	private void checkIfColorExistsByName(String name) {
		Color Color = this.colorRepository.findByName(name);
		if (Color != null) 
			throw new BusinessException("COLOR.EXISTS");
		}




	
}
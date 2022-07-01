package com.kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.Brand;
import com.kodlamaio.rentAcar.business.abstracts.BrandService;
import com.kodlamaio.rentAcar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentAcar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentAcar.business.response.brand.GetAllBrandsResponse;
import com.kodlamaio.rentAcar.business.response.brand.GetBrandResponse;
import com.kodlamaio.rentAcar.core.Utilies.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.BrandRepository;

@Service
public class BrandManager implements BrandService{
	
	
	
	private BrandRepository brandRepository;
	
	//ModelMapper
	private ModelMapperService modelMapperService;
    @Autowired
	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {

		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}
	

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		// mapping
		checkIfBrandExistByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");

	}
	
	
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		checkIfBrandExistsById(updateBrandRequest.getId());
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.UPDATED");



	}

	@Override
	public Result deleteById(int id) {
		this.brandRepository.deleteById(id);
		return new SuccessResult("BRAND.DELETED");

	}


	/*@Overrideupdate güncelle!!!
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand colorToUpdate = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		brandRepository.save(colorToUpdate);
		return new SuccessResult();
		
	}*/
	@Override
	public DataResult<List<GetAllBrandsResponse>> getall() {
		List<Brand> brands =this.brandRepository.findAll();
		List<GetAllBrandsResponse> response = brands.stream()
				.map(brand->this.modelMapperService.forRequest()
						.map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());
		brandRepository.findAll();
		return new SuccessDataResult<List<GetAllBrandsResponse>>();
	}

	@Override
	public DataResult<List<GetBrandResponse>> getById(int id) {
		/*Brand brand = new Brand();
		for (Brand item : brandRepository.findAll()) {
			if (item.getId() == id) {
				brand = item;
			}
		}
		return brand;*/
		List<Brand> brands = this.brandRepository.findAll();
		List<GetBrandResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse()
						.map(brand, GetBrandResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetBrandResponse>>(response);
		
		/*this.modelMapperService.forResponse()
		.map(brand, getall().getClass()*/

	}
	
	private void checkIfBrandExistByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand != null) {
			throw new BusinessException("BRAND.EXIST");
		}
	}
	
	private Brand checkIfBrandExistsById(int id) {
		Brand currentBrand;
		try {
			currentBrand = this.brandRepository.findById(id);

		} catch (Exception e) {
			throw new BusinessException("BRAND.NOT.EXISTS");
		}
		return currentBrand;

	}


}
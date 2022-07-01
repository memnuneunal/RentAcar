package com.kodlamaio.rentAcar.core.Utilies.mapping;


import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forResponse();
	ModelMapper forRequest();

}

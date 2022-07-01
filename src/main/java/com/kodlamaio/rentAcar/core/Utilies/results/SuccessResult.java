package com.kodlamaio.rentAcar.core.Utilies.results;

public class SuccessResult extends Result{
	public SuccessResult() {
		super(true);
	} 
	
	public SuccessResult(String message) {
		super(true,message);
	} 
}
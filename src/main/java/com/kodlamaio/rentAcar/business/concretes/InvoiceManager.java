package com.kodlamaio.rentAcar.business.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.OrderedAdditionalItems;
import com.kodlamaio.rentAcar.Entities.concretes.AdditionalItems;
import com.kodlamaio.rentAcar.Entities.concretes.Invoice;
import com.kodlamaio.rentAcar.Entities.concretes.Rental;
import com.kodlamaio.rentAcar.business.abstracts.InvoiceService;
import com.kodlamaio.rentAcar.business.request.invoice.CreateInvoicesRequest;
import com.kodlamaio.rentAcar.business.request.invoice.UpdateInvoicesRequest;
import com.kodlamaio.rentAcar.business.response.invoice.GetAllInvoiceResponse;
import com.kodlamaio.rentAcar.business.response.invoice.ReadInvoiceResponse;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AdditionalRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.RentalRepository;
@Service
public class InvoiceManager implements InvoiceService{
	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;
	private AdditionalRepository additionalRepository;
	private RentalRepository rentalRepository;
	private AdditionalItemRepository additionalItemRepository;
	

	@Autowired
	public InvoiceManager(InvoiceRepository invoiceRepository, ModelMapperService modelMapperService,
			AdditionalRepository additionalRepository, RentalRepository rentalRepository,
			AdditionalItemRepository additionalItemRepository) {
		super();
		this.invoiceRepository = invoiceRepository;
		this.modelMapperService = modelMapperService;
		this.additionalRepository = additionalRepository;
		this.rentalRepository = rentalRepository;
		this.additionalItemRepository = additionalItemRepository;
	}

	@Override
	public Result add(CreateInvoicesRequest createInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setCurrentDate(LocalDate.now());
		invoice.setTotalPrice(calculateTotalPrice(createInvoiceRequest.getRentalId()));
		
		this.invoiceRepository.save(invoice);
		
		return new SuccessResult("INVOICE.ADDED");
	}


	@Override
	public Result delete(int id) {
		this.invoiceRepository.deleteById(id);
		return new SuccessResult("INVOICE.DELETED");
	}

	@Override
	public Result update(UpdateInvoicesRequest updateInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.UPDATED");
	}

	@Override
	public DataResult<ReadInvoiceResponse> getById(int id) {
		Invoice invoice = this.invoiceRepository.findById(id).get();
		ReadInvoiceResponse response = this.modelMapperService.forResponse().map(invoice, ReadInvoiceResponse.class);
		return new SuccessDataResult<ReadInvoiceResponse>(response);
	}

	@Override
	public DataResult<List<GetAllInvoiceResponse>> getAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
		List<GetAllInvoiceResponse> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse()
				.map(invoice, GetAllInvoiceResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult(response);
	
	}

	@Override
	public DataResult<List<AdditionalItems>> getAllAdditionalItems(int rentalId) {
		List<OrderedAdditionalItems> additionals = this.additionalRepository.findByRentalId(rentalId);
		List<AdditionalItems> additionalItems = new ArrayList<AdditionalItems>();
		
		for (OrderedAdditionalItems additional : additionals) {
			AdditionalItems additionalItem = this.additionalItemRepository.findById(additional.getAdditionalItem().getId()).get();
			additionalItems.add(additionalItem);
		}
		return new SuccessDataResult(additionalItems);
	}

	
	private double calculateTotalPrice(int rentalId) {
		Rental rental = this.rentalRepository.findById(rentalId);
		double totalPrice = rental.getTotalPrice() + allRentalAdditionalTotalPrice(rentalId);
		return totalPrice;
	}
	
	private double allRentalAdditionalTotalPrice(int id) {
		double totalAdditional = 0;
		List<OrderedAdditionalItems> additional = this.additionalRepository.findByRentalId(id);
		for (OrderedAdditionalItems additionals : additional) {
			totalAdditional += additionals.getTotalPrice();
		}
		return totalAdditional;
	}
}

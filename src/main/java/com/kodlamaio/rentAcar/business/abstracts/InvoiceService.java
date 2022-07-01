package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.Entities.concretes.AdditionalItems;
import com.kodlamaio.rentAcar.business.request.invoice.CreateInvoicesRequest;
import com.kodlamaio.rentAcar.business.request.invoice.UpdateInvoicesRequest;
import com.kodlamaio.rentAcar.business.response.invoice.GetAllInvoiceResponse;
import com.kodlamaio.rentAcar.business.response.invoice.ReadInvoiceResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface InvoiceService {

	Result delete(int id);

	Result update(UpdateInvoicesRequest updateInvoiceRequest);

	DataResult<List<AdditionalItems>> getAllAdditionalItems(int rentalId);

	Result add(CreateInvoicesRequest createInvoiceRequest);

	DataResult<List<GetAllInvoiceResponse>> getAll();

	DataResult<ReadInvoiceResponse> getById(int id);

}

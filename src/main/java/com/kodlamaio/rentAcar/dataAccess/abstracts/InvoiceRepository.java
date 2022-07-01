package com.kodlamaio.rentAcar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.Entities.concretes.AdditionalItems;
import com.kodlamaio.rentAcar.Entities.concretes.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	List<AdditionalItems> getByRentalId(int id);
}

package com.kodlamaio.rentAcar.Entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="additional_services")
public class OrderedAdditionalItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name ="total_days")
	private int totalDays;
	
	@Column(name = "total_price")
	private double totalPrice;
	@Column(name="pickup_date")
	private LocalDate pickupDate;
	
	@Column(name="return_date")
	private LocalDate returnDate;
	
	@ManyToOne
	@JoinColumn(name = "additional_item_id")
	private AdditionalItems additionalItem;


	@ManyToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;


}	
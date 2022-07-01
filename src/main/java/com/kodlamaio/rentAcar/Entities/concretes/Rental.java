package com.kodlamaio.rentAcar.Entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rentacar")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	//Column(name="car_id")
	//private int car_id;
	@Column(name="pickupDate")
	private LocalDate pickupDate;
	@Column(name="returnedDate")
	private LocalDate returnedDate;
	@Column(name="totalDays")
	private int totalDays;
	@Column(name="totalPrice")
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	
	@ManyToOne
	@JoinColumn(name="pickup_city_id")
	private City pickUpCity;
	
	@ManyToOne
	@JoinColumn(name="return_city_id")
	private City returnCity;
	
	
	 @OneToMany(mappedBy ="rental")
		List<Invoice> invoices;
	 
}

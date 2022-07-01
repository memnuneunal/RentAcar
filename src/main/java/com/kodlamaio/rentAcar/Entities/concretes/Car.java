package com.kodlamaio.rentAcar.Entities.concretes;

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
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="description")
	private String description;
	@Column(name="dailyPrice")
	private Double dailyPrice;
	@Column(name="kilometer")
	private Double kilometer;
	@Column(name="carPlate")
	private String carPlate;
	@Column(name = "state")
	private int state;
	@Column(name = "carScore")
	private int carScore;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;
	
	@OneToMany(mappedBy = "car")
	private List<Maintenance> carMaintenances;
	@OneToMany(mappedBy = "car")
	List<Rental> rentals;





	
}
///arabalar bakıma gönderilebilmeli
//arabalara kilometre ve plaka bilgisi eklenecek

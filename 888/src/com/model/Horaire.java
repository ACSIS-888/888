package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Horaire {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdHoraire")
	private int idHoraire;
	@Column(name="Lundi")
	private String lundi;
	@Column(name="Mardi")
	private String mardi;
	@Column(name="Mercredi")
	private String mercredi;
	@Column(name="Jeudi")
	private String jeudi;
	@Column(name="Vendredi")
	private String vendredi;
	@Column(name="Samedi")
	private String samedi;
	@Column(name="Dimanche")
	private String dimanche;
	@OneToOne()
	@Column(name="Commerce",nullable=false)
	private Commerce commerce;
}

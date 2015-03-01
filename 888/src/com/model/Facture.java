package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Facture {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdFacture")
	private int idFacture;
	@Column(name="Date", nullable=false)
	private Date date;
	@Column(name="Prix", nullable=false)
	private double prix;
	@Column(name="ZoneAffichage", nullable=false)
	private String zoneAffichage;
	@Column(name="Duree", nullable=false)
	private int duree;	//combien de jour.
	@OneToOne(mappedBy = "facture", fetch=FetchType.LAZY)
	@Column(name="Publicite", nullable=false)
	private Publicite publicite;
}

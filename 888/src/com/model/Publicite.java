package com.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Publicite {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdPub")
	private int idPub;
	@Column(name="Duree",nullable=false)
	private int duree;	//combine de jour.
	@Column(name="Tarif",nullable=false)
	private double tarif;
	@Column(name="Description",nullable=false)
	private String description;
	@Column(name="ZoneAffichage",nullable=false)
	private String zoneAffichage;
	@Column(name="Date",nullable=false)
	private Date date;
	@Column(name="Valide",nullable=false)
	private boolean valide = false;	//par defaut false
	@ManyToOne
	@Column(name="Commerce",nullable=false)
	private Commerce commerce;
	@OneToOne(cascade=CascadeType.ALL)
	private Facture facture;
}

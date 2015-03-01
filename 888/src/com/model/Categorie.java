package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Categorie {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdCategorie")
	private int idCategorie;
	@Column(name="Libelle",nullable=false)
	private String libelle;
	@OneToOne()
	private Commerce commerce;
}

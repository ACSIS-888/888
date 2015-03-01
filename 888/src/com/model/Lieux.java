package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Lieux {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdLieux")
	private int idLieux;
	@Column(name="Adresse",nullable=false)
	private String adresse;
	@Column(name="Ville",nullable=false)
	private String ville;
	@Column(name="CodePostale",nullable=false)
	private int codePostale;
	@Column(name="Departement",nullable=false)
	private String departement;
	@OneToOne(mappedBy = "lieux", fetch=FetchType.LAZY)
	@Column(name="Commerce",nullable=false)
	private Commerce commerces;
}

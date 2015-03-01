package com.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Commerce {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdCommerce")
	private int idCommerce;
	@Column(name="Nom",nullable=false)
	private String nom;
	@Column(name="Description")
	private String description;
	@Column(name="Photo")
	private String photo;
	@Column(name="DateDemande",nullable=false)
	private Date dateDemande;
	@Column(name="DateValide",nullable=false)
	private Date dateValide;
	@Column(name="NombreVisiteParMois")
	private int nombreVisiteParMois = 0;
	@Column(name="NombreVisite")
	private int nombreVisite = 0;
	@Column(name="Permis",nullable=false)
	private String permis;
	@Column(name="Valide")
	private boolean valide = false;
	@ManyToOne()
	private Inscris prorietaire;
	@OneToOne(mappedBy = "commerce",cascade=CascadeType.ALL)
	private Horaire horaire;
	@OneToOne(mappedBy = "commerce",cascade=CascadeType.ALL)
	private Lieux lieux;
	@ManyToOne
	@Column(name="Categorie",nullable=false)
	private Categorie categorie;
	@OneToMany(mappedBy = "commerce", fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Commentaire> commentaires;
	@OneToMany(mappedBy = "commerce", fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Publicite> publicites;
}

package com.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Commentaire {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdCommentaire")
	private int idCommentaire;
	@Column(name="Description",nullable=false)
	private String description;
	@Column(name="NbEtoile",nullable=false)
	private int nbEtoile;	//moins que 5 
	@Column(name="Photo")
	private String photo;
	@Column(name="Video")
	private String video;
	@ManyToOne
	private Inscris commentateur;
	@ManyToOne
	private Commerce commerce;
	@OneToMany(mappedBy = "commentaire",cascade=CascadeType.ALL)
	private Set<Note> notes;
}

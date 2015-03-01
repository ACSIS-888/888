package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// As a joined class between two entities, if we got the
// additional attributes besides the keys of these entities,
// we should create it physically in order to keep all the 
// informations with creating a key.
@Entity
public class Note {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdNote")
	private int idNote;
	@Column(name="Note",nullable=false)
	private boolean note;	//soi Util, soi Inutil
	@ManyToOne
	@Column(name="Inscris",nullable=false)
	private Inscris noteur;
	@ManyToOne
	@Column(name="Commentaire",nullable=false)
	private Commentaire commentaire;
}

package com.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(      name="Type",
 discriminatorType=DiscriminatorType.STRING      )
@DiscriminatorValue("Inscris")
public class Inscris {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdUser")
	private int idUser;
	@Column(name="Nom",nullable=false)
	private String nom;
	@Column(name="Prenom",nullable=false)
	private String prenom;
	@Column(name="Password",nullable=false)
	private String password;
	@Column(name="Mail",nullable=false)
	private String mail;
	@OneToMany(mappedBy = "noteur", fetch=FetchType.LAZY)
	private Set<Note> notes;
	@OneToMany(mappedBy = "commentateur", fetch=FetchType.LAZY)
	private Set<Commentaire> commentaires;
	@OneToMany()
	private Set<Commerce> commerces;
}
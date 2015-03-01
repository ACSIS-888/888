package com.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Professionnel")
public class Professionnel extends Inscris {
	
}

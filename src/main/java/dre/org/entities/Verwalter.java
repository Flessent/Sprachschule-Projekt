package dre.org.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = "VERWALTER")
@JsonTypeName("verwalter")
public class Verwalter extends Personne implements Serializable {

	
	private static final long serialVersionUID = 1L;

}

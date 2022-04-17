package dre.org.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "leiter")
@DiscriminatorValue(value = "LEITER")
@JsonTypeName("leiter")
@NoArgsConstructor

public  class  Leiter extends Lehrer implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "salaire")
	protected BigDecimal salaire;
		  @Column(name = "nbre_mois_experiences")
		  protected Integer nbreMoisExperiences;
		



			public Leiter(String username, String nom, String prenom, Date dateNaissance,String nationalite, String profession, String quartier,
					String numTel, String email, String numCni, String password ,String diplomeEleve,
						Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean isActived,Set<Sprache> sprache,	Set<Niveau> niveau,Integer nbreMoisExperiences,BigDecimal salaire,HashSet<Roles> roles) {
					super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours,activiteEnParalelle,
					isActived,sprache,niveau,nbreMoisExperiences,salaire,roles);
	this.salaire=salaire;
	this.nbreMoisExperiences=nbreMoisExperiences;

				}
	
		  
	
	

}

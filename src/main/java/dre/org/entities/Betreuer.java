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
@DiscriminatorValue(value = "BETREUER")
@Entity(name = "betreuer")
@JsonTypeName("betreuer")

public @Data class Betreuer extends Lehrer implements Serializable{
		private static final long serialVersionUID = 1L;

	@Basic(optional = false)
//@NotNull(message = "Gehalt kann nicht leer sein !")
// @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
	
@Column(name = "salaire")
protected BigDecimal salaire;
 	//@NotNull(message="Erfahrung muss nicht leer sein !")
	  @Column(name = "nbre_mois_experiences")
	  protected Integer nbreMoisExperiences;
	
	
	 




		public Betreuer(String username, String nom, String prenom, Date dateNaissance,String nationalite, String profession, String quartier,
				String numTel, String email, String numCni, String password ,String diplomeEleve,
					Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean isActived,Set<Sprache> sprache,	Set<Niveau> niveau,Integer nbreMoisExperiences,BigDecimal salaire,HashSet<Roles> roles) {
				super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours,activiteEnParalelle,
				isActived,sprache,niveau,nbreMoisExperiences,salaire,roles);
this.salaire=salaire;
this.nbreMoisExperiences=nbreMoisExperiences;

			}
		
		
		
Betreuer(String username,String nom,String prenom,Date dateNaissance,String nationalite,String profession,String quartier,String numTel,String email,String numCni,String password,String diplomeEleve,
				Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean isActived, Set<Sprache> sprache, Set<Niveau> niveau,HashSet<Roles> roles){
			super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours,activiteEnParalelle ,isActived,sprache,niveau,roles);

		}

		
		
		

public Betreuer(){
	
}



public BigDecimal getSalaire() {
	return salaire;
}



public void setSalaire(BigDecimal salaire) {
	this.salaire = salaire;
}




public Integer getNbreMoisExperiences() {
	return nbreMoisExperiences;
}



public void setNbreMoisExperiences(Integer nbreMoisExperiences) {
	this.nbreMoisExperiences = nbreMoisExperiences;
}







}

package dre.org.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue(value = "SEKRETAERE")
@JsonTypeName("sekretaer")
@NoArgsConstructor
@AllArgsConstructor
public  class Sekretaer extends Lehrer implements Serializable{


	private static final long serialVersionUID = 1L;
	@Column(name = "nbre_mois_experiences",nullable = false)
	  protected Integer nbreMoisExperiences;
	@NotNull(message = "Die von dem Sekretär beherrcherten Sprachen sind erforderlich !")
	@Column(name = "langues_sekretaer")
	  protected String languesSekretaer;
		@Basic(optional = false)
	// @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
	 @Column(name = "salaire",nullable = false)
	 protected BigDecimal salaire;
		
		public  Sekretaer(String username, String nom, String prenom, Date dateNaissance,String nationalite, String profession, String quartier,
				String numTel, String email, String numCni, String password ,String diplomeEleve,
				Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean isActived,String languesSekretaer,Integer nbreMoisExperiences,BigDecimal salaire,HashSet<Roles> roles) {
			super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours,activiteEnParalelle, isActived,roles);
		this.languesSekretaer=languesSekretaer;

			this.nbreMoisExperiences = nbreMoisExperiences;
			this.salaire = salaire;		
		}

		public Integer getNbreMoisExperiences() {
			return nbreMoisExperiences;
		}

		public void setNbreMoisExperiences(Integer nbreMoisExperiences) {
			this.nbreMoisExperiences = nbreMoisExperiences;
		}

	

		public BigDecimal getSalaire() {
			return salaire;
		}

		public void setSalaire(BigDecimal salaire) {
			this.salaire = salaire;
		}

		public String getLanguesSekretaer() {
			return languesSekretaer;
		}

		public void setLanguesSekretaer(String languesSekretaer) {
			this.languesSekretaer = languesSekretaer;
		}
	

}

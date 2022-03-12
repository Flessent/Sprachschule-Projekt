package dre.org.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@DiscriminatorValue(value = "STUDENT")
@JsonTypeName("student")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "username",
        resolver = EntityIdResolver.class,
        scope=Student.class)
public  class Student  extends Betreuer implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	
	
		@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
	 @Column(name = "num_tel_pere_ou_tuteur")
	 protected String numTelPereOuTuteur;
		
		@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
	 @Column(name = "num_tel_mere_ou_tutrice")
	 protected String numTelMereOuTutrice;
		
		@Basic(optional = false)
		@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
		 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
		 @Column(name = "nom_pere_ou_tuteur")
		 protected String nomPereOuTuteur;
		@Basic(optional = false)
		@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
		 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
		 @Column(name = "nom_mere_ou_tutrice")
		 protected String nomMereOuTutrice;
		
		@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
	 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
	 @Column(name = "nom_financeur_cours")
	 protected String nomFinanceurCours;
		
		@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
	 @Column(name = "num_tel_financeur_cours")
	 protected String numTelFinanceurCours;
		

		public Student(){
			
		}
        
		
		
	   //private Sprache sprache;
	
		public Student(String username, String nom, String prenom, Date dateNaissance,String nationalite, String profession, String quartier,
				String numTel, String email, String numCni, String password ,String diplomeEleve,String numTelPereOuTuteur,
				String nomPereOuTuteur,String numTelMereOuTutrice,String nomMereOuTutrice,String numTelFinanceurCours,String nomFinanceurCours, String niveauLangue,
				Date dateDebutActiviteOuCours, String activiteEnParalelle, Set<Sprache> sprache, Set<Niveau> niveau,boolean isActived,HashSet<Roles> roles) {
			super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours,activiteEnParalelle
					, isActived, sprache, niveau,roles);
		
		
		 }

	

	


		public String getNomPereOuTuteur() {
			return nomPereOuTuteur;
		}



		public void setNomPereOuTuteur(String nomPereOuTuteur) {
			this.nomPereOuTuteur = nomPereOuTuteur;
		}



		public String getNomMereOuTutrice() {
			return nomMereOuTutrice;
		}



		public void setNomMereOuTutrice(String nomMereOuTutrice) {
			this.nomMereOuTutrice = nomMereOuTutrice;
		}



		public String getNomFinanceurCours() {
			return nomFinanceurCours;
		}



		public void setNomFinanceurCours(String nomFinanceurCours) {
			this.nomFinanceurCours = nomFinanceurCours;
		}



	


		public String getNumTelPereOuTuteur() {
			return numTelPereOuTuteur;
		}



		public void setNumTelPereOuTuteur(String numTelPereOuTuteur) {
			this.numTelPereOuTuteur = numTelPereOuTuteur;
		}



		public String getNumTelMereOuTutrice() {
			return numTelMereOuTutrice;
		}



		public void setNumTelMereOuTutrice(String numTelMereOuTutrice) {
			this.numTelMereOuTutrice = numTelMereOuTutrice;
		}



		public String getNumTelFinanceurCours() {
			return numTelFinanceurCours;
		}



		public void setNumTelFinanceurCours(String numTelFinanceurCours) {
			this.numTelFinanceurCours = numTelFinanceurCours;
		}



	


		


}

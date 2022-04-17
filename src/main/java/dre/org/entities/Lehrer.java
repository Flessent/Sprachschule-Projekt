package dre.org.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonTypeName;
@DiscriminatorValue(value = "LEHRER")
@Entity(name = "lehrer")
@JsonTypeName("lehrer")

public class Lehrer extends Personne implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name = "nbre_mois_experiences",nullable = false)
	  protected Integer nbreMoisExperiences;
		@Basic(optional = false)
	// @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
	 @Column(name = "salaire",nullable = false)
	 protected BigDecimal salaire;
	  	

		 @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
		    @JoinTable(
		        name = "sprachelehrer", //nom de la table de jointure en bd
		        joinColumns = { @JoinColumn(name = "username_personne") }, 
		        inverseJoinColumns = { @JoinColumn(name = "code_sprache") }
		    )
		    private Set<Sprache> sprache = new HashSet<>();

		 @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
		    @JoinTable(
		        name = "niveaulehrer", //nom de la table de jointure en bd
		        joinColumns = { @JoinColumn(name = "username_personne") }, 
		        inverseJoinColumns = { @JoinColumn(name = "code_niveau") }
		    )
		    private Set<Niveau> niveau = new HashSet<>();

			public Lehrer(String username, String nom, String prenom, Date dateNaissance,String nationalite, String profession, String quartier,
					String numTel, String email, String numCni, String password ,String diplomeEleve,
					Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean isActived,Set<Sprache> sprache,	Set<Niveau> niveau,Integer nbreMoisExperiences,BigDecimal salaire, HashSet<Roles> roles) {
				super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours,activiteEnParalelle, isActived,roles);
				this.sprache = sprache;
				this.niveau = niveau;

				this.nbreMoisExperiences = nbreMoisExperiences;
				this.salaire = salaire;
			
			}
			public Lehrer( java.util.Date heute2, String activiteEnParalelle, boolean isActived,
					HashSet<Sprache> sprache2, HashSet<Niveau> niveau2, int nbreMoisExperiences2, BigDecimal salaire2,
					HashSet<Roles> roles) {
				// TODO Auto-generated constructor stub
			}

			
	
			
			
	
			public Lehrer(){
				
			}
			
			//für Konstuktor der Student
			Lehrer(String username,String nom,String prenom,Date dateNaissance,String nationalite,String profession,String quartier,String numTel,String email,String numCni,String password,String diplomeEleve,
					Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean isActived,HashSet<Roles> roles){
				super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours,activiteEnParalelle, isActived,roles);

			}

			Lehrer(String username,String nom,String prenom,Date dateNaissance,String nationalite,String profession,String quartier,String numTel,String email,String numCni,String password,String diplomeEleve,
					Date dateDebutActiviteOuCours,String activiteEnParalelle
					,boolean isActived,Set<Sprache> sprache, Set<Niveau> niveau,HashSet<Roles> roles){
				super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours,activiteEnParalelle, isActived,roles);
				this.sprache=sprache;
				this.niveau=niveau;

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

			public void setSprache(Set<Sprache> sprachen) {
				
				this.sprache = new HashSet<>(sprachen);
			}
			public List<Sprache> getSprache() {
				return new ArrayList<Sprache>(this.sprache);
			}


			public List<Niveau> getNiveau() {
				return new ArrayList<Niveau>(this.niveau);
			}


			public void setNiveau(Set<Niveau> niveau) {
				this.niveau = new HashSet<>(niveau);
			}






			
			
			
			
			
			
			
			
			
			
			
}

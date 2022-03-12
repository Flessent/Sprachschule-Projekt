package dre.org.entities;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
	   @JsonSubTypes.Type(value = Student.class, name = "student"),
	    @JsonSubTypes.Type(value = Sekretaer.class, name = "sekretaer"),
	    @JsonSubTypes.Type(value = Lehrer.class, name = "lehrer"),
	    @JsonSubTypes.Type(value = Verwalter.class, name = "verwalter"),
	    @JsonSubTypes.Type(value = Betreuer.class, name = "betreuer")
})

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeCompte",discriminatorType = DiscriminatorType.STRING)
@Table(name = "personne")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "username",
        resolver = EntityIdResolver.class,
        scope=Personne.class)
public abstract class Personne implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	
	
	

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "personne_roles", //nom de la table de jointure en bd
        joinColumns = { @JoinColumn(name = "username") }, 
        inverseJoinColumns = { @JoinColumn(name = "code_role") }
    )
    private Set<Roles> roles = new HashSet<>();

	
	
	@Id
	@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
	 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
	 @Column(name = "username")
	 protected String username;
	
	
	@Basic(optional = false)
    @Column(name = "date_inscription")
    protected Timestamp dateInscription;
	

	@Basic(optional = false)
    @Column(name = "profil_bild")
    protected String  profilBild;

	@Basic(optional = false)
    @Column(name = "last_date_Login")
    protected Timestamp lastDateLogin;

	@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")  
	@Column(name = "account_activated")
    protected boolean accountActivated;
	
	

	@Basic(optional = false)
	 @Null(message = "ce champ est n'est pas obligatoire !")
 //@Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
 @Column(name = "village",nullable = true)
 protected String village;
	@Basic(optional = false)
	 @Null(message = "ce champ est n'est pas obligatoire !")
// @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
 @Column(name = "salaire",nullable = true)
 protected BigDecimal salaire;

	
	@Basic(optional = false)
@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
 @Column(name = "nom")
 protected String nom;

  @Basic(optional = false)
  @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
  @NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
   @Column(name = "activite_en_paralelle")
   protected String activiteEnParalelle;
  
  @Basic(optional = false)
  @NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
   @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
   @Column(name = "nationalite")
   protected String nationalite;
  
  
  
	
	@Basic(optional = false)
// @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
 @Column(name = "date_debut_activite_ou_cours",columnDefinition = "DATE DEFAULT CURRENT_DATE")
 protected Date dateDebutActiviteOuCours;
	
	
	

	
	
	@Basic(optional = false)
 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
 @Column(name = "prenom",nullable = true)
 protected String prenom;
	
	@Basic(optional = false)
    @NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
    @Column(name = "date_naissance")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateNaissance;
	
	@Basic(optional = false)
@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
 @Column(name = "profession")
 protected String profession;
	
	@Basic(optional = false)
@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
 @Column(name = "quartier",nullable = true)
 protected String quartier;
	
	@Basic(optional = false)
@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
 @Column(name = "num_tel")
 protected String numTel;
	
	@Basic(optional = false)
@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
 @Column(name = "email")
 protected String email;
	
	@Basic(optional = false)
 @Column(name = "num_cni",nullable = true)
 protected String numCni;
	
	
	
	@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
	 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
	 @Column(name = "password")
	 protected String password;
	
	@Basic(optional = false)
	 @Column(name = "is_online")
	 protected boolean isOnline;

	public boolean isOnline() {
		return isOnline;
	}



	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}


	@Basic(optional = false)
	 @NotNull(message = "ce champ est  obligatoire !")
	 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
	 @Column(name = "diplome_eleve")
	 protected String diplomeEleve;
	
	
	

	
	public Personne(String username,String password) {
		this.username=username;
		this.password=password;
	}
	

	
	public Personne() {	}
	public Personne(String username, String nom, String prenom, Date dateNaissance, String nationalite, String profession, String quartier,
			String numTel, String email, String numCni, String password, String diplomeEleve,Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean isActived, HashSet<Roles> roles) {
		//super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.nationalite=nationalite;
		this.profession = profession;
		this.quartier = quartier;
		this.numTel = numTel;
		this.email = email;
		this.numCni = numCni;
		this.password = password;
		this.diplomeEleve = diplomeEleve;
		this.dateDebutActiviteOuCours=dateDebutActiviteOuCours;
		this.activiteEnParalelle=activiteEnParalelle;
		this.accountActivated=isActived;
		this.roles=roles;
		
	}
	public Personne( String nom, String prenom, Date dateNaissance, String nationalite, String profession, String quartier,
			String numTel, String email, String numCni, String password, String diplomeEleve,Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean isActived) {
		//super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.nationalite=nationalite;
		this.profession = profession;
		this.quartier = quartier;
		this.numTel = numTel;
		this.email = email;
		this.numCni = numCni;
		this.password = password;
		this.diplomeEleve = diplomeEleve;
		this.dateDebutActiviteOuCours=dateDebutActiviteOuCours;
		this.activiteEnParalelle=activiteEnParalelle;
		this.accountActivated=isActived;
	}

	
	public List<Roles> getRoles() {
		return new  ArrayList<Roles>(this.roles);
	}
	public void setRoles(Set<Roles> roles) {
		this.roles = new HashSet<>(roles);
	}
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Date getDateInscription() {
		return dateInscription;
	}


	


	public String getProfilBild() {
		return profilBild;
	}


	public void setProfilBild(String profilBild) {
		this.profilBild = profilBild;
	}






	public boolean isAccountActivated() {
		return accountActivated;
	}



	public void setAccountActivated(boolean accountActivated) {
		this.accountActivated = accountActivated;
	}



	public void setDateInscription(Timestamp dateInscription) {
		this.dateInscription = dateInscription;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Date getDateDebutActiviteOuCours() {
		return dateDebutActiviteOuCours;
	}


	public void setDateDebutActiviteOuCours(Date dateDebutActiviteOuCours) {
		this.dateDebutActiviteOuCours = dateDebutActiviteOuCours;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Date getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
	}


	public String getQuartier() {
		return quartier;
	}


	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}




	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}




	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDiplomeEleve() {
		return diplomeEleve;
	}


	public void setDiplomeEleve(String diplomeEleve) {
		this.diplomeEleve = diplomeEleve;
	}
	
	



	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public BigDecimal getSalaire() {
		return salaire;
	}
	public void setSalaire(BigDecimal salaire) {
		this.salaire = salaire;
	
	}

	public String getActiviteEnParalelle() {
		return activiteEnParalelle;
	}
	public void setActiviteEnParalelle(String activiteEnParalelle) {
		this.activiteEnParalelle = activiteEnParalelle;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	


	 public String getNumTel() {
		return numTel;
	}



	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}



	public String getNumCni() {
		return numCni;
	}



	public void setNumCni(String numCni) {
		this.numCni = numCni;
	}



	@Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (username != null ? username.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof Personne)) {
	            return false;
	        }
	        Personne other = (Personne) object;
	        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
	            return false;
	        }
	        return true;
	    }



		@Override
		public String toString() {
			return "Personne [roles=" + roles + ", username=" + username + ", dateInscription=" + dateInscription
					+ ", profilBild=" + profilBild + ", lastDateLogin=" + lastDateLogin + ", isActived=" + accountActivated
					+ ", village=" + village + ", salaire=" + salaire + ", nom=" + nom + ", nbreMoisExperiences="
					+  ", activiteEnParalelle=" + activiteEnParalelle + ", nationalite="
					+ nationalite + ", dateDebutActiviteOuCours=" + dateDebutActiviteOuCours + ", prenom=" + prenom
					+ ", dateNaissance=" + dateNaissance + ", profession=" + profession + ", quartier=" + quartier
					+ ", numTel=" + numTel + ", email=" + email + ", numCni=" + numCni + ", password=" + password
					+ ", isOnline=" + isOnline + ", diplomeEleve=" + diplomeEleve + "]";
		}



	


	

	 
	
	

	
	
	
	
	
	
}

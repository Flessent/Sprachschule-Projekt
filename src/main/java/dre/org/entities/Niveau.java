package dre.org.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "niveau")
@JsonIdentityInfo(scope = Niveau.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "codeNiveau")

public class Niveau implements Serializable {


	private static final long serialVersionUID = 1L;
	//@JsonBackReference


	@ManyToMany(fetch = FetchType.EAGER,mappedBy = "niveau")//tableau se trouvant dans niveau
    private Set<Lehrer> lehrer = new HashSet<>();
  
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "niveau")//tableau se trouvant dans sprache
    private Set<Kurs> kurs = new HashSet<>(); 
    	

	 @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinTable(
	        name = "sprachniveau", //nom de la table de jointure en bd
	        joinColumns = { @JoinColumn(name = "code_niveau") }, 
	        inverseJoinColumns = { @JoinColumn(name = "code_sprache") }
	    )
	    private Set<Sprache> sprache = new HashSet<>();

	
	@Valid
	@Id
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@GeneratedValue(generator = "uuid1Niveau")
    @GenericGenerator(name = "uuid1Niveau", strategy = "org.hibernate.id.UUIDGenerator")
	 @Column(name = "code_niveau")
	 private UUID codeNiveau;	
	@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
	 @Size(min =2, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
	 @Column(name = "libelle")
	 private String libelle;

	@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
	 @Column(name = "prix")
	private BigDecimal prix;

	@Basic(optional = false)
	 @Column(name = "date_debut")
	private Date date_debut;
	@Basic(optional = false)
	 @Column(name = "date_fin")
	private Date date_fin;
public Niveau() {
	
}
public Niveau(String libelle) {
	this.libelle=libelle;
}

public Niveau(UUID codeNiveau) {
	this.codeNiveau=codeNiveau;
	
}
	public Niveau(String libelle, BigDecimal prix, Date date_debut, Date date_fin, HashSet<Sprache> sprache) {
		this.libelle=libelle;
		this.prix=prix;
		this.date_debut=date_debut;
		this.date_fin=date_fin;
		this.sprache=sprache;
	}
	public UUID getCodeNiveau() {
		return codeNiveau;
	}
	public void setCodeNiveau(UUID codeNiveau) {
		this.codeNiveau = codeNiveau;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public BigDecimal getPrix() {
		return prix;
	}
	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	/*
	public void addSprache(Sprache sprache) {
		this.sprache.add(sprache);
	}*/
	
	
	

	public Set<Kurs> getKurs() {
		return kurs;
	}

	public void setKurs(Set<Kurs> kurs) {
		this.kurs = kurs;
	}

	public void setSprache(Set<Sprache> sprachen) {
		
		this.sprache = new HashSet<>(sprachen);
	}
	public List<Sprache> getSprache() {
		return new ArrayList<Sprache>(this.sprache);
	}

	public Set<Lehrer> getLehrer() {
		return lehrer;
	}

	public void setLehrer(Set<Lehrer> lehrer) {
		this.lehrer = lehrer;
	}


	@Override
	public String toString() {
		return "Niveau [codeNiveau=" + codeNiveau + "]";
	}

	

	



	

	
	
	
	
	
}

package dre.org.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;





@Entity
@Table(name = "sprache")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "codeSprache",
        resolver = EntityIdResolver.class,
        scope=Sprache.class)


public class Sprache  implements Serializable{


	private static final long serialVersionUID = 1L;
	//@JsonBackReference

	@OneToMany (fetch = FetchType.EAGER,mappedBy = "langue")
	private Set<Kurs> kurs;
	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy = "sprache")//tableau se trouvant dans niveau
    private Set<Niveau> niveau = new HashSet<>();
    	

	   
	@ManyToMany(fetch = FetchType.EAGER,mappedBy = "sprache")//tableau se trouvant dans niveau
    private Set<Lehrer> lehrer = new HashSet<>();
    	
	
	
	
	@Valid
	@Id
	@Basic(optional = false)
	@GeneratedValue(generator = "uuid1Sprache")
    @GenericGenerator(name = "uuid1Sprache", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type = "pg-uuid")
	 @Column(name = "code_sprache")
	 private UUID codeSprache;

	@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
	 @Size(min =5, max = 35, message="La taille doit etre comprise entre 5 et 35 caractères svp !")
	 @Column(name = "libelle")
	 private String libelle;
	
	@Basic(optional = false)
	@NotNull(message = "ce champ est obligatoire et donc ne peut etre nul !")
	 @Column(name = "intensive")
	 private boolean intensive;
	
	public Sprache(String libelle, boolean intensive, HashSet<Niveau> niveau) {
		this.libelle=libelle;
		this.intensive=intensive;
		this.niveau=niveau;
	}
	/*
	public void assigNiveau(HashSet<Niveau> niveaus) {
		for (Niveau niveau : niveaus) {
			addNiveau(niveau);
		}
	}*/
	
	public Sprache() {
		
	}
	

public Sprache(UUID codeSprache) {
	this.codeSprache=codeSprache;
		
	}
	public Set<Kurs> getKurs() {
		return kurs;
	}

	public void setKurs(Set<Kurs> kurs) {
		this.kurs = kurs;
	}

	public UUID getCodeSprache() {
		return codeSprache;
	}

	public void setCodeSprache(UUID codeSprache) {
		this.codeSprache = codeSprache;
	}

	public Sprache(String libelle, boolean intensive ) {
		this.libelle=libelle;
		this.intensive=intensive;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public boolean isIntensive() {
		return intensive;
	}
	public void setIntensive(boolean intensive) {
		this.intensive = intensive;
	}
	
	/*public void addNiveau(Niveau niveau) {
		niveau.addSprache(this);
		this.niveau.add(niveau);
	}
	*/
	


	public Set<Niveau> getNiveau() {
		return niveau;
	}

	public void setNiveau(Set<Niveau> niveau) {
		this.niveau = niveau;
	}

	@Override
	public String toString() {
		return "Sprache [codeSprache=" + codeSprache + "]";
	}



	public Set<Lehrer> getLehrer() {
		return lehrer;
	}

	public void setLehrer(Set<Lehrer> lehrer) {
		this.lehrer = lehrer;
	}

	/*@Override
	public String toString() {
		return "Sprache [kurs=" + kurs + ", niveau=" + niveau + ", codeSprache=" + codeSprache + ", libelle=" + libelle
				+ ", intensive=" + intensive + "]";
	}
*/




	
		
}

package dre.org.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "kurs")
//@JsonIdentityInfo(scope = Kurs.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "codeKurs")
//@JsonIgnoreProperties(allowSetters = true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "codeKurs",
        resolver = EntityIdResolver.class,
        scope=Kurs.class)
public class Kurs implements Serializable{

	private static final long serialVersionUID = 1L;



		@ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.ALL })
		    @JoinTable(
		        name = "kursniveau", //nom de la table de jointure en bd
		        joinColumns = { @JoinColumn(name = "code_kurs") }, 
		        inverseJoinColumns = { @JoinColumn(name = "code_niveau") }
		    )
		    private Set<Niveau> niveau = new HashSet<>();
	
	
    
	@ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.ALL })
	    @JoinTable(
	        name = "zeitraum", //nom de la table de jointure en bd
	        joinColumns = { @JoinColumn(name = "code_kurs") }, 
	        inverseJoinColumns = { @JoinColumn(name = "code_raum") }
	    )
	    private Set<Raum> raum = new HashSet<>();

   // @JsonManagedReference

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name="langue" ,nullable = false)
	private Sprache langue;
	@Id
	@Column(name = "code_kurs")
	@NotNull
	@Basic(optional = false)
	@GeneratedValue(generator = "uuid1Kurs")
    @GenericGenerator(name = "uuid1Kurs", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type = "pg-uuid")
	private UUID codeKurs;
	@Column(name = "au_programme")
	@NotNull(message = "Das Programm dieses Kurses ist erforderlich")
	 private String  auProgramme;
	@Column(name = "heure_debut")
	@NotNull(message = "Die Zeit dieses Kurses ist erforderlich")
	 private Timestamp heure_debut;
	@Column(name = "heure_fin")
	@NotNull(message = "Die Zeit dieses Kurses ist erforderlich")
	 private Timestamp heure_fin;
	@Column(name = "professeur")
	@NotNull(message = "Der Lehrer dieses Kurses ist erforderlich")
	 private String professeur;
	
	
	
	public Kurs() {
		
	}
	

	public Kurs(UUID codeKurs) {
		this.codeKurs=codeKurs;
	}
	
	
	
		public Kurs(Set<Niveau> niveau, Set<Raum> raum, Sprache langue,
			@NotNull(message = "Das Programm dieses Kurses ist erforderlich") String auProgramme,
			@NotNull(message = "Die Zeit dieses Kurses ist erforderlich") Timestamp heure_debut,
			@NotNull(message = "Die Zeit dieses Kurses ist erforderlich") Timestamp heure_fin,
			@NotNull(message = "Der Lehrer dieses Kurses ist erforderlich") String professeur) {
		this.niveau = niveau;
		this.raum = raum;
		this.langue = langue;
		this.auProgramme = auProgramme;
		this.heure_debut = heure_debut;
		this.heure_fin = heure_fin;
		this.professeur = professeur;
	}





		public Kurs(UUID codeKurs,  Set<Niveau> niveau, Set<Raum> raum, Sprache langue,
			@NotNull(message = "Das Programm dieses Kurses ist erforderlich") String auProgramme,
			@NotNull(message = "Die Zeit dieses Kurses ist erforderlich") Timestamp heure_debut,
			@NotNull(message = "Die Zeit dieses Kurses ist erforderlich") Timestamp heure_fin,
			@NotNull(message = "Der Lehrer dieses Kurses ist erforderlich") String professeur) {
			this.codeKurs=codeKurs;
		this.niveau = niveau;
		this.raum = raum;
		this.langue = langue;
		this.auProgramme = auProgramme;
		this.heure_debut = heure_debut;
		this.heure_fin = heure_fin;
		this.professeur = professeur;
	}






	


	public Sprache getLangue() {
		return langue;
	}


	public void setLangue(Sprache langue) {
		this.langue = langue;
	}


	public UUID getCodeKurs() {
		return codeKurs;
	}
	public void setCodeKurs(UUID codeKurs) {
		this.codeKurs = codeKurs;
	}
	public String getAuProgramme() {
		return auProgramme;
	}
	public void setAuProgramme(String auProgramme) {
		this.auProgramme = auProgramme;
	}
	
	public Timestamp getHeure_debut() {
		return heure_debut;
	}


	public void setHeure_debut(Timestamp heure_debut) {
		this.heure_debut = heure_debut;
	}


	public Timestamp getHeure_fin() {
		return heure_fin;
	}


	public void setHeure_fin(Timestamp heure_fin) {
		this.heure_fin = heure_fin;
	}


	public String getProfesseur() {
		return professeur;
	}
	public void setProfesseur(String professeur) {
		this.professeur = professeur;
	}
	public List<Raum> getRaum() {
		return new ArrayList<Raum>(this.raum);
	}
	public void setRaum(Set<Raum> raum) {
		this.raum= new HashSet<>(raum);
	}

	
 
	public List<Niveau> getNiveau() {
		return new ArrayList<Niveau>(this.niveau);
	}


	public void setNiveau(Set<Niveau> niveau) {
		this.niveau= new HashSet<>(niveau);
	}


	@Override
	public String toString() {
		return "Kurs [niveau=" + niveau + ", raum=" + raum + ", langue=" + langue + ", codeKurs=" + codeKurs
				+ ", auProgramme=" + auProgramme + ", heure_debut=" + heure_debut + ", heure_fin=" + heure_fin
				+ ", professeur=" + professeur + "]";
	}






	
	
	
}

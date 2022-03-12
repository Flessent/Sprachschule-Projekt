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
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "raum")
//@JsonIdentityInfo(scope = Raum.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "codeRaum")
public class Raum implements Serializable{
	
	private static final long serialVersionUID = 1L;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "raum")//tableau se trouvant dans sprache
    private Set<Kurs> kurs = new HashSet<>();
    	
    @Valid
	@Id
	@Basic(optional = false)
	@GeneratedValue(generator = "uuid1Raum")
    @GenericGenerator(name = "uuid1Raum", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "code_raum")
	private UUID codeRaum;
	@Basic(optional = false)
	@Column(name = "nom_raume")
	@NotNull(message = "Name des Raums ist erforderlich")
	@Size(min = 5,max = 35)
 private String nomRaume;
	
	@Basic(optional = false)
	@Column(name = "standort")
	@NotNull(message = "Standort des Raums ist erforderlich")
	@Size(min = 5,max = 35)
 private String standort;
	
	
	@NotNull(message="Die Zahl der vorhandenen Plätze ist erforderlich)")
	@Basic(optional = false)
	@Column(name = "nbre_places")
 private int nbrePlaces ;
	
		
	public Raum(Set<Kurs> kurs,
			@NotNull(message = "Name des Raums ist erforderlich") @Size(min = 5, max = 35) String nomRaume, String standort,
			@NotNull(message = "Die Zahl der vorhandenen Plätze ist erforderlich)") int nbrePlaces) {
		this.kurs = kurs;
		this.nomRaume = nomRaume;
		this.nbrePlaces = nbrePlaces;
		this.standort=standort;
	}
	public Raum(String nomRaume) {
	this.nomRaume=nomRaume;
	}

	public String getStandort() {
		return standort;
	}
	public void setStandort(String standort) {
		this.standort = standort;
	}
	public Raum() {
	}
	public Raum(UUID codeRaum) {
		this.codeRaum=codeRaum;
	}
	

	public String getNomRaume() {
		return nomRaume;
	}
	public void setNomRaume(String nomRaume) {
		this.nomRaume = nomRaume;
	}
	public int getNbrePlaces() {
		return nbrePlaces;
	}
	public void setNbrePlaces(int nbrePlaces) {
		this.nbrePlaces = nbrePlaces;
	}
	public Set<Kurs> getKurs() {
		return kurs;
	}
	public void setKurs(Set<Kurs> kurs) {
		this.kurs = kurs;
	}
	public UUID getCodeRaum() {
		return codeRaum;
	}
	public void setCodeRaum(UUID codeRaum) {
		this.codeRaum = codeRaum;
	}

	public void addKurs(Kurs kurs) {
		this.kurs.add(kurs);
	}
	/*@Override
	public String toString() {
		return "Raum [kurs=" + kurs + ", codeRaum=" + codeRaum + ", nomRaume=" + nomRaume + ", nbrePlaces=" + nbrePlaces
				+ "]";
	}
*/
	@Override
	public String toString() {
		return "Raum [codeRaum=" + codeRaum + "]";
	}	
	
	
	
}

package dre.org.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
public @Data  class PersonneDTO {
	private String username;private String nom; private String prenom; private Date dateNaissance; private String nationalite;private String profession;
	private String quartier; private String numTel; private  String email; private String numCni; private String password;
	private String diplomeEleve; private  Date dateDebutActiviteOuCours; private String activiteEnParalelle;
	private boolean  accountActivated;private Timestamp dateInscription ;
	private  Set<UUID> roles;

	public boolean accountActivated() {
		return accountActivated;
	}
	public void setActived(boolean isActived) {
		this.accountActivated = isActived;
	}
	
	/*PersonneDTO(String username,String nom,String prenom,Date dateNaissance, String profession, String quartier,int numTel,  String email,int numCni,String password,
			String diplomeEleve, Date dateDebutActiviteOuCours
			,boolean  isActived){
		this.username=username;
		this.nom=nom;
		this.prenom=prenom;
		this.dateNaissance=dateNaissance;
		this.profession=profession;
		this.dateDebutActiviteOuCours=dateDebutActiviteOuCours;
		this.numCni=numCni;
		this.numTel=numTel;
		this.quartier=quartier;
		this.email=email;
		this.password=password;
		this.diplomeEleve=diplomeEleve;
		this.isActived=isActived;		
		
	}
	 public PersonneDTO() {
		// TODO Auto-generated constructor stub
	}
	*/
	
}

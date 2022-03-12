package dre.org.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class BetreuerDTO extends LehrerDTO{
	private Integer nbreMoisExperiences;
	private BigDecimal  salaire;
	private Set<UUID> sprache;
	private Set<UUID> niveau;

	
	public BetreuerDTO(String username,String nom,String prenom,Date dateNaissance,String nationalite, String profession, String quartier,String numTel,  String email,String numCni,String password,
			String diplomeEleve, Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean  accountActivated, 
			Timestamp dateInscription,Set<UUID> sprache, Set<UUID> niveau, Integer nbreMoisExperiences,BigDecimal salaire,Set<UUID> roles) {
		super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours, activiteEnParalelle,accountActivated, 
				dateInscription,sprache,niveau,nbreMoisExperiences,salaire,roles);
		
	

	}
	
	public BetreuerDTO(String username,String nom,String prenom,Date dateNaissance, String nationalite,String profession, String quartier,String numTel,  String email,String numCni,String password,
			String diplomeEleve, Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean  accountActivated, Timestamp dateInscription,Set<UUID> sprache, Set<UUID> niveau,Set<UUID> roles) {
		super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours,activiteEnParalelle, 
				accountActivated, dateInscription,sprache,niveau,roles);
		
	

	}
}

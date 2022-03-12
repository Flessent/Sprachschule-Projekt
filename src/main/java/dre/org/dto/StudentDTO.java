package dre.org.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class StudentDTO extends BetreuerDTO {
private Set<UUID> sprache;
private Set<UUID> niveau;
private String numTelPereOuTuteur;
private String numTelMereOuTutrice;
private String nomPereOuTuteur;
private String nomMereOuTutrice;
private String nomFinanceurCours;
private String numTelFinanceurCours;
	
	StudentDTO(String username,String nom,String prenom,Date dateNaissance, String nationalite,String profession, String quartier,String numTel,  String email,String numCni,String password,
			String diplomeEleve, Date dateDebutActiviteOuCours,String activiteEnParalelle
			,boolean  accountActivated, Timestamp dateInscription, Set<UUID> sprache, Set<UUID> niveau,Set<UUID> roles){
		super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours, activiteEnParalelle, accountActivated,
				dateInscription,sprache,niveau,roles);
this.niveau=niveau;
this.sprache=sprache;
		
	}
}

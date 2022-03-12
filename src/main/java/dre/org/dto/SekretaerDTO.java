package dre.org.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
public @Data class SekretaerDTO  extends PersonneDTO{
	private Integer nbreMoisExperiences;
	private BigDecimal  salaire;
	private List< String> languesSekretaer;

	
	public SekretaerDTO(String username,String nom,String prenom,Date dateNaissance, String nationalite,String profession, String quartier,String numTel,  String email,String numCni,String password,
			String diplomeEleve, Date dateDebutActiviteOuCours,String activiteEnParalelle,boolean  accountActivated,
			Timestamp dateInscription,List<String> languesSekretaer, Integer nbreMoisExperiences,BigDecimal salaire,Set<UUID> roles) {
		super(username,nom,prenom,dateNaissance,nationalite,profession,quartier,numTel,email,numCni,password,diplomeEleve,dateDebutActiviteOuCours,activiteEnParalelle, accountActivated
				,  dateInscription,roles);
		this.languesSekretaer = languesSekretaer;
		this.nbreMoisExperiences = nbreMoisExperiences;
		this.salaire = salaire;
	}
	
}

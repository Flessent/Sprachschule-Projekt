package dre.org.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

public @Data class NiveauDTO {
private UUID codeNiveau;
	private String libelle;
	private Date date_debut ;
	private Date date_fin;
	private BigDecimal prix;
	//private Set<String>  sprache;
	private Set<UUID> sprache;

}

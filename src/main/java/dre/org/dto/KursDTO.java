package dre.org.dto;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

// getter und setter generieren
public @Data class KursDTO {
	
	
	private UUID codeKurs;
	private Set<UUID> niveau;
	private Set<UUID > raum;

private UUID langue;
private String au_programme ;
private Timestamp heure_debut;
private Timestamp heure_fin;
private String professeur;







}

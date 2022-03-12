package dre.org.dto;

import java.util.UUID;

import lombok.Data;

public @Data class RaumDTO {
	private UUID codeRaum;
private String standort;
	private String nom_raume;
	private int nbre_places;
	
}

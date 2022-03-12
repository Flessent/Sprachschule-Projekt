package dre.org.dto;

import java.util.UUID;


import lombok.Data;

 // @Data =getter, setter,toString,hashcode,usw..
//@Builder 
public @Data class SpracheDTO  {
	private UUID codeSprache;
private String libelle;
private boolean intensive; 
//private Set<NiveauDTO> niveau;

}
 
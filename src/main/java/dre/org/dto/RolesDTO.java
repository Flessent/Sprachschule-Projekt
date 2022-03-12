package dre.org.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
public @Data class RolesDTO {

	private UUID codeRole;
	private String  role;
	private String description;
	
}

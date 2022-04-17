package dre.org.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import dre.org.dto.LehrerDTO;
import dre.org.entities.Lehrer;
import dre.org.entities.Niveau;
import dre.org.entities.Roles;
import dre.org.entities.Sprache;
import dre.org.services.LehrerServices;
import dre.org.services.NiveauServices;
import dre.org.services.RolesServices;
import dre.org.services.SpracheServices;
import dre.org.services.implementation.LehrerServicesImplementation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/personne/lehrer")
@NoArgsConstructor
@EnableWebMvc
public class LehrerRestService {
@Autowired
private LehrerServices lehrerServices;
	public LehrerRestService(LehrerServices lehrerServices) {
		this.lehrerServices=lehrerServices;
	}
    @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
 	@PostMapping(path = "/saveLehrer" , produces =  MediaType.APPLICATION_JSON_VALUE)
 	public  ResponseEntity<Integer> saveLehrer(@RequestBody @Valid LehrerDTO lehrerDTO) {
 		System.out.println("lehrerDTO :"+lehrerDTO.getRoles());
 		int back=lehrerServices.saveLehrer( this.lehrerServices.convertDtoToEntity(lehrerDTO));
 		return new ResponseEntity<Integer>(back, HttpStatus.CREATED);

 	}

	 @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@GetMapping(path = "/listLehrer", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity< List<LehrerDTO>> listLehrer(){
		 List<LehrerDTO> listLehrerDTO =lehrerServices.getListLehrer().stream().map(lehrer ->lehrerServices.convertEntityToDto(lehrer)  ).collect(Collectors.toList());
	System.out.println("liste est"+lehrerServices.getListLehrer());
		 return new ResponseEntity<>(listLehrerDTO,HttpStatus.OK);
	 }
	 @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
@PatchMapping(path = "/updateLehrer")
	public ResponseEntity<?> updateLehrer(@RequestBody LehrerDTO lehrerDTO){
		 return new  ResponseEntity<>(lehrerServices.updateLehrer(lehrerServices.convertDtoToEntity(lehrerDTO)), HttpStatus.OK);
		 
	 }
	 @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
     @DeleteMapping(path = "/deleteLehrer/{username}")
	 public ResponseEntity<?> deleteLehrer(@PathVariable("username") String username) {
		 lehrerServices.deletePersonne(username);
	 return new ResponseEntity<>(HttpStatus.OK);
	 }
	
}

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

import dre.org.dto.SekretaerDTO;
import dre.org.entities.Roles;
import dre.org.entities.Sekretaer;
import dre.org.services.LehrerServices;
import dre.org.services.RolesServices;
import dre.org.services.SekretaerServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/sekretaer")
public class SekretaerRestService {

	@Autowired
	private SekretaerServices sekretaerServices;
	

	
    @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
 	@PostMapping(path = "/saveSekretaer")
 	public @ResponseBody Object saveSekretaer(@RequestBody @Valid SekretaerDTO sekretaerDTO) {
 		System.out.println("sekretaerDTO :"+sekretaerDTO.toString());

 	return this.sekretaerServices.saveSekretaer(this.sekretaerServices.convertDtoToEntity(sekretaerDTO));
 	}

	 @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@GetMapping(path = "/listSekretaer")
	 public ResponseEntity< List<SekretaerDTO>> listSekretaer(){
		 List<SekretaerDTO> listSekretaerDTO =this.sekretaerServices.getListSekretaer().stream().map(sekretaerDTO-> this.sekretaerServices. convertEntityToDto(sekretaerDTO) ).collect(Collectors.toList());
		
		 return new ResponseEntity<>(listSekretaerDTO,HttpStatus.OK);
	 }
	 @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
@PatchMapping(path = "/updateSekretaer")
	public ResponseEntity<?> updateSekretaer(@RequestBody SekretaerDTO sekretaerDTO){
		 return new  ResponseEntity<>(this.sekretaerServices.updateSekretaer( this.sekretaerServices.convertDtoToEntity(sekretaerDTO)), HttpStatus.OK);
		 
	 }
	 @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
     @DeleteMapping(path = "/deleteSekretaer/{username}")
	 public ResponseEntity<?> deleteSekretaer(@PathVariable("username") String username) {
		 this.sekretaerServices.deleteSekretaer(username);
	 return new ResponseEntity<>(HttpStatus.OK);
	 }
	
}

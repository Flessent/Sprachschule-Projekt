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

import dre.org.dto.BetreuerDTO;
import dre.org.entities.Betreuer;
import dre.org.entities.Niveau;
import dre.org.entities.Roles;
import dre.org.entities.Sprache;
import dre.org.services.BetreuerServices;
import dre.org.services.LehrerServices;
import dre.org.services.NiveauServices;
import dre.org.services.RolesServices;
import dre.org.services.SpracheServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/betreuer")
public class BetreuerRestService extends RuntimeException{

	private static final long serialVersionUID = 1L;

	@Autowired
	private BetreuerServices betreuerServices;



	
    @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
 	@PostMapping(path = "/saveBetreuer")
 	public @ResponseBody Object saveBetreuer(@RequestBody @Valid BetreuerDTO betreuerDTO) {
 		System.out.println(betreuerDTO.accountActivated());

 	return this.betreuerServices.saveBetreuer(this.betreuerServices.convertDtoToEntity(betreuerDTO));
 	}
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@GetMapping(path = "/listBetreuer")
	public ResponseEntity<List <BetreuerDTO>> listBetreuer(){
	
		
		List<BetreuerDTO> listeBeteuer=this.betreuerServices.getAllBetreuer().stream().map(betreuerDTO-> this.betreuerServices.convertEntityToDto(betreuerDTO)  ).collect(Collectors.toList());;
		System.out.print("voic la liste"+listeBeteuer);
		return new ResponseEntity<>(listeBeteuer,HttpStatus.OK);
	}
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@PatchMapping(path = "/updateBetreuer")
	public ResponseEntity<?> updateBetreuer(@RequestBody BetreuerDTO betreuerDTO){
	return new ResponseEntity<> ( this.betreuerServices.updateBetreuer( this.betreuerServices.convertDtoToEntity(betreuerDTO)), HttpStatus.OK);
	}
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@DeleteMapping(path = "/deleteBetreuer/{username}")
	public void  deleteBetreuer(@PathVariable String username) {
		this.betreuerServices.deletePersonne(username);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

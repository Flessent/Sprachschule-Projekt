package dre.org.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dre.org.dto.LeiterDTO;
import dre.org.dto.SekretaerDTO;
import dre.org.services.LeiterServices;
import lombok.NoArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/personne/leiter")
@NoArgsConstructor
public class LeiterRestService {

	@Autowired
	private LeiterServices leiterServices;
	
		public LeiterRestService(LeiterServices leiterServices) {
			this.leiterServices=leiterServices;
		}
	    @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	 	@PostMapping(path = "/saveLeiter" , consumes = "application/json")
	 	public @ResponseBody Object saveLeiter(@RequestBody @Valid LeiterDTO leiterDTO) {
	 		System.out.println("leiterDTO :"+leiterDTO.toString());

	 	return leiterServices.saveLeiter(this.leiterServices.convertDtoToEntity(leiterDTO));
	 	}
	
	
	    @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@GetMapping(path = "/listLeiter")
	 public ResponseEntity< List<LeiterDTO>> listLeiter(){
		 List<LeiterDTO> listLeiterDTO =leiterServices.getAllLeiter().stream().map(leiterDTO-> this.leiterServices . convertEntityToDto(leiterDTO) ).collect(Collectors.toList());
		
		 return new ResponseEntity<>(listLeiterDTO,HttpStatus.OK);
	 }
}

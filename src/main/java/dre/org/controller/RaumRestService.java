package dre.org.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import dre.org.dto.RaumDTO;
import dre.org.entities.Raum;
import dre.org.services.RaumServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/raum")
public class RaumRestService extends RuntimeException{

	private static final long serialVersionUID = 1L;
	@Autowired
	public RaumServices raumServices;
	@Autowired
	private ModelMapper modelMapper;
	//modelMapper.getConfiguration().setAmbiguityIgnored(true);
	private RaumDTO convertEntityToDto(Raum raum){
	    modelMapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.LOOSE);
	    RaumDTO raumDTO = new RaumDTO();
	    raumDTO = modelMapper.map(raum, RaumDTO.class);
	    return raumDTO;
	}

	private Raum convertDtoToEntity(RaumDTO raumDTO){
	    modelMapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.LOOSE);
	    Raum raum = new Raum();
	    raum = modelMapper.map(raumDTO, Raum.class);
	    return raum;
	}
	
	
	
		@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@PostMapping(path = "/saveRaum")
		public @ResponseBody Object saveRaum(@RequestBody @Valid RaumDTO raum,BindingResult bindingResult) {
			return raumServices.saveRaum(convertDtoToEntity(raum), bindingResult);
		}

		
		@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@PatchMapping(path = "/updateRaum")
		public ResponseEntity<?> updateRaum(@RequestBody RaumDTO raum) {

		    return new  ResponseEntity<>(raumServices.updateRaum(convertDtoToEntity(raum)),HttpStatus.OK);
		}
		@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@DeleteMapping(path  = "/deleteRaum/{codeRaum}",produces = "application/json")
		public ResponseEntity<?> deleteRaum(@PathVariable("codeRaum") UUID codeRaum){
			raumServices.deleteRaum(codeRaum);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		

		
		@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@GetMapping(path = "/listRaume")
		public ResponseEntity<List <RaumDTO>> listRaume(){
			List<RaumDTO> listeRaume=raumServices.listRaume().stream().map(this::convertEntityToDto).collect(Collectors.toList());;
			return new ResponseEntity<>(listeRaume,HttpStatus.OK);
		}
		
		
		
		@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@GetMapping(path = "/getRaumByCodeRaum/{codeRaum}")
		public ResponseEntity<RaumDTO> getOneRaum(@PathVariable("codeRaum")  UUID codeRaum){
		
			return new ResponseEntity<>(convertEntityToDto(raumServices.getOneRaumByCodeRaum(codeRaum)),HttpStatus.OK);
		}
		
		
}

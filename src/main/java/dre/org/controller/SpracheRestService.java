package dre.org.controller;

import java.util.List;
import java.util.Set;
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

import dre.org.dto.NiveauDTO;
import dre.org.dto.SpracheDTO;
import dre.org.entities.Niveau;
import dre.org.entities.Sprache;
import dre.org.services.NiveauServices;
import dre.org.services.SpracheServices;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/sprachen")
public class SpracheRestService extends RuntimeException{

	private static final long serialVersionUID = 1L;
	@Autowired
	public SpracheServices spracheServices;
	@Autowired
public NiveauServices niveauServices;
	   @Autowired
	    private  ModelMapper modelMapper;
	   
	   
	   
	    private SpracheDTO convertEntityToDto(Sprache sprache){
	    	modelMapper.getConfiguration()
	    	.setMatchingStrategy(MatchingStrategies.LOOSE)
	    	  .setFieldMatchingEnabled(true);

	    	SpracheDTO spracheDTO=new SpracheDTO();

	    	spracheDTO=modelMapper.map(sprache, SpracheDTO.class);

	    	return spracheDTO;
	    } 
	
	   
	   
	    
	    private Sprache convertDtoToEntity(SpracheDTO spracheDTO){
	    	//modelMapper.addConverter(convert(spracheDTO.getCodeSprache())  );

	        modelMapper.getConfiguration()
	                .setMatchingStrategy(MatchingStrategies.LOOSE);
	        Sprache sprache = new Sprache();
	        sprache = modelMapper.map(spracheDTO, Sprache.class);
			
	        
	        return sprache;
	    }
	    
	    private NiveauDTO convertEntityToDtoNiv(Niveau niveau){ 
	        modelMapper.getConfiguration()
	                .setMatchingStrategy(MatchingStrategies.LOOSE);
	        NiveauDTO niveauDTO = new NiveauDTO();
	        niveauDTO = modelMapper.map(niveau, NiveauDTO.class);
	        return niveauDTO;
	    }
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@PostMapping(path = "/saveSprache")
	public @ResponseBody Object saveSprache(@RequestBody @Valid SpracheDTO spracheDTO,BindingResult bindingResult) {
		
	return spracheServices.saveSprache(convertDtoToEntity(spracheDTO), bindingResult);
	}
	
	

	
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@PatchMapping(path = "/updateSprache")
	public ResponseEntity<?> updateSprache(@RequestBody SpracheDTO sprache) {
		
  return new ResponseEntity<>(spracheServices.updateSprache(convertDtoToEntity(sprache)), HttpStatus.OK);
	}
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@DeleteMapping(path  = "/deleteSprache/{codeSprache}", produces = "text/plain")
	public ResponseEntity<?> deleteSprache(@PathVariable("codeSprache") UUID codeSprache){
		spracheServices.deleteSprache(codeSprache);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	

	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@GetMapping(path = "/listNiveausLibelleSprache/{libelleSprache}")
	public ResponseEntity< Set<NiveauDTO>> listNiveauByLibelleSprache(@PathVariable("libelleSprache") String libelleSprache){
		Set<NiveauDTO> listeNiveaus=spracheServices.listNiveauByLibelleSprache(libelleSprache).stream().map(this::convertEntityToDtoNiv).collect(Collectors.toSet());;
		return new ResponseEntity<>(listeNiveaus,HttpStatus.OK);
	}
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@GetMapping(path = "/listSprachen")
	public ResponseEntity<List <SpracheDTO>> listSprachen(){
	
		
		
		List<SpracheDTO> listeSprache=spracheServices.listSprachen().stream().map(this::convertEntityToDto).collect(Collectors.toList());;
		System.out.print("voic la liste"+listeSprache);
		return new ResponseEntity<>(listeSprache,HttpStatus.OK);
	}
	
	
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@GetMapping(path = "/getSpracheByCodeSprache/{codeSprache}")
	public ResponseEntity<SpracheDTO> getOneSpracheByCodeSprache(@PathVariable("codeSprache")  UUID codeSprache){
		
		SpracheDTO sprache=convertEntityToDto(spracheServices.getOneSpracheByCodeSprache(codeSprache));
	 
	 return new ResponseEntity<>(sprache,HttpStatus.OK); 
		
	}
	
	
	
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@GetMapping(path = "/getListeSpracheByCodeSprachen")
	public ResponseEntity<List<SpracheDTO>> getListeSprachenByCodeSprache(Set <UUID> listCodeSprache){
		
		List<SpracheDTO> listeSprache=spracheServices.getAllSprachenByUUID(listCodeSprache).stream().map(this::convertEntityToDto).collect(Collectors.toList());;
	 
	 return new ResponseEntity<>(listeSprache,HttpStatus.OK); 
		
	}
	
	
	
	
	
	
	/*
	
	@PostConstruct
	private void createMappingConfig() {
		modelMapper.createTypeMap(Sprache.class, SpracheDTO.class)
		.addMappings(mapper -> mapper.skip(SpracheDTO::setNiveau))
		.addMapping(s -> s.getNiveau() == null ? null : s.getNiveau().stream().map(Niveau::getCodeNiveau).collect(Collectors.toSet()), SpracheDTO::setNiveau);
	}*/
	
	
}
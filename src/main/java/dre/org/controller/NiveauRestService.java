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

import dre.org.dto.NiveauDTO;
import dre.org.entities.Niveau;
import dre.org.entities.Sprache;
import dre.org.services.NiveauServices;
import dre.org.services.SpracheServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/niveau")
public class NiveauRestService extends RuntimeException{
	private static final long serialVersionUID = 1L;
	@Autowired
	public  NiveauServices niveauServices;

	@Autowired
	public  SpracheServices spracheServices;

	
@Autowired
private ModelMapper modelMapper;
//modelMapper.getConfiguration().setAmbiguityIgnored(true);
Converter<Sprache, UUID> uuidConverter = new AbstractConverter<Sprache, UUID>() {
    protected UUID convert(Sprache source) {
        return UUID.fromString(source.getCodeSprache().toString());
    }
};
private NiveauDTO convertEntityToDto(Niveau niveau){
	modelMapper.addConverter(uuidConverter);
    modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.LOOSE);
    NiveauDTO niveauDTO = new NiveauDTO();
    niveauDTO = modelMapper.map(niveau, NiveauDTO.class);
    return niveauDTO;
}

private Niveau convertDtoToEntity(NiveauDTO niveauDTO){
    modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.LOOSE);
    Niveau niveau = new Niveau();
    niveau = modelMapper.map(niveauDTO, Niveau.class);
    //Set<Sprache> listeSprachen=niveauServices.getAllNiveauByUUID(spracheDTO.getNiveau());
    Set<Sprache> listeSprachen=spracheServices.getAllSprachenByUUID(niveauDTO.getSprache());
    niveau.setSprache(listeSprachen);

    return niveau;
}
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"}) 
	@PostMapping(path = "/saveNiveau")
	public @ResponseBody Object saveNiveau(@RequestBody @Valid NiveauDTO niveauDTO,BindingResult bindingResult) {

		return niveauServices.saveNiveau(convertDtoToEntity(niveauDTO), bindingResult);
	}
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@GetMapping(path = "/listeNiveau")
	public ResponseEntity<List<NiveauDTO>>  listeNiveau(){
		List<NiveauDTO> listeNiveaus=niveauServices.listNiveaus().stream().map(this::convertEntityToDto).collect(Collectors.toList());;
		return new ResponseEntity<>(listeNiveaus,HttpStatus.OK);
	}
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@PatchMapping(path = "/updateNiveau")
	public ResponseEntity<?> updateNiveau( @RequestBody NiveauDTO niveau){
		System.out.print("voici le niveauDTO arrivant"+niveau);
		return new ResponseEntity<>(niveauServices.updateNiveau(convertDtoToEntity(niveau)),HttpStatus.OK);
	}
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@DeleteMapping(path = "/deleteNiveau/{codeNiveau}", produces = "application/json")
	public ResponseEntity<?> deleteNiveau(@PathVariable("codeNiveau") UUID codeNiveau){
		niveauServices.deleteNiveau(codeNiveau);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
}

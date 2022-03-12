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

import dre.org.dto.LehrerDTO;
import dre.org.entities.Lehrer;
import dre.org.entities.Niveau;
import dre.org.entities.Roles;
import dre.org.entities.Sprache;
import dre.org.services.LehrerServices;
import dre.org.services.NiveauServices;
import dre.org.services.RolesServices;
import dre.org.services.SpracheServices;
import lombok.NoArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/personne/lehrer")
@NoArgsConstructor
public class LehrerRestService {
	@Autowired
	public SpracheServices spracheServices;
	@Autowired
	public NiveauServices niveauServices;

	//@Autowired
	//private  PersonneServices personneServices;
	


	@Autowired
	private  LehrerServices lehrerServices;
	@Autowired
	private  RolesServices rolesServices;
	
	
	Converter<Niveau, UUID> uuidConverterNiveau = new AbstractConverter<Niveau, UUID>() {
	    protected UUID convert(Niveau source) {
	        return UUID.fromString(source.getCodeNiveau().toString());
	    }
	};
	Converter<Sprache, UUID> uuidConverterSprache = new AbstractConverter<Sprache, UUID>() {
	    protected UUID convert(Sprache source) {
	        return UUID.fromString(source.getCodeSprache().toString());
	    }
	};
	
	
	@Autowired
	public ModelMapper modelMapper;
	public  LehrerDTO convertEntityToDto(Lehrer lehrer){
		modelMapper.addConverter(uuidConverterSprache);
		modelMapper.addConverter(uuidConverterNiveau);
      	modelMapper.getConfiguration()
    	.setMatchingStrategy(MatchingStrategies.LOOSE)
    	  .setFieldMatchingEnabled(true);

      	LehrerDTO lehrerDTO=new LehrerDTO();

    	lehrerDTO=modelMapper.map(lehrer, LehrerDTO.class);

    	return lehrerDTO;
    } 

   
   
    
    public  Lehrer convertDtoToEntity(LehrerDTO lehrerDTO){

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Lehrer lehrer = new Lehrer() ;
        lehrer = modelMapper.map(lehrerDTO, Lehrer.class);
        Set<Niveau> listeNiveau=niveauServices.getAllNiveauByUUID(lehrerDTO.getNiveau());
        lehrer.setNiveau(listeNiveau);    
        Set<Sprache> sprache = spracheServices.getAllSprachenByCodeSprachen(lehrerDTO.getSprache());
        lehrer.setSprache(sprache);
        Set<Roles> roles=rolesServices.getAllRolesByUUID(lehrerDTO.getRoles());
        lehrer.setRoles(roles);
        return lehrer;
    }
	
    @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
 	@PostMapping(path = "/saveLehrer")
 	public @ResponseBody Object saveLehrer(@RequestBody @Valid LehrerDTO lehrerDTO) {
 		System.out.println("lehrerDTO :"+lehrerDTO.getRoles());

 	return lehrerServices.saveLehrer(convertDtoToEntity(lehrerDTO));
 	}

	 @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@GetMapping(path = "/listLehrer")
	 public ResponseEntity< List<LehrerDTO>> listLehrer(){
		 List<LehrerDTO> listLehrerDTO =lehrerServices.getListLehrer().stream().map(this::convertEntityToDto).collect(Collectors.toList());
		
		 return new ResponseEntity<>(listLehrerDTO,HttpStatus.OK);
	 }
	 @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
@PatchMapping(path = "/updateLehrer")
	public ResponseEntity<?> updateLehrer(@RequestBody LehrerDTO lehrerDTO){
		 return new  ResponseEntity<>(lehrerServices.updateLehrer(convertDtoToEntity(lehrerDTO)), HttpStatus.OK);
		 
	 }
	 @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
     @DeleteMapping(path = "/deleteLehrer/{username}")
	 public ResponseEntity<?> deleteLehrer(@PathVariable("username") String username) {
		 lehrerServices.deleteLehrer(username);
	 return new ResponseEntity<>(HttpStatus.OK);
	 }
	
}

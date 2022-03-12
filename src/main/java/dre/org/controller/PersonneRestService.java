package dre.org.controller;

import java.util.UUID;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dre.org.dao.PersonneRepository;
import dre.org.dto.LehrerDTO;
import dre.org.dto.PersonneDTO;
import dre.org.entities.Lehrer;
import dre.org.entities.Niveau;
import dre.org.entities.Personne;
import dre.org.entities.Roles;
import dre.org.entities.Sprache;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/personne")
public class PersonneRestService implements UserDetailsService{
@Autowired
private PersonneRepository< Personne> personneRepository;
@Autowired
private ModelMapper modelMapper;

Converter<Roles, UUID> uuidConverterRoles = new AbstractConverter<Roles, UUID>() {
    protected UUID convert(Roles roles) {
        return UUID.fromString(roles.getCodeRole().toString());
    }
};


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
private PersonneDTO convertEntityToDto(Personne personne){
	modelMapper.addConverter(uuidConverterSprache);
	modelMapper.addConverter(uuidConverterNiveau);
	modelMapper.addConverter(uuidConverterRoles);
  	modelMapper.getConfiguration()
	.setMatchingStrategy(MatchingStrategies.LOOSE)
	  .setFieldMatchingEnabled(true);

  	PersonneDTO personneDTO=new PersonneDTO();

	personneDTO=modelMapper.map(personne, PersonneDTO.class);

	return personneDTO;
} 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	@GetMapping(value="/getPersonneInfo/{username}")
	public @ResponseBody Object getPersonneByUsername( @PathVariable("username") String username, Authentication authentication) throws UsernameNotFoundException {
	Personne personne =personneRepository.getGemeinsameInfosPersonne(username);
	System.out.println("Valeur username connecté "+authentication.getName());

		
	if(personne==null) {
		UsernameNotFoundException e = null;
		System.out.println("Personne pas trouvee"+e.getMessage());
		return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
	}
	else {

		return new ResponseEntity<>(convertEntityToDto(personne),HttpStatus.OK);
	}
	
	}  

	
	
	
	
	
	
}

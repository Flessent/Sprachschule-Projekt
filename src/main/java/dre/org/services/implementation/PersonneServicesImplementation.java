package dre.org.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import dre.org.dao.PersonneRepository;
import dre.org.dto.LehrerDTO;
import dre.org.dto.PersonneDTO;
import dre.org.entities.Lehrer;
import dre.org.entities.Personne;
import dre.org.services.PersonneServices;

@Service
public class PersonneServicesImplementation implements PersonneServices{
	@Autowired
	private PersonneRepository<Personne> personneRepository;
	@Autowired
	private ModelMapper modelMapper;
	private PersonneDTO convertEntityToDto(Personne personne){
      	modelMapper.getConfiguration()
    	.setMatchingStrategy(MatchingStrategies.LOOSE)
    	  .setFieldMatchingEnabled(true);

      	PersonneDTO personneDTO=new LehrerDTO();

      	personneDTO=modelMapper.map(personne, PersonneDTO.class);

    	return personneDTO;
    } 
	
	
	

	@Override
	public Object savePersonne(Personne personne, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, Object> errors=new HashMap<>();
			errors.put("errors", true);
			for(FieldError fieldError:bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
				System.out.println("Voici les erreurs"+errors);
			}

		return errors;
		}
		System.out.println("Informations bien enregistrées"+personne);
		return personneRepository.save(personne);	}




	@Override
	public List<PersonneDTO> listeBestimmtePersone(String type) {
		return personneRepository.getBestimmtePersonne(type).stream().map(this:: convertEntityToDto).collect(Collectors.toList());
	}

}

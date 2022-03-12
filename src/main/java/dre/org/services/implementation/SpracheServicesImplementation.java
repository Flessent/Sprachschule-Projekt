package dre.org.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import dre.org.dao.SpracheRepository;
import dre.org.entities.Niveau;
import dre.org.entities.Sprache;
import dre.org.services.SpracheServices;

@Service
public class SpracheServicesImplementation implements SpracheServices {

	@Autowired
	public SpracheRepository spracheRepository;

 
    
	@Override
	public Object saveSprache(Sprache sprache, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, Object> errors=new HashMap<>();
			errors.put("errors", true);
			for(FieldError fieldError:bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
				System.out.println("Voici les erreurs"+errors);
			}

		return errors;
		}
		System.out.println("Informations bien enregistrées");
		return spracheRepository.save(sprache);		
		
		
	}

	@Override
	public Sprache updateSprache(Sprache sprache) {
		System.out.print("voici les valeurs de Updatedsprache"+ sprache.getLibelle());
		Sprache updatedSprache =spracheRepository.save(sprache);

	    return updatedSprache;
	}

	@Override
	public void deleteSprache(UUID codeSprache) {
		System.out.print("voici le codeSprache struit"+ codeSprache);
		spracheRepository.deleteSprache(codeSprache);

	}

	@Override
	public Set<Niveau> listNiveauByLibelleSprache(String libelleSprache) {
		Set<Niveau> listNiveau= spracheRepository.getAllNiveauByLibelleSprache(libelleSprache);
		return listNiveau;
	}

	@Override
	public List<Sprache> listSprachen() {
		List<Sprache> listSprachen= spracheRepository.findAll();
				
		return listSprachen;
	}

	@Override
	public Sprache getOneSpracheByCodeSprache(UUID codeSprache) {
		Sprache sprache= spracheRepository.getSpracheByCodeSprache(codeSprache);
        System.out.print("voici le sprache"+sprache);
		return sprache;
	}

	@Override
	public Set<Sprache> getAllSprachenByCodeSprachen(Set<UUID> listeUUIDSprachen) {

		return spracheRepository.findByCodeSpracheIn( listeUUIDSprachen);
	}

	@Override
	public Set<Sprache> getAllSprachenByUUID(Set<UUID> listeUUIDSprachen) {
	
		return spracheRepository.findByCodeSpracheIn(listeUUIDSprachen);
	}

	@Override
	public List<String> getAllLibelleByCodeSprache(List<UUID> listeLibelle) {
		
		return spracheRepository.listeLibelleByCodeSprache(listeLibelle);
	}

	@Override
	public UUID getCodeSpracheByLibelle(String libelle) {
		return spracheRepository.getCodeSpracheByLibelle(libelle);
	}

	

	
}

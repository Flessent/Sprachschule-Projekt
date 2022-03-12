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

import dre.org.dao.NiveauRepository;
import dre.org.dto.NiveauDTO;
import dre.org.entities.Niveau;
import dre.org.services.NiveauServices;

@Service
public class NiveauServicesImplementation implements NiveauServices{
@Autowired
NiveauRepository niveauRepository;



	@Override
	public Object saveNiveau(Niveau niveau, BindingResult bindingResult) {
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
		return niveauRepository.save(niveau) ;
	}

	@Override
	public Niveau updateNiveau(Niveau niveau) {
		Niveau updatedNiveau=  niveauRepository.save(niveau);
		return updatedNiveau;
	}

	@Override
	public void deleteNiveau(UUID codeNiveau) {
		niveauRepository.deleteNiveau(codeNiveau);
		
	}

	@Override
	public List<Niveau> listNiveaus() {
		// TODO Auto-generated method stub
		List<Niveau> listeNiveau= niveauRepository.findAll();
				
		return listeNiveau;
	}

	@Override
	public Niveau getOneNiveauByCodeNiveau(UUID codeNiveau) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Set<Niveau> getAllNiveauDTO(Set<NiveauDTO> listeNiveauDTO) {
		
		return (Set<Niveau>) niveauRepository.findAll();
	}

	@Override
	public Set<Niveau> getAllNiveauByLibelle(Set<String> libelle) {
		
		return niveauRepository.findByLibelleIn(libelle);
	}

	@Override
	public Set<Niveau> getAllNiveauByUUID(Set<UUID> listeUUIDNiveau) {
		// TODO Auto-generated method stub
		return niveauRepository.findByCodeNiveauIn(listeUUIDNiveau);
	}

	





	

	

}

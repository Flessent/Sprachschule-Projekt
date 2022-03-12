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

import dre.org.dao.RaumRepository;
import dre.org.entities.Raum;
import dre.org.services.RaumServices;

@Service
public class RaumServicesImplementation implements RaumServices{
@Autowired
RaumRepository raumRepository;
	@Override
	public Object saveRaum(Raum raum, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, Object> errors=new HashMap<>();
			errors.put("errors", true);
			for(FieldError fieldError:bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
				System.out.println("Voici les erreurs"+raum);
			}

		return errors;
		}
		System.out.println("Informations bien enregistrées"+raum);
		return raumRepository.save(raum);
	}

	@Override
	public Raum updateRaum(Raum raum) {
		Raum updatedRaum =raumRepository.save(raum);
return updatedRaum;
	}

	@Override
	public void deleteRaum(UUID codeRaum) {
		System.out.print("voici le codeRaum struit"+ codeRaum);
		raumRepository.deleteRaum(codeRaum);		
	}

	@Override
	public List<Raum> listRaume() {
		List<Raum> listRaume= raumRepository.findAll();
		return listRaume;
	}

	@Override
	public Raum getOneRaumByCodeRaum(UUID codeRaum) {
		Raum raum= raumRepository.getRaumByCodeRaum(codeRaum);
        System.out.print("voici le sprache"+raum);
        return raum;
	}

	@Override
	public Set<Raum> getAllRaumByUUID(Set<UUID> listeUUIDRaum) {
		
		return raumRepository.findByCodeRaumIn(listeUUIDRaum);
	}

}

package dre.org.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import dre.org.dao.KursRepository;
import dre.org.entities.Kurs;

import dre.org.services.KursServices;

@Service
public class KursServicesImplementation implements KursServices {

	@Autowired
	KursRepository kursRepository;
	
	

    
	
	@Override
	public Object saveKurs(Kurs kurs, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			Map<String, Object> errors=new HashMap<>();
			errors.put("errors", true);
			for(FieldError fieldError:bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
				System.out.println("Voici les erreurs"+errors);
			}

		return errors;
		}
		System.out.println("Informations bien enregistrées"+kurs);
		 
		 return kursRepository.save(kurs);
	}

	@Override
	public Kurs updateKurs(Kurs kurs) {
		System.out.print("voici les valeurs de Updatedsprache"+ kurs.getProfesseur());
		 Kurs updatedKurs =kursRepository.save(kurs);
return updatedKurs;
	}

	@Override
	public void deleteKurs(UUID codeKurs) {
		System.out.print("voici le codeSprache struit"+ codeKurs);
		kursRepository.deleteKurs(codeKurs);		
	}
	
	

	@Override
	public Kurs getOneKursByCodeKurs(UUID codeKurs) {
		Kurs kurs= kursRepository.getKursByCodeKurs(codeKurs);
        System.out.print("voici le sprache"+kurs);
        return kurs;
	}



	@Override
	public List< List<String>>getNiveauByCodeKurs(UUID codeKurs) {
		
		return kursRepository.getNiveauByCodeKurs(codeKurs);
	}

	@Override
	public List< List<String>>getRaumByCodeKurs(UUID codeKurs) {
		return kursRepository.getRaumByCodeKurs(codeKurs);
	}

	@Override
	public List<Kurs> getAllKurs() {
		// TODO Auto-generated method stub
		return kursRepository.getAllKurs();
	}

}

package dre.org.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import dre.org.dao.LehrerRepository;
import dre.org.dao.NiveauRepository;
import dre.org.dao.RolesRepository;
import dre.org.dao.SpracheRepository;
import dre.org.dto.PersonneDTO;
import dre.org.entities.Betreuer;
import dre.org.entities.Lehrer;
import dre.org.entities.Personne;
import dre.org.entities.Sekretaer;
import dre.org.entities.Student;
import dre.org.services.LehrerServices;
import dre.org.services.PersonneServices;
@Service
public class LehrerServicesImplementation implements LehrerServices{

@Autowired
private LehrerRepository lehrerRepository;
@Autowired
private SpracheRepository spracheRepository;
@Autowired
private NiveauRepository niveauRepository;
@Autowired
private RolesRepository rolesRepository;

	@Override
	public int saveLehrer(Lehrer lehrer) {
		try {
			lehrerRepository.save(lehrer);
	        System.out.println("Voici le lehrer"+lehrer);

			return 0;
			
		} catch (Exception e) {
			
	        System.out.println("Voici les erreurs"+e.getMessage());

		      }

			return 1;
		}
	

	@Override
	public int updateLehrer(Lehrer lehrer) {
try {
	Lehrer updatedLehrer=this.lehrerRepository.save(lehrer);
	return 0;
} catch (Exception e) {
return 1;
}
		
	}

	@Override
	public void deleteLehrer(String username) {
		lehrerRepository.deletePersonne(username);// deletePersonne() kommt aus  PersonneRepository (Vererbung der PersonneRepository von LehrerRepository)
		
	}


	@Override
	public final List<Lehrer> getListLehrer() {
		
		return lehrerRepository.getAllLehrer();
	}

	@Override
	public List<Sekretaer> getListSekretaer() {
		
		return lehrerRepository.getAllSekretaer();
	}

	@Override
	public List<Betreuer> getListBetreuer() {
		
		return lehrerRepository.getAllBetreuer();
	}

	@Override
	public List<Student> getListStudent() {
		
		return lehrerRepository.getAllStudenten();
	}

 



}

package dre.org.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import dre.org.dao.SekretaerRepository;
import dre.org.entities.Betreuer;
import dre.org.entities.Lehrer;
import dre.org.entities.Sekretaer;
import dre.org.entities.Student;
import dre.org.services.LehrerServices;
import dre.org.services.SekretaerServices;

//@Service
public class SekretaerServicesImplementation  implements SekretaerServices {
	@Autowired
	private SekretaerRepository sekretaerRepository;
@Autowired
private LehrerServices lehrerServices;
	@Override
	public Object saveSekretaer(Lehrer lehrer) {
		lehrer=new Sekretaer();
		return lehrerServices.saveLehrer(lehrer);
	}

	@Override
	public List<Sekretaer> getListSekretaer() {
		
		return lehrerServices.getListSekretaer();
	}

	@Override
	public void deleteSekretaer(String username) {
		sekretaerRepository.deletePersonne( username);
	}

	@Override
	public Sekretaer updateSekretaer(Sekretaer sekretaer) {
		Sekretaer updateSekretaer = sekretaerRepository.save(sekretaer);
		
		return updateSekretaer;
	}



	@Override
	public int updateLehrer(Lehrer sekretaer) {
		sekretaer = new Sekretaer();
		return lehrerServices.updateLehrer(sekretaer);
	}

	@Override
	public void deleteLehrer(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Lehrer> getListLehrer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveLehrer(Lehrer lehrer) {
		lehrer=new Sekretaer();
		return lehrerServices.saveLehrer(lehrer);
		
	}

	@Override
	public List<Betreuer> getListBetreuer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getListStudent() {
		// TODO Auto-generated method stub
		return null;
	}



}

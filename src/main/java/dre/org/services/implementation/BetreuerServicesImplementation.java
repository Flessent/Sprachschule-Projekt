package dre.org.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import dre.org.dao.BetreuerRepository;
import dre.org.entities.Betreuer;
import dre.org.entities.Lehrer;
import dre.org.entities.Sekretaer;
import dre.org.services.BetreuerServices;
import dre.org.services.LehrerServices;

//@Service
public class BetreuerServicesImplementation implements BetreuerServices {
@Autowired
BetreuerRepository betreuerRepository;

	@Override
	public Object saveBetreuer(Betreuer betreuer, BindingResult bindingResult) {
		return null;
	
	}
	@Override
	public List<Betreuer> getAllBetreuer() {
		// TODO Auto-generated method stub
		return betreuerRepository.getAllBetreuer();
	}
	@Override
	public Betreuer updateBetreuer(Betreuer betreuer) {
		return null;
	}
	@Override
	public void deleteBetreuer(String username) {
		
	}
	@Override
	public Object saveLehrer(Lehrer betreuer, BindingResult bindingResult) {
		return null;
	}
	@Override
	public Lehrer updateLehrer(Lehrer betreuer) {
		return null;
	}
	

}

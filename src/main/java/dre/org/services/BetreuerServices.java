package dre.org.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import dre.org.entities.Betreuer;
import dre.org.entities.Lehrer;

public interface BetreuerServices{
 public Object saveBetreuer(Betreuer betreuer, BindingResult bindingResult);
	public List<Betreuer> getAllBetreuer();
	
	public Object saveLehrer(Lehrer lehrer,BindingResult bindingResult);
public 	Betreuer updateBetreuer(Betreuer  betreuer);
	public void deleteBetreuer(String username);
	
	public 	Lehrer updateLehrer(Lehrer  lehrer);

}

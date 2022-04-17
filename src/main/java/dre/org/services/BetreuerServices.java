package dre.org.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import dre.org.dto.BetreuerDTO;
import dre.org.entities.Betreuer;
import dre.org.entities.Lehrer;

public interface BetreuerServices{
 public int saveBetreuer(Betreuer betreuer);
	public List<Betreuer> getAllBetreuer();
	
	//public Object saveLehrer(Lehrer lehrer,BindingResult bindingResult);
public 	int  updateBetreuer(Betreuer  betreuer);
	public void deletePersonne(String username);
	
	//public 	Lehrer updateLehrer(Lehrer  lehrer);
	
	public Betreuer convertDtoToEntity(BetreuerDTO betreuerDTO);
	public BetreuerDTO convertEntityToDto(Betreuer betreuer);
	
	

}

package dre.org.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import dre.org.dto.SekretaerDTO;
import dre.org.entities.Lehrer;
import dre.org.entities.Sekretaer;

public interface SekretaerServices {
public int saveSekretaer(Sekretaer sekretaer);



public List<Sekretaer> getListSekretaer();

//public List<Sekretaer> getListSekretaer();
public void deleteSekretaer(String username);
public Sekretaer updateSekretaer(Sekretaer sekretaer);

public SekretaerDTO convertEntityToDto(Sekretaer sekretaer);

public Sekretaer convertDtoToEntity(SekretaerDTO sekretaerDTO);
}
 
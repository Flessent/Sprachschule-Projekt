package dre.org.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import dre.org.entities.Lehrer;
import dre.org.entities.Sekretaer;

public interface SekretaerServices extends LehrerServices {
public Object saveSekretaer(Lehrer sekretaer);
@Override
public int saveLehrer(Lehrer lehrer);
@Override
public 	int updateLehrer(Lehrer  lehrer);
@Override
public List<Sekretaer> getListSekretaer();

//public List<Sekretaer> getListSekretaer();
public void deleteSekretaer(String username);
public Sekretaer updateSekretaer(Sekretaer sekretaer);
}
 
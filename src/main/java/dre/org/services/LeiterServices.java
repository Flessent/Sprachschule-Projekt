package dre.org.services;

import java.util.List;

import dre.org.dto.LeiterDTO;
import dre.org.entities.Lehrer;
import dre.org.entities.Leiter;

public interface LeiterServices {
public int saveLeiter(Leiter leiter);
public  LeiterDTO convertEntityToDto(Leiter leiter);
public  Leiter convertDtoToEntity(LeiterDTO leiterrDTO);

public List<Leiter>getAllLeiter();
}

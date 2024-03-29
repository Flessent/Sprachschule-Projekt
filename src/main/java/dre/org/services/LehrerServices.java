package dre.org.services;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.BindingResult;

import dre.org.dto.LehrerDTO;
import dre.org.entities.Betreuer;
import dre.org.entities.Lehrer;
import dre.org.entities.Personne;
import dre.org.entities.Sekretaer;
import dre.org.entities.Student;

public interface LehrerServices {
	 public int saveLehrer(Lehrer lehrer);
		
	public 	int updateLehrer(Lehrer  lehrer);
		public int deletePersonne(String username);
		public   List<Lehrer> getListLehrer();
		public List<Sekretaer> getListSekretaer();
		public List<Betreuer> getListBetreuer();
		public List<Student> getListStudent();
		public  LehrerDTO convertEntityToDto(Lehrer lehrer);
		public  Lehrer convertDtoToEntity(LehrerDTO lehrerDTO);
		/*
      public UUID getCodeSpracheByLibelle(String libelle);
      public UUID getCodeNiveauByLibelle(String libelle);
      public UUID getCodeRoleByRole(String role);*/

}

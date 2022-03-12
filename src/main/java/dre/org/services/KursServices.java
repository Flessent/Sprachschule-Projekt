package dre.org.services;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.BindingResult;

import dre.org.entities.Kurs;

public interface KursServices {

	public Object saveKurs(Kurs kurs,BindingResult result);
	public Kurs updateKurs(Kurs kurs);
	public void deleteKurs(UUID codeKurs);
	//public Set <Niveau >listNiveauByLibelleSprache (String libelleSprache);
	//public   List<Kurs> listKurse();
	public Kurs getOneKursByCodeKurs(UUID codeKurs);
	//public List<List<List<String>>> getAllInfosKurs();
	public List< List<String>>getNiveauByCodeKurs(UUID codeKurs);
	public List< List<String>> getRaumByCodeKurs(UUID codeKurs);
public List<Kurs> getAllKurs();
}

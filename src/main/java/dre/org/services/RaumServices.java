package dre.org.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.validation.BindingResult;

import dre.org.entities.Raum;

public interface RaumServices {

	public Object saveRaum(Raum raum,BindingResult result);
	public Raum updateRaum(Raum raum);
	public void deleteRaum(UUID codeRaum);
	//public Set <Niveau >listNiveauByLibelleSprache (String libelleSprache);
	public   List<Raum> listRaume();
	public Raum getOneRaumByCodeRaum(UUID codeRaum);
	public Set<Raum> getAllRaumByUUID(Set<UUID> listeUUIDRaum);
}

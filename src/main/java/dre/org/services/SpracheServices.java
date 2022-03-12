package dre.org.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.validation.BindingResult;

import dre.org.entities.Niveau;
import dre.org.entities.Sprache;

public interface SpracheServices {

	public Object saveSprache(Sprache sprache,BindingResult result);
	public Sprache updateSprache(Sprache sprache);
	public void deleteSprache(UUID codeSprache);
	public Set <Niveau>listNiveauByLibelleSprache (String libelleSprache);
	public   List<Sprache> listSprachen();
	public Sprache getOneSpracheByCodeSprache(UUID codeSprache);
	public Set<Sprache> getAllSprachenByCodeSprachen(Set<UUID> listeUUIDSprachen);
	
	public Set<Sprache> getAllSprachenByUUID(Set<UUID> listeUUIDSprachen);
public List<String> getAllLibelleByCodeSprache(List<UUID> listeLibelle);
public UUID getCodeSpracheByLibelle(String libelle);
}

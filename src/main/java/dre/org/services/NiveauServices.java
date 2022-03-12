package dre.org.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.validation.BindingResult;

import dre.org.dto.NiveauDTO;
import dre.org.entities.Niveau;

public interface NiveauServices {
	public Object saveNiveau(Niveau niveau,BindingResult result);
	public Niveau updateNiveau(Niveau niveau);
	public void deleteNiveau(UUID codeNiveau);
	//public Set <Niveau >listNiveauByLibelleSprache (String libelleSprache);
	public   List<Niveau> listNiveaus();
	public Niveau getOneNiveauByCodeNiveau(UUID codeNiveau);
	public Set<Niveau> getAllNiveauByUUID(Set<UUID> listeUUIDNiveau);
	public Set<Niveau> getAllNiveauDTO(Set<NiveauDTO> listeNiveauDTO);
public Set< Niveau> getAllNiveauByLibelle(Set<String> libelle);
//public <Niveau> get
}

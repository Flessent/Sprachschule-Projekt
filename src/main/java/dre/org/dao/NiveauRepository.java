package dre.org.dao;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import dre.org.entities.Niveau;

public interface NiveauRepository extends JpaRepository<Niveau, UUID>{
    @Transactional
	@Modifying
	@Query("delete from Niveau n where n.codeNiveau=:codeNiveau ")
void deleteNiveau(UUID codeNiveau);
    Set<Niveau> findByCodeNiveauIn(Set <UUID> listeUUIDNiveau);

Set<Niveau> findByLibelleIn (Set<String> libelle); 
@Modifying
@Transactional
	@Query(value="select  n.libelle from niveau n inner join kursniveau kn on kn.code_niveau=n.code_niveau", nativeQuery = true )
	List<String> getNiveauInfo();
List<Niveau> findByLibelleIn(List<String> listeLiebelle);
@Transactional
@Query("select codeNiveau  from Niveau n where n.libelle=:libelle ")
public UUID getCodeNiveauByLibelle(@Param("libelle") String  libelle);

}
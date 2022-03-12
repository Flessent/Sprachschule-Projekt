package dre.org.dao;


import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import dre.org.entities.Raum;

//@Repository
public interface RaumRepository extends JpaRepository<Raum, UUID> {
	void	deleteRaumByCodeRaum(UUID codeRaum);
	Raum getRaumByCodeRaum(UUID codeRaum);
	@Transactional
	@Modifying
	@Query("delete from Raum r where r.codeRaum=:codeRaum")
	void deleteRaum(@Param("codeRaum") UUID codeRaum);
    Set<Raum> findByCodeRaumIn(Set <UUID> listeUUIDRaum);
    @Modifying
    @Transactional
    	@Query(value="select  n.libelle from Niveau n inner join kursniveau kn on kn.codeNiveau=n.codeNiveau", nativeQuery = true )
    	List<String> getNiveauInfo();
    List<Raum> findByNomRaumeIn(List<String> listeNomRaume);
}

package dre.org.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import dre.org.entities.Niveau;
import dre.org.entities.Sprache;


//@Repository
public interface SpracheRepository extends JpaRepository<Sprache, String>{

void	deleteSpracheBycodeSprache(UUID codeSprache);
Sprache getSpracheByLibelle(String libelle);
Sprache getSpracheByCodeSprache(UUID codeSprache);

@Transactional
@Modifying
@Query("delete from Sprache s where s.codeSprache=:codeSprache")
void deleteSprache(@Param("codeSprache") UUID codeSprache);
Set<Sprache> findByCodeSpracheIn(Set <UUID> listeUUID);
@Transactional
@Modifying
@Query("SELECT s.libelle from Sprache s where s.codeSprache IN ( : listeUUID)")
List<String> listeLibelleByCodeSprache(List<UUID>  listeUUID);
@Transactional
@Modifying
@Query("select niveau  from Sprache s where s.libelle=:libelle ")
Set<Niveau> getAllNiveauByLibelleSprache(String libelle);
@Transactional
@Query("select codeSprache  from Sprache s where s.libelle=:libelle ")
public UUID getCodeSpracheByLibelle( @Param("libelle") String libelle);

}

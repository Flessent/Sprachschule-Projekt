package dre.org.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dre.org.entities.Kurs;
@Repository
public interface KursRepository extends JpaRepository<Kurs, UUID>{

	

void	deleteKursByCodeKurs(UUID codeKurs);
Kurs getKursByCodeKurs(UUID codeKurs);
@Transactional
@Modifying
@Query("delete from Kurs k where k.codeKurs=:codeKurs")
void deleteKurs(@Param("codeKurs") UUID codeKurs);
@Transactional
@Modifying
@Query(value="select  cast(k.code_kurs as varchar),  cast( n.code_niveau as varchar),cast(r.code_raum as varchar)  ,cast(s.code_sprache as varchar), k.au_programme, k.heure_debut,k.heure_fin, k.professeur from sprache s\r\n"
		+ " inner join kurs k on k.langue=s.code_sprache\r\n"
		+ "		LEFT JOIN kursniveau kn on kn.code_kurs=k.code_kurs left join niveau n on n.code_niveau=kn.code_niveau left  join zeitraum  zt on zt.code_kurs=k.code_kurs\r\n"
		+ "		left join raum r on r.code_raum=zt.code_raum where n.libelle is not null", nativeQuery = true)
List<List<List<String>>> getAllInfosKurs();


@Transactional
@Modifying
@Query(value="select  cast(k.code_kurs as varchar), k.langue,  cast( n.code_niveau as varchar),cast(r.code_raum as varchar)  ,cast(s.code_sprache as varchar), k.au_programme, k.heure_debut,k.heure_fin, k.professeur from sprache s\r\n"
		+ " inner join kurs k on k.langue=s.code_sprache\r\n"
		+ "		LEFT JOIN kursniveau kn on kn.code_kurs=k.code_kurs left join niveau n on n.code_niveau=kn.code_niveau left  join zeitraum  zt on zt.code_kurs=k.code_kurs\r\n"
		+ "		left join raum r on r.code_raum=zt.code_raum where n.libelle is not null", nativeQuery = true)
List<Kurs> getAllKurs();

@Transactional
@Modifying
@Query(value="SELECT cast(code_raum as varchar) from zeitraum  where code_kurs=:code_kurs ",nativeQuery = true)
List< List<String>> getRaumByCodeKurs(@Param("code_kurs") UUID code_kurs);
@Transactional
@Modifying
@Query(value="SELECT cast(code_niveau as varchar) from kursniveau  where code_kurs =:code_kurs ",nativeQuery = true)
 List< List<String>> getNiveauByCodeKurs(@Param("code_kurs") UUID code_kurs);
}

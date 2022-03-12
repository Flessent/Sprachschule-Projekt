package dre.org.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import dre.org.entities.Betreuer;
import dre.org.entities.Personne;
import dre.org.entities.Sekretaer;
@Repository
public interface PersonneRepository <T extends Personne>extends JpaRepository<T, String>{
	@Query("SELECT   P.nom,P.prenom,P.dateInscription from Personne P   WHERE P.username=?1")
	public Page<Personne>  getEinigeInfosUserOnline(String username,Pageable pageable);	
	
	@Transactional
	@Modifying
	@Query("delete from Personne p where p.username=:username")
	void deletePersonne(@Param("username") String username);
		

	@Transactional
	@Modifying
	@Query(value="select"
			+ " p.account_activated,p.password,p.nationalite,p.type_compte,p.num_cni,p.num_tel, p.nbre_mois_experiences,p.last_date_login,p.is_online,p.username,p.nom,p.prenom,p.date_naissance,"
			+ "p.date_inscription,p.profession,p.profil_bild,p.quartier,p.salaire,p.village,"
			+ "p.salaire,p.nbre_mois_experiences,"+ " p.activite_en_paralelle,p.email ,p.diplome_eleve,p.date_debut_activite_ou_cours ,n.code_niveau, s.code_sprache from personne p join  niveaulehrer n"
			+ " on  p.username = n.username_personne \r\n"
			+ "join sprachelehrer s on  p.username = s.username_personne  where p.type_compte=:type", nativeQuery = true)
	public List<Personne>  getBestimmtePersonne(@Param("type") String type);
	
	
	@Transactional
	//@Modifying
	@Query(value="select p.nom_financeur_cours,p.num_tel_financeur_cours,p.nom_pere_ou_tuteur,p.nom_mere_ou_tutrice,p.num_tel_pere_ou_tuteur, p.num_tel_mere_ou_tutrice, p.salaire,p.nbre_mois_experiences,"
			+ "  p.account_activated,p.password,p.num_tel,p.nationalite,p.type_compte,p.num_cni,p.num_tel, p.nbre_mois_experiences,p.last_date_login,p.is_online,\r\n"
			+ "p.username,p.nom,p.prenom,p.date_naissance,p.date_inscription,p.profession,p.profil_bild,p.quartier,p.salaire,p.village,p.langues_sekretaer, p.salaire,\r\n"
			+ "p.nbre_mois_experiences, p.activite_en_paralelle,p.email ,p.diplome_eleve,p.date_debut_activite_ou_cours from personne p   where p.username=:username ", nativeQuery = true)
	public Personne getGemeinsameInfosPersonne(@Param("username") String username);
	
	

}

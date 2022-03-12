package dre.org.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dre.org.entities.Betreuer;
@Repository
public interface BetreuerRepository extends LehrerRepository{

	
	@Transactional
	@Modifying
	@Query(value="select"
			+ " p.account_activated,p.password,p.nationalite,p.type_compte,p.num_cni,p.num_tel, p.nbre_mois_experiences,p.last_date_login,p.is_online,p.username,p.nom,p.prenom,p.date_naissance,"
			+ "p.date_inscription,p.profession,p.profil_bild,p.quartier,p.salaire,p.village,"
			+ "p.salaire,p.nbre_mois_experiences,"+ " p.activite_en_paralelle,p.email ,p.diplome_eleve,p.date_debut_activite_ou_cours ,n.code_niveau, s.code_sprache from personne p join  niveaulehrer n"
			+ " on  p.username = n.username_personne \r\n"
			+ "join sprachelehrer s on  p.username = s.username_personne  where p.type_compte='BETREUER'", nativeQuery = true)
	public List<Betreuer> getAllBetreuer();

	
	
	
}

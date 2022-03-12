package dre.org.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import dre.org.entities.Sekretaer;

public interface SekretaerRepository extends LehrerRepository {
	@Transactional
	@Modifying
	@Query(value="select"
			+ " p.account_activated,p.password,p.nationalite,p.type_compte,p.num_cni,p.num_tel, p.nbre_mois_experiences,p.last_date_login,p.is_online,p.username,p.nom,p.prenom,p.date_naissance,"
			+ "p.date_inscription,p.profession,p.profil_bild,p.quartier,p.salaire,p.village,p.langues_sekretaer"
			+ "p.salaire,p.nbre_mois_experiences,"+ " p.activite_en_paralelle,p.email ,p.diplome_eleve,p.date_debut_activite_ou_cours ,"
			+ " on  p.username = n.username_personne \r\n"
			+ "  where p.type_compte='SEKRETAER'", nativeQuery = true)
	public List<Sekretaer> getAllSekretaer();
}

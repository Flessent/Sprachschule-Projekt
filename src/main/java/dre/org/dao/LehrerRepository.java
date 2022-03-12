package dre.org.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import dre.org.entities.Betreuer;
import dre.org.entities.Lehrer;
import dre.org.entities.Sekretaer;
import dre.org.entities.Student;
public interface LehrerRepository extends PersonneRepository<Lehrer> {

	@Transactional
	@Modifying
	@Query(value="select"
			+ " p.account_activated,p.password,p.nationalite,p.num_cni,p.num_tel, p.nbre_mois_experiences,p.last_date_login,p.is_online,p.username,p.nom,p.prenom,p.date_naissance,"
			+ "p.date_inscription,p.profession,p.profil_bild,p.quartier,p.salaire,p.village,p.nom_financeur_cours,p.date_inscription,"
			+ "p.nom_mere_ou_tutrice,p.nom_pere_ou_tuteur,p.num_tel_mere_ou_tutrice,p.num_tel_pere_ou_tuteur,p.num_tel_financeur_cours,"
			+ " p.activite_en_paralelle,p.email ,p.diplome_eleve,p.date_debut_activite_ou_cours ,n.code_niveau, s.code_sprache from personne p join  niveaulehrer n"
			+ " on  p.username = n.username_personne \r\n"
			+ "join sprachelehrer s on  p.username = s.username_personne  where p.type_compte='STUDENT'", nativeQuery = true)
	public List<Student> getAllStudenten();


	@Transactional
	@Modifying
	@Query(value="select"
			+ " p.account_activated,p.password,p.nationalite,p.type_compte,p.num_cni,p.num_tel, p.nbre_mois_experiences,p.last_date_login,p.is_online,p.username,p.nom,p.prenom,p.date_naissance,"
			+ "p.date_inscription,p.profession,p.profil_bild,p.quartier,p.salaire,p.village,"
			+ "p.salaire,p.nbre_mois_experiences,"+ " p.activite_en_paralelle,p.email ,p.diplome_eleve,p.date_debut_activite_ou_cours ,n.code_niveau, s.code_sprache from personne p join  niveaulehrer n"
			+ " on  p.username = n.username_personne \r\n"
			+ "join sprachelehrer s on  p.username = s.username_personne  where p.type_compte='LEHRER'", nativeQuery = true)
	public List<Lehrer> getAllLehrer();
	
	
	@Transactional
	@Modifying
	@Query(value="select p.account_activated,p.password,p.nationalite,p.type_compte,p.num_cni,p.num_tel, p.nbre_mois_experiences,p.last_date_login,p.is_online,\r\n"
			+ "p.username,p.nom,p.prenom,p.date_naissance,p.date_inscription,p.profession,p.profil_bild,p.quartier,p.salaire,p.village,p.langues_sekretaer, p.salaire,\r\n"
			+ "p.nbre_mois_experiences, p.activite_en_paralelle,p.email ,p.diplome_eleve,p.date_debut_activite_ou_cours from personne p   where p.type_compte='SEKRETAERE'", nativeQuery = true)
	public List<Sekretaer> getAllSekretaer();
	
	
	
	
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

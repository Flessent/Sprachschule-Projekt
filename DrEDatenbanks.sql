-- DB von Sprachzentrum DrE

CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS personne(
type_compte varchar(100),--joue le role de la colonne discriminatoire au niveau de la BD et au niveau hibernate
nom varchar(100) NOT NULL,
prenom varchar(100),
date_naissance timestamp NOT NULL,
profession varchar(100) NOT NULL,
quartier varchar(100) NOT NULL,
num_tel integer NOT NULL,
email varchar(100) NOT NULL,
niveau_langue varchar(100),
diplome_eleve varchar(100),
nom_pere_ou_tuteur varchar(200),
nom_mere_ou_tutrice varchar(200),
num_tel_pere_ou_tuteur integer,
num_tel_mere_ou_tutrice integer,
nom_financeur_cours varchar(200),
num_tel_financeur_cours integer,
num_cni integer,
salaire decimal,
libelle_langue varchar(100),
langues_sekretaer varchar(200),-- langues maitrisees par un/une secretaire quand on le/la recrute
nbre_mois_experiences integer, -- experience du prof,directrice
activite_en_paralelle varchar(100),-- concernant les etudiants, ou autres
nationalite varchar(100) not null,
village varchar(100),
username varchar(100) not null,
password varchar(100) not null,
date_debut_activite_ou_cours date default current_date,--date de debut de l'activité, activité concerne tout le monde ,car activité peut etre cours pr les etufdiants, profs, emploi pour secretaire,etc..
date_inscription timestamp default current_timestamp,
profil_bild varchar(255) not null default 'C:\Users\freun\Desktop\LG\LG1\Gallery\Ma_Vie\IMG-20220102-WA0026.jpg',
account_activated boolean default true, 
lastDateLogin timestamp default current_timestamp,

CONSTRAINT PK_Personne PRIMARY KEY(username),
CONSTRAINT UniciteEmailPersonne UNIQUE(email),
CONSTRAINT UniciteUsernamePersonne UNIQUE(username),
CONSTRAINT UniciteNumTelPersonne UNIQUE(num_tel),
CONSTRAINT UniciteNumTelFinanceur UNIQUE(num_tel_financeur_cours),
CONSTRAINT UniciteNumTelPereOuTuteur UNIQUE(num_tel_pere_ou_tuteur),
CONSTRAINT UniciteNumTelMereOuTutrice UNIQUE(num_tel_mere_ou_tutrice),
CONSTRAINT UniciteNumCni UNIQUE(num_cni)




);

CREATE TABLE IF NOT EXISTS niveau(
	code_niveau  UUID NOT NULL DEFAULT uuid_generate_v1() , 
libelle varchar(100) not null,
prix integer NOT null,
date_debut DATE default CURRENT_DATE NOT null,
date_fin DATE default CURRENT_DATE NOT null,
CONSTRAINT PK_niveau PRIMARY KEY(code_niveau),
CONSTRAINT UniciteNiveau UNIQUE(libelle)
);
CREATE SEQUENCE seq_sprache;
CREATE TABLE IF NOT EXISTS sprache(
	code_sprache  UUID NOT NULL DEFAULT uuid_generate_v1() , 
libelle varchar(100) not null,
intensive boolean  default false NOT null,
CONSTRAINT PK_sprache PRIMARY KEY(code_sprache),
CONSTRAINT UniciteLibelle UNIQUE(libelle)
);

CREATE TABLE IF NOT EXISTS kurs(
	code_kurs  UUID NOT NULL DEFAULT uuid_generate_v1() , 
--raum varchar(200) NOT null,
	langue  UUID NOT NULL DEFAULT uuid_generate_v1() , 
au_programme varchar(250) NOT null,
heure_debut timestamp default current_timestamp NOT null,
heure_fin timestamp default current_timestamp NOT null,
professeur varchar(200) NOT null,
constraint UniciteSpracheProgrammeHeureDebutHeureFinProfesseur UNIQUE(langue,au_programme,heure_debut,professeur,heure_fin),
CONSTRAINT FK_langue  FOREIGN KEY(langue) REFERENCES sprache(code_sprache) on delete cascade on update cascade,
CONSTRAINT PK_Kurs PRIMARY KEY(code_kurs)

);
CREATE TABLE IF NOT EXISTS SpracheLehrer(
username_personne varchar(100) NOT NULL , 
code_sprache UUID NOT NULL ,

CONSTRAINT FK_sprache FOREIGN KEY(code_sprache) REFERENCES sprache(code_sprache) on delete cascade on update cascade,
CONSTRAINT FK_lehrer FOREIGN KEY(username_personne) REFERENCES personne(username) on delete cascade on update cascade,
CONSTRAINT PK_SpracheLehrer PRIMARY KEY (username_personne,code_sprache)
);

CREATE TABLE IF NOT EXISTS SpracheBetreuer(
username_personne varchar(100) NOT NULL, 
code_sprache UUID NOT NULL ,

CONSTRAINT FK_sprache FOREIGN KEY(code_sprache) REFERENCES sprache(code_sprache) on delete cascade on update cascade,
CONSTRAINT FK_betreuer FOREIGN KEY(username_personne) REFERENCES personne(username) on delete cascade on update cascade,
CONSTRAINT PK_SpracheBetreuer PRIMARY KEY (username_personne,code_sprache)


);

CREATE TABLE IF NOT EXISTS NiveauStudent(
username_personne varchar(100) NOT NULL, 
code_niveau UUID NOT NULL ,

CONSTRAINT FK_niveau FOREIGN KEY(code_niveau) REFERENCES niveau(code_niveau) on delete cascade on update cascade,
CONSTRAINT FK_student FOREIGN KEY(username_personne) REFERENCES personne(username) on delete cascade on update cascade,
CONSTRAINT PK_NiveauStudent PRIMARY KEY (username_personne,code_niveau)


);
CREATE TABLE IF NOT EXISTS NiveauLehrer(
username_personne varchar(100) NOT NULL, 
code_niveau UUID NOT NULL ,

CONSTRAINT FK_niveau FOREIGN KEY(code_niveau) REFERENCES niveau(code_niveau) on delete cascade on update cascade,
CONSTRAINT FK_lehrer FOREIGN KEY(username_personne) REFERENCES personne(username) on delete cascade on update cascade,
CONSTRAINT PK_NiveauLehrer PRIMARY KEY (username_personne,code_niveau)


);

CREATE TABLE IF NOT EXISTS NiveauBetreuer(
username_personne varchar(100) NOT NULL, 
code_niveau UUID NOT NULL ,

CONSTRAINT FK_niveau FOREIGN KEY(code_niveau) REFERENCES niveau(code_niveau) on delete cascade on update cascade,
CONSTRAINT FK_betreuer FOREIGN KEY(username_personne) REFERENCES personne(username) on delete cascade on update cascade,
CONSTRAINT PK_NiveauBetreuer PRIMARY KEY (username_personne,code_niveau)


);



CREATE TABLE IF NOT EXISTS KursPersonne(
username_personne varchar(100) NOT NULL default 'flexeUnd@merkel', 
code_kurs UUID NOT NULL default uuid_generate_v1(),

CONSTRAINT FK_kurs FOREIGN KEY(code_kurs) REFERENCES kurs(code_kurs) on delete cascade on update cascade,
CONSTRAINT FK_personne FOREIGN KEY(username_personne) REFERENCES personne(username) on delete cascade on update cascade,
CONSTRAINT PK_KursPersonne PRIMARY KEY (username_personne,code_kurs)


);


CREATE SEQUENCE seq_role;
CREATE TABLE IF NOT EXISTS  roles(
	code_role UUID default uuid_generate_v1(),
role varchar(100) not null,
description text not null,
id_role integer default nextval('seq_role'),

CONSTRAINT UniciteRole UNIQUE(role),
CONSTRAINT PK_Role PRIMARY KEY (role),
CONSTRAINT UniciteCodeRole UNIQUE(code_role)

);
 --create table doctor_patient (dcode int references doctor(dcode) on delete cascade on update cascade ,pcode int references patient(pcode) on delete cascade on update cascade)


CREATE TABLE IF NOT EXISTS  personne_roles(
	username varchar(100) not null,
code_role UUID not null,

CONSTRAINT FK_personne FOREIGN KEY(username) REFERENCES personne(username) on delete cascade on update cascade,
CONSTRAINT FK_role FOREIGN KEY(code_role) REFERENCES roles(code_role) on delete cascade on update cascade,
CONSTRAINT PK_rolePersonne PRIMARY KEY(username,code_role),
CONSTRAINT UniciteRolePersonneUsername UNIQUE(username,code_role)

);




CREATE TABLE IF NOT EXISTS raum(
	code_raum  UUID NOT NULL DEFAULT uuid_generate_v1() , 
nom_raume varchar(100) not null,
nbre_places integer,
CONSTRAINT PK_raum PRIMARY KEY(code_raum)
);





CREATE TABLE IF NOT EXISTS zeitraum(
	code_kurs  UUID NOT NULL DEFAULT uuid_generate_v1() , 
	code_raum  UUID NOT NULL DEFAULT uuid_generate_v1() , 
CONSTRAINT FK_raum FOREIGN KEY(code_raum) REFERENCES raum(code_raum)  on delete cascade on update cascade,
CONSTRAINT FK_kurs FOREIGN KEY(code_kurs) REFERENCES kurs(code_kurs)  on delete cascade on update cascade,
CONSTRAINT PK_zeitraum PRIMARY KEY (code_kurs,code_raum) 
);

CREATE TABLE IF NOT EXISTS kursniveau(
code_kurs  UUID NOT NULL , 
code_niveau  UUID NOT NULL, 
CONSTRAINT FK_kurs FOREIGN KEY(code_kurs) REFERENCES kurs(code_kurs)  on delete cascade on update cascade,
CONSTRAINT FK_niveau FOREIGN KEY(code_niveau) REFERENCES niveau(code_niveau)  on delete cascade on update cascade,
CONSTRAINT PK_KursNiveau PRIMARY KEY(code_kurs,code_niveau) 

);

CREATE TABLE IF NOT EXISTS sprachniveau(
code_sprache  UUID NOT NULL  ,
code_niveau UUID NOT NULL  ,
CONSTRAINT FK_sprache FOREIGN KEY(code_sprache) REFERENCES sprache(code_sprache) on delete cascade on update cascade,
CONSTRAINT FK_niveau FOREIGN KEY(code_niveau) REFERENCES niveau(code_niveau) on delete cascade on update cascade,
CONSTRAINT PK_sprachniveau PRIMARY KEY(code_niveau,code_sprache)
);


-- -------------------------------------------Function Management-----------------------------------------------------------------------------------

create or replace function crypt_password() returns trigger as $$
begin
update personne set password = crypt(NEW.username, gen_salt('bf', 8)  ) where username=NEW.username;
insert into personne_roles values(NEW.username,NEW.type_compte);
RETURN NEW;
end;
$$ language plpgsql;
-- --------------------------------Trigger ------------------------------------------------------------------------------------------------------
create trigger crypt_password_trigger
after insert on personne 
for each row execute procedure crypt_password();








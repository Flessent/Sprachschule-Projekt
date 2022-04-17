package dre.org.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import dre.org.dao.LehrerRepository;
import dre.org.dao.NiveauRepository;
import dre.org.dao.RolesRepository;
import dre.org.dao.SpracheRepository;
import dre.org.dto.LehrerDTO;
import dre.org.dto.PersonneDTO;
import dre.org.entities.Betreuer;
import dre.org.entities.Lehrer;
import dre.org.entities.Niveau;
import dre.org.entities.Personne;
import dre.org.entities.Roles;
import dre.org.entities.Sekretaer;
import dre.org.entities.Sprache;
import dre.org.entities.Student;
import dre.org.services.LehrerServices;
import dre.org.services.NiveauServices;
import dre.org.services.PersonneServices;
import dre.org.services.RolesServices;
import dre.org.services.SpracheServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@NoArgsConstructor
@Transactional
public class LehrerServicesImplementation implements LehrerServices{

@Autowired
private  LehrerRepository lehrerRepository;
@Autowired
private SpracheServices spracheServices;
@Autowired
private NiveauServices niveauServices;

@Autowired
private ModelMapper modelMapper;


@Autowired
private  RolesServices rolesServices;
/*
public LehrerServicesImplementation(LehrerRepository lehrerRepository,SpracheServices spracheServices, NiveauServices niveauServices,
		RolesServices rolesServices,ModelMapper modelMapper) {
	this.lehrerRepository=lehrerRepository;
	this.modelMapper=modelMapper;
	this.rolesServices=rolesServices;
	this.niveauServices=niveauServices;
	this.spracheServices=spracheServices;
}*/

public Converter<Niveau, UUID> uuidConverterNiveau = new AbstractConverter<Niveau, UUID>() {
    public UUID convert(Niveau source) {
        return UUID.fromString(source.getCodeNiveau().toString());
    }
};
public Converter<Sprache, UUID> uuidConverterSprache = new AbstractConverter<Sprache, UUID>() {
    protected UUID convert(Sprache source) {
        return UUID.fromString(source.getCodeSprache().toString());
    }
};
public Converter<Roles, UUID> uuidConverterRole = new AbstractConverter<Roles, UUID>() {
    protected UUID convert(Roles role) {
        return UUID.fromString(role.getCodeRole().toString());
    }
};

@Override
public  LehrerDTO convertEntityToDto(Lehrer lehrer){
	modelMapper.addConverter(uuidConverterSprache);
	modelMapper.addConverter(uuidConverterNiveau);
	modelMapper.addConverter(uuidConverterRole);
  	modelMapper.getConfiguration()
	.setMatchingStrategy(MatchingStrategies.LOOSE)
	  .setFieldMatchingEnabled(true);

  	LehrerDTO lehrerDTO=new LehrerDTO();

	lehrerDTO=modelMapper.map(lehrer, LehrerDTO.class);

	return lehrerDTO;
} 



@Override
public  Lehrer convertDtoToEntity(LehrerDTO lehrerDTO){

    modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.LOOSE);
    Lehrer lehrer = new Lehrer() ;
    lehrer = modelMapper.map(lehrerDTO, Lehrer.class);
    Set<Niveau> listeNiveau=niveauServices.getAllNiveauByUUID(lehrerDTO.getNiveau());
    lehrer.setNiveau(listeNiveau);    
    Set<Sprache> sprache = spracheServices.getAllSprachenByCodeSprachen(lehrerDTO.getSprache());
    lehrer.setSprache(sprache);
    Set<Roles> roles=rolesServices.getAllRolesByUUID(lehrerDTO.getRoles());
    lehrer.setRoles(roles);
    return lehrer;
}






	@Override
	public int saveLehrer(Lehrer lehrer){
		try {
			lehrerRepository.save(lehrer);
	        System.out.println("Voici le lehrer"+lehrer);

			return 0;
			
		} catch (Exception e) {
			
	        System.out.println("Voici les erreurs"+e.getMessage());

		      }

			return 1;
		}
	

	@Override
	public int updateLehrer(Lehrer lehrer) {
try {
	Lehrer updatedLehrer=this.lehrerRepository.save(lehrer);
	return 0;
} catch (Exception e) {
return 1;
}
		
	}

	@Override
	public int deletePersonne(String username) {
try {
	lehrerRepository.deletePersonne(username);// deletePersonne() kommt aus  PersonneRepository (Vererbung der PersonneRepository von LehrerRepository)
return 0;
} catch (Exception e) {
	return 1;
}		
	}


	@Override
	public  List<Lehrer> getListLehrer() {
		
		return lehrerRepository.getAllLehrer();
	}

	@Override
	public List<Sekretaer> getListSekretaer() {
		
		return lehrerRepository.getAllSekretaer();
	}

	@Override
	public List<Betreuer> getListBetreuer() {
		
		return lehrerRepository.getAllBetreuer();
	}

	@Override
	public List<Student> getListStudent() {
		
		return lehrerRepository.getAllStudenten();
	}

 



}

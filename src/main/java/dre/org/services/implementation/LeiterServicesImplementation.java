package dre.org.services.implementation;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dre.org.dao.LeiterRepository;
import dre.org.dto.LeiterDTO;
import dre.org.entities.Lehrer;
import dre.org.entities.Leiter;
import dre.org.entities.Niveau;
import dre.org.entities.Roles;
import dre.org.entities.Sprache;
import dre.org.services.LehrerServices;
import dre.org.services.LeiterServices;
import dre.org.services.NiveauServices;
import dre.org.services.RolesServices;
import dre.org.services.SpracheServices;

@Service
public class LeiterServicesImplementation implements LeiterServices{
	@Autowired
	private  LeiterRepository leiterRepository;
	@Autowired
	private SpracheServices spracheServices;
	@Autowired
	private NiveauServices niveauServices;

	@Autowired
	private ModelMapper modelMapper;

@Autowired
private LehrerServices lehrerServices;
	@Autowired
	private  RolesServices rolesServices;

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
	public  LeiterDTO convertEntityToDto(Leiter leiter){
		modelMapper.addConverter(uuidConverterSprache);
		modelMapper.addConverter(uuidConverterNiveau);
		modelMapper.addConverter(uuidConverterRole);
	  	modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE)
		  .setFieldMatchingEnabled(true);

	  	LeiterDTO leiterDTO=new LeiterDTO();

		leiterDTO=modelMapper.map(leiter, LeiterDTO.class);

		return leiterDTO;
	} 



	@Override
	public  Leiter convertDtoToEntity(LeiterDTO leiterDTO){

	    modelMapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.LOOSE);
	    Leiter leiter = new Leiter() ;
	    leiter = modelMapper.map(leiterDTO, Leiter.class);
	    Set<Niveau> listeNiveau=niveauServices.getAllNiveauByUUID(leiterDTO.getNiveau());
	    leiter.setNiveau(listeNiveau);    
	    Set<Sprache> sprache = spracheServices.getAllSprachenByCodeSprachen(leiterDTO.getSprache());
	    leiter.setSprache(sprache);
	    Set<Roles> roles=rolesServices.getAllRolesByUUID(leiterDTO.getRoles());
	    leiter.setRoles(roles);
	    return leiter;
	}


	@Override
	public int saveLeiter(Leiter leiter) {
		//lehrer = new Leiter();
		return  this.lehrerServices.saveLehrer(leiter);
	}



	@Override
	public List<Leiter> getAllLeiter() {
		
		return leiterRepository.getAllLeiter();
	}		

}

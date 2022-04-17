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

import dre.org.dao.BetreuerRepository;
import dre.org.dto.BetreuerDTO;
import dre.org.entities.Betreuer;
import dre.org.entities.Niveau;
import dre.org.entities.Roles;
import dre.org.entities.Sprache;
import dre.org.services.BetreuerServices;
import dre.org.services.LehrerServices;
import dre.org.services.NiveauServices;
import dre.org.services.RolesServices;
import dre.org.services.SpracheServices;

@Service
public class BetreuerServicesImplementation implements BetreuerServices {
@Autowired
BetreuerRepository betreuerRepository;
@Autowired
private LehrerServices lehrerServices;
@Autowired
private RolesServices rolesServices;
	@Autowired
	public SpracheServices spracheServices;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	public NiveauServices niveauServices;
	Converter<Niveau, UUID> uuidConverterNiveau = new AbstractConverter<Niveau, UUID>() {
	    protected UUID convert(Niveau source) {
	        return UUID.fromString(source.getCodeNiveau().toString());
	    }
	};
	Converter<Sprache, UUID> uuidConverterSprache = new AbstractConverter<Sprache, UUID>() {
	    protected UUID convert(Sprache source) {
	        return UUID.fromString(source.getCodeSprache().toString());
	    }
	};
	
	
	@Override
    public BetreuerDTO convertEntityToDto(Betreuer betreuer){
    	modelMapper.addConverter(uuidConverterSprache);
    	modelMapper.addConverter(uuidConverterNiveau);

    	modelMapper.getConfiguration()
    	.setMatchingStrategy(MatchingStrategies.LOOSE)
    	  .setFieldMatchingEnabled(true);

    	BetreuerDTO betreuerDTO=new BetreuerDTO();

    	betreuerDTO=modelMapper.map(betreuer, BetreuerDTO.class);

    	return betreuerDTO;
    } 

   
   
    @Override
    public Betreuer convertDtoToEntity(BetreuerDTO betreuerDTO){
    	//modelMapper.addConverter(convert(spracheDTO.getCodeSprache())  );

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Betreuer betreuer = new Betreuer();
        betreuer = modelMapper.map(betreuerDTO, Betreuer.class);
        Set<Niveau> listeNiveau=niveauServices.getAllNiveauByUUID(betreuerDTO.getNiveau());
        betreuer.setNiveau(listeNiveau);    
        Set<Sprache> sprache = spracheServices.getAllSprachenByCodeSprachen(betreuerDTO.getSprache());
        betreuer.setSprache(sprache);
        Set<Roles> roles=rolesServices.getAllRolesByUUID(betreuerDTO.getRoles());
        betreuer.setRoles(roles);
        
        return betreuer;
    }






	@Override
	public int saveBetreuer(Betreuer betreuer) {
		return this.lehrerServices.saveLehrer(betreuer);
	
	}
	@Override
	public List<Betreuer> getAllBetreuer() {
		return betreuerRepository.getAllBetreuer();
	}
	@Override
	public int updateBetreuer(Betreuer betreuer) {
		return this.lehrerServices.updateLehrer(betreuer);
	}
	@Override
	public void deletePersonne(String username) {
		this.lehrerServices.deletePersonne(username);
	}
	
	

}

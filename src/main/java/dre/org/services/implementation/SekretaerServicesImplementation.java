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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import dre.org.dao.SekretaerRepository;
import dre.org.dto.SekretaerDTO;
import dre.org.entities.Betreuer;
import dre.org.entities.Lehrer;
import dre.org.entities.Roles;
import dre.org.entities.Sekretaer;
import dre.org.entities.Sprache;
import dre.org.entities.Student;
import dre.org.services.LehrerServices;
import dre.org.services.RolesServices;
import dre.org.services.SekretaerServices;

@Service
public class SekretaerServicesImplementation  implements SekretaerServices {
	@Autowired
	private SekretaerRepository sekretaerRepository;
@Autowired
private LehrerServices lehrerServices;



@Autowired
private RolesServices rolesServices;
	
	@Autowired
	private ModelMapper modelMapper;
	
	Converter<Roles, UUID> uuidConverterRoles = new AbstractConverter<Roles, UUID>() {
		protected UUID convert(Roles source) {
		    return UUID.fromString(source.getCodeRole().toString());
		}
		};
	@Override
	public SekretaerDTO convertEntityToDto(Sekretaer sekretaer){
		modelMapper.addConverter(uuidConverterRoles);
      	modelMapper.getConfiguration()
    	.setMatchingStrategy(MatchingStrategies.LOOSE)
    	  .setFieldMatchingEnabled(true);

      	SekretaerDTO sekretaerDTO=new SekretaerDTO();

      	sekretaerDTO=modelMapper.map(sekretaer, SekretaerDTO.class);

    	return sekretaerDTO;
    } 

   
   
    
	@Override
    public Sekretaer convertDtoToEntity(SekretaerDTO sekretaerDTO){

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Sekretaer sekretaer = new Sekretaer() ;
      //sekretaer.setLanguesSekretaer(sekretaerDTO.getLanguesSekretaer().toString());
        sekretaer = modelMapper.map(sekretaerDTO, Sekretaer.class);
        Set<Roles> roles=rolesServices.getAllRolesByUUID(sekretaerDTO.getRoles());
        sekretaer.setRoles(roles);
        return sekretaer;
    }
	


	@Override
	public int saveSekretaer(Sekretaer sekretaer) {
		return lehrerServices.saveLehrer(sekretaer);
	}

	@Override
	public List<Sekretaer> getListSekretaer() {
		
		return lehrerServices.getListSekretaer();
	}

	@Override
	public void deleteSekretaer(String username) {
		sekretaerRepository.deletePersonne( username);
	}

	@Override
	public Sekretaer updateSekretaer(Sekretaer sekretaer) {
		Sekretaer updateSekretaer = sekretaerRepository.save(sekretaer);
		
		return updateSekretaer;
	}



	







}

package dre.org.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dre.org.dto.LehrerDTO;
import dre.org.entities.Lehrer;
import dre.org.entities.Niveau;
import dre.org.entities.Roles;
import dre.org.entities.Sprache;
import dre.org.services.LehrerServices;
import dre.org.services.NiveauServices;
import dre.org.services.RolesServices;
import dre.org.services.SpracheServices;
@SpringBootTest

public class LehrerRepositoryTest {

	/*
	 * 
	 This Testclass work well with JUNIT5. But we want to test it with Mockito.
	 */
	
	/*
	@Autowired
	private RolesServices rolesServices;
	@Autowired
	private NiveauServices niveauServices;
	@Autowired
	private SpracheServices spracheServices;
	
	@Autowired
	private LehrerServices lehrerServices;
	@Autowired
	private ModelMapper modelMapper;
	 public  Lehrer convertDtoToEntity(LehrerDTO lehrerDTO){

	        this.modelMapper.getConfiguration()
	                .setMatchingStrategy(MatchingStrategies.LOOSE);
	        Lehrer lehrer = new Lehrer() ;
	        lehrer = this.modelMapper.map(lehrerDTO, Lehrer.class);
	        Set<Niveau> listeNiveau=niveauServices.getAllNiveauByUUID(lehrerDTO.getNiveau());
	        lehrer.setNiveau(listeNiveau);    
	        Set<Sprache> sprache = spracheServices.getAllSprachenByCodeSprachen(lehrerDTO.getSprache());
	        lehrer.setSprache(sprache);
	        Set<Roles> roles=rolesServices.getAllRolesByUUID(lehrerDTO.getRoles());
	        lehrer.setRoles(roles);
	        return lehrer;
	    }
	
		    public UUID convert(Sprache source) {
		        return UUID.fromString(source.getCodeSprache().toString());
		    }
		    protected UUID convert(Roles source) {
		        return UUID.fromString(source.getCodeRole().toString());
		    }
		    protected UUID convert(Niveau source) {
		        return UUID.fromString(source.getCodeNiveau().toString());
		    }
		
	
	 private LehrerDTO lehrerDTO;
	 private  BigDecimal salaire=new BigDecimal(3238293); 
	 Long datetime = System.currentTimeMillis();
     Timestamp timestamp= new Timestamp(datetime);
     Date heute = new Date(0);
     HashSet<UUID> niveau = new HashSet<UUID>();
		HashSet<UUID> sprache = new HashSet<UUID>();
		   HashSet<UUID> 	roles = new HashSet<UUID> ();
		   Sprache spraches = new Sprache(UUID.fromString("610660d9-691a-4937-84d4-80fd3a2ef850"));
			Niveau niveaus= new Niveau(UUID.fromString("99639e27-d860-4e82-824e-5f9672601b8f"));
			Roles role= new Roles(UUID.fromString("aafe6f7a-9e21-11ec-b154-744ca1ba4869"));  

	@Test
	public void saveLehrerTest() {
	
		sprache.add(convert(spraches) );
		niveau.add(convert(niveaus));
      roles.add(convert(role));
       
      

    lehrerDTO =new LehrerDTO( "merkel@dre.org","Nzeunga", "Merkel",heute, "Kamerun","Ärtzin",  "Terminus", "+491521654",  "merkel@gmail.com", "CNI_Merkel002222",
			 "passwordMerkel", "Doktor", heute, "Landwirtin",true ,  timestamp  , sprache,  niveau, 24, salaire,   roles);
   
   int result=lehrerServices.saveLehrer(convertDtoToEntity(lehrerDTO)  ); 
assertThat(0).isEqualTo(result);
	
	}
	
	@Test
	public void updateLehrerTest() {
	
		sprache.add(convert(spraches) );
		niveau.add(convert(niveaus));
      roles.add(convert(role));
       
    lehrerDTO =new LehrerDTO( "merkel@dre.org","Nzeunga", "Merkel",heute, "Kamerun","Ärtzin",  "Terminus", "+491521654",  "merkel@gmail.com", "CNI_Merkel002222",
			 "passwordMerkel", "Doktor", heute, "Landwirtin",true ,  timestamp  , sprache,  niveau, 24, salaire,   roles);
   
	lehrerDTO.setProfession("ZahnÄrtzin");
		int result= lehrerServices.updateLehrer(convertDtoToEntity(lehrerDTO));
		assertThat(0).isEqualTo(result);
	}
	
	@Test
	@AfterEach  
	public void deleteLehrerTest() {
		int result = lehrerServices.deletePersonne("merkel@dre.org");
		assertThat(0).isEqualTo(result);
	}
	
	*/
}

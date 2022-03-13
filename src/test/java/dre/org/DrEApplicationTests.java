package dre.org;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dre.org.controller.LehrerRestService;
import dre.org.dao.NiveauRepository;
import dre.org.dao.RolesRepository;
import dre.org.dao.SpracheRepository;
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
class DrEApplicationTests {
	@Test
	public void contextLoads() {
		assertEquals(true, true);
	}

	@Test
	void CalculTest() {
		int a=5; int b=10;
		Calculator calcul = new Calculator();
		assertThat(15).isEqualTo(calcul.summe(a, b));
	}

	class Calculator{
		int summe(int a,int b) {
			return a+b;
		}
	}
	
	@Autowired
	private RolesServices rolesServices;
	@Autowired
	private NiveauServices niveauServices;
	@Autowired
	private SpracheServices spracheServices;
	
	@Autowired
	private NiveauRepository niveauRepository;
	@Autowired
	private SpracheRepository spracheRepository;
	@Autowired
	private RolesRepository rolesRepository;
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
	
	
	
	
	
	@Test
	public void saveLehrerTest() {
		//String date="2022-03-11T17:31:47.024Z";
		//String niv="7bf7bd4f-60dc-47c1-8238-834da753545c";
		//Date dateDebutActiviteOuCours= Date.valueOf(date);
		HashSet<UUID> niveau = new HashSet<UUID>();
		HashSet<UUID> sprache = new HashSet<UUID>();
		sprache.add(spracheRepository.getCodeSpracheByLibelle("Englischmmm"));
		niveau.add(niveauRepository.getCodeNiveauByLibelle("C2"));
   HashSet<UUID> 	roles = new HashSet<UUID> ();
      roles.add(rolesRepository.getCodeRoleByRole("LEHRER"));
      Long datetime = System.currentTimeMillis();
      Timestamp timestamp= new Timestamp(datetime);
      Date heute = new Date(0); 
  BigDecimal salaire=new BigDecimal(3238293); 
      
      LehrerRestService lehrerRestService = new LehrerRestService();
      //		 List<LehrerDTO> listLehrerDTO =lehrerServices.getListLehrer().stream().map(this::convertEntityToDto).collect(Collectors.toList());

   LehrerDTO lehrerDTO =new LehrerDTO( "merkel@dre.org","Nzeunga", "Merkel",heute, "Kamerun","Ärtzin",  "Terminus", "+491521654",  "merkel@gmail.com", "CNI_Merkel002222",
			 "passwordMerkel", "Doktor", heute, "Landwirtin",true ,  timestamp  , sprache,  niveau, 24, salaire,   roles);
   
   int result=lehrerServices.saveLehrer(convertDtoToEntity(lehrerDTO)  ); 
assertThat(0).isEqualTo(result);
	
	}
	
	
	
	
}

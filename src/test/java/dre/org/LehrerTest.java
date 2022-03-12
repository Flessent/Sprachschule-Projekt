package dre.org;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import dre.org.DrEApplication;
import dre.org.controller.LehrerRestService;
import dre.org.dao.NiveauRepository;
import dre.org.dao.RolesRepository;
import dre.org.dao.SpracheRepository;
import dre.org.dto.LehrerDTO;
import dre.org.services.LehrerServices;
import dre.org.services.implementation.LehrerServicesImplementation;

@SpringBootTest(classes = DrEApplication.class)

@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class LehrerTest{

	public LehrerTest(String nom) {
		
	}
	
	@Autowired
	private NiveauRepository niveauRepository;
	@Autowired
	private SpracheRepository spracheRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private LehrerServices lehrerServices;
	
	
	@Test
	public void saveLehrerTest() {
		//String date="2022-03-11T17:31:47.024Z";
		//String niv="7bf7bd4f-60dc-47c1-8238-834da753545c";
		//Date dateDebutActiviteOuCours= Date.valueOf(date);
		HashSet<UUID> niveau = new HashSet<UUID>();
		HashSet<UUID> sprache = new HashSet<UUID>();
		sprache.add(spracheRepository.getCodeSpracheByLibelle("Englisch"));
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
   
   int result=lehrerServices.saveLehrer( lehrerRestService.convertDtoToEntity(lehrerDTO)  ); 
assertThat(0).isEqualTo(result);
	
	}
	
	
}

package dre.org.serivices.implementation;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import dre.org.dao.LehrerRepository;
import dre.org.entities.Lehrer;
import dre.org.entities.Niveau;
import dre.org.entities.Roles;
import dre.org.entities.Sprache;
import dre.org.services.NiveauServices;
import dre.org.services.RolesServices;
import dre.org.services.SpracheServices;
import dre.org.services.implementation.LehrerServicesImplementation;

//@ContextConfiguration
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@Execution(ExecutionMode.SAME_THREAD) // <--- Workaround
class LehrerServicesImplementationTest {
	
	
	 private Lehrer lehrer;
	 
	 @InjectMocks
	 private LehrerServicesImplementation lehrerServicesImplementation;
	 
	@MockBean
	private RolesServices rolesServices;
	@MockBean
	private NiveauServices niveauServices;
	@MockBean
	private SpracheServices spracheServices;
	
	@Mock
	private LehrerRepository lehrerRepository;
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private Converter<Niveau, UUID> uuidConverterNiveau;
	@Mock
	private Converter<Sprache, UUID> uuidConverterSprache;
	@Mock
	private Converter<Roles, UUID> uuidConverterRoles;

	
	@BeforeEach
	public void setup() {
	//Mockito.reset(lehrerServicesImplementation);
    MockitoAnnotations.openMocks(this);

	lehrerServicesImplementation=new LehrerServicesImplementation(lehrerRepository, spracheServices, niveauServices, modelMapper,
			rolesServices, uuidConverterNiveau, uuidConverterSprache, uuidConverterRoles);
			
	}
	
	
	
		    public Sprache convertUUIDToSprache(UUID  source) {
		        return new Sprache(UUID.fromString("610660d9-691a-4937-84d4-80fd3a2ef850"));
		    }
		    protected Roles convertUUIDToRoles(UUID source) {
		        return new Roles(UUID.fromString("aafe6f7a-9e21-11ec-b154-744ca1ba4869"));
		    }
		    protected Niveau convertUUIDToNiveau(UUID source) {
		        return new Niveau(UUID.fromString("99639e27-d860-4e82-824e-5f9672601b8f"));
		    }
		
	
	 private  BigDecimal salaire=new BigDecimal(3238293); 
	 Long datetime = System.currentTimeMillis();
     Timestamp timestamp= new Timestamp(datetime);
     Date heute = new Date(0);
     HashSet<Niveau> niveau = new HashSet<Niveau>();
		HashSet<Sprache> sprache = new HashSet<Sprache>();
		   HashSet<Roles> 	roles = new HashSet<Roles> ();
		   Sprache spraches = new Sprache(UUID.fromString("610660d9-691a-4937-84d4-80fd3a2ef850"));
			Niveau niveaus= new Niveau(UUID.fromString("99639e27-d860-4e82-824e-5f9672601b8f"));
			Roles role= new Roles(UUID.fromString("aafe6f7a-9e21-11ec-b154-744ca1ba4869"));  

}

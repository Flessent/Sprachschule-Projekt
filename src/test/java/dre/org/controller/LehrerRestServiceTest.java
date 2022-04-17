package dre.org.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import dre.org.dao.LehrerRepository;
import dre.org.dto.LehrerDTO;
import dre.org.entities.Lehrer;
import dre.org.entities.Niveau;
import dre.org.entities.Roles;
import dre.org.entities.Sprache;
import dre.org.services.LehrerServices;
import dre.org.services.implementation.LehrerServicesImplementation;


//@WebMvcTest(controllers =  LehrerRestService.class ,value = LehrerRestService.class )// only the Controller is tested unlike all the app to launch
//@RunWith(SpringRunner.class)//we used SpringRunner to launch Spring TestContext framework with the @RunWith annotation.
//@ContextConfiguration(classes = DrEApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LehrerRestServiceTest {
	private LehrerServicesImplementation lehrerServicesImplementation;
    @Autowired
	private MockMvc mockMvc;//we autowired MockMvc to execute requests, it is basically an entry point for Server Side test

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Mock
    private LehrerServices lehrerServices;/*we are creating MockBean of LehrerService for the unit tests of LehrerRestService(Controller)
	it will add mock of the LehrerService to the ApplicationContext.*/
    @InjectMocks
    private LehrerRestService lehrerRestService;
	@Mock
	private LehrerRepository lehrerRepository;
	  @BeforeEach
	    public void setup() {
		
		  Mockito.reset(lehrerServices);
		  MockMvcBuilders
          .webAppContextSetup(webApplicationContext)
          .apply(springSecurity())
          .build();
		  
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
	/*	private  BigDecimal salaire=new BigDecimal(3238293); 
		 Long datetime = System.currentTimeMillis();
	    Timestamp timestamp= new Timestamp(datetime);
	    Date heute = new Date(0);
	    HashSet<Niveau> niveau = new HashSet<Niveau>();
			HashSet<Sprache> sprache = new HashSet<Sprache>();
			   HashSet<Roles> 	roles = new HashSet<Roles> ();
			   Sprache spraches = new Sprache(UUID.fromString("610660d9-691a-4937-84d4-80fd3a2ef850"));
				Niveau niveaus= new Niveau(UUID.fromString("99639e27-d860-4e82-824e-5f9672601b8f"));
				Roles role= new Roles(UUID.fromString("aafe6f7a-9e21-11ec-b154-744ca1ba4869"), "LEHRER", "Rolle eines Lehres");  */
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

				
				@WithMockUser("spring")
				@Test
				public void saveLehrerTest() throws Exception{
					/*
					sprache.add( new Sprache(UUID.fromString("610660d9-691a-4937-84d4-80fd3a2ef850")) );
					niveau.add(new Niveau(UUID.fromString("99639e27-d860-4e82-824e-5f9672601b8f")));
			      roles.add(new Roles(UUID.fromString("aafe6f7a-9e21-11ec-b154-744ca1ba4869")));
*/
					sprache.add(convert(spraches) );
					niveau.add(convert(niveaus));
			      roles.add(convert(role));
				     /* lehrer =new Lehrer( "merkel@dre.org","Nzeunga", "Merkel",heute, "Kamerun","Ärtzin",  "Terminus", "+491521654",  "merkel@gmail.com",
				    		  "CNI_Merkel002222","merkel007", "Doktor", heute, "Landwirtin",true ,  sprache, niveau, 30, salaire,    roles);*/
					//Mockito.when(lehrerServices.saveLehrer(lehrer)).thenReturn(0);
			     lehrerDTO= new LehrerDTO( "merkel@dre.org","Nzeunga", "Merkel",heute, "Kamerun","Ärtzin",  "Terminus", "+491521654",  "merkel@gmail.com", "CNI_Merkel002222",
							 "passwordMerkel", "Doktor", heute, "Landwirtin",true ,  timestamp  , sprache,  niveau, 24, salaire,   roles);
					RequestBuilder requestBuilder = MockMvcRequestBuilders.post( "/personne/lehrer/saveLehrer")
							.content(asJsonString(lehrerDTO))
						      .contentType(MediaType.APPLICATION_JSON_VALUE)
						      .accept(MediaType.APPLICATION_JSON_VALUE)							
						      .with(user("flessent@dre.org").password("merkel007").roles("LEITER"))
							.accept(MediaType.APPLICATION_JSON_VALUE);
					ResultActions resultActions= mockMvc.perform(requestBuilder)
							.andExpect(MockMvcResultMatchers.status().isCreated())
							.andDo(print());
				
					
					
				}
	  
				
	@WithMockUser("spring")
	@Test
	public void listLehrerTest() throws Exception {
		List<Lehrer> listeLehrer = lehrerRepository.getAllLehrer();
		Mockito.when(lehrerServices.getListLehrer()).thenReturn(listeLehrer);
		//listeLehrer.stream().map(lehrer-> this.lehrerServicesImplementation.convertEntityToDto(lehrer)).collect(Collectors.toList());

		RequestBuilder requestBuilder =MockMvcRequestBuilders.get( "/personne/lehrer/listLehrer")
				.with(user("merkel@dre.org").password("merkel007").roles("LEITER"))
				.accept(MediaType.APPLICATION_JSON);
              ResultActions resultActions=   mockMvc.perform(requestBuilder)            		  
        		.andExpect(MockMvcResultMatchers.status().isOk() )
        		.andDo(print());
        		
	}
	@Test
	@WithMockUser("spring")
	
	public void updateLehrerTest() throws Exception {
		sprache.add(convert(spraches) );
		niveau.add(convert(niveaus));
      roles.add(convert(role));
	     /* lehrer =new Lehrer( "merkel@dre.org","Nzeunga", "Merkel",heute, "Kamerun","Ärtzin",  "Terminus", "+491521654",  "merkel@gmail.com",
	    		  "CNI_Merkel002222","merkel007", "Doktor", heute, "Landwirtin",true ,  sprache, niveau, 30, salaire,    roles);*/
		//Mockito.when(lehrerServices.saveLehrer(lehrer)).thenReturn(0);
      lehrerDTO= new LehrerDTO( "merkel@dre.org","Nzeunga", "Merkel",heute, "Kamerun","Ärtzin",  "Terminus", "+491521654",  "merkel@gmail.com", "CNI_Merkel002222",
				 "passwordMerkel", "Doktor", heute, "Landwirtin",true ,  timestamp  , sprache,  niveau, 24, salaire,   roles);
      lehrerDTO.setNationalite("Deutsch");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch( "/personne/lehrer/updateLehrer")
				.content(asJsonString(lehrerDTO))
			      .contentType(MediaType.APPLICATION_JSON_VALUE)
			      .accept(MediaType.APPLICATION_JSON_VALUE)							
			      .with(user("flessent@dre.org").password("merkel007").roles("LEITER"))
				.accept(MediaType.APPLICATION_JSON_VALUE);
		ResultActions resultActions= mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	@WithMockUser("spring")
	@AfterEach
	public  void  deleteLehrerTest() throws Exception {
		RequestBuilder requestBuilder=MockMvcRequestBuilders.delete("/personne/lehrer/deleteLehrer/merkel@dre.org")
				.accept(MediaType.APPLICATION_JSON);
		//RequestBuilder requestBuilder =MockMvcRequestBuilders.delete("/personne/lehrer/deleteLehrer/{username}", "merkel@dre.org");
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}

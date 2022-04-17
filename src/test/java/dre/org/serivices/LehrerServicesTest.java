package dre.org.serivices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.runner.RunWith;

import dre.org.dao.LehrerRepository;
import dre.org.services.LehrerServices;
import dre.org.services.NiveauServices;
import dre.org.services.RolesServices;
import dre.org.services.SpracheServices;
import dre.org.services.implementation.LehrerServicesImplementation;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ContextConfiguration
@ExtendWith(MockitoExtension.class)
public class LehrerServicesTest {
	
	private LehrerServices lehrerServices;



}

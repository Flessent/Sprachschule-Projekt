package dre.org;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@CrossOrigin(origins = "http://localhost:4200")
@Configuration
@RestController
@EnableWebMvc
@RequestMapping("/api/v1")
public class WebMVCConfig  implements WebMvcConfigurer {
	
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
	    }
	
	/*@Override
	public void addViewControllers(ViewControllerRegistry controllerRegistry) {
		
		
		controllerRegistry.addViewController("/").setViewName("/login");// des le demarrage on redirrige  vers login
		controllerRegistry.addViewController("/login").setViewName("/login");// de login vers login
		controllerRegistry.addViewController("/logout").setViewName("/login");//de logout vers login (en cas de dévonnexion)
			
	}*/
	
	
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(produces = "application/json")
	@RequestMapping("/greeting")
	public String greeting() {
		return "Hallo ! Bonjour";
	}
	
	
	

	}
	
	
	
	
	



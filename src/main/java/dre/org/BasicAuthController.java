package dre.org;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})

public class BasicAuthController implements WebMvcConfigurer {
	
	//@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	 @GetMapping(path = "/basicauth")
	    public AuthenticationBean basicauth() {
	        return new AuthenticationBean("You are authenticated");
	    }



}
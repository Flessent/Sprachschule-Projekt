package dre.org;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class DrE_SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
public String encoder(String passwd) { //encodage des mdp avec bcrypt
	BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
	return  bCryptPasswordEncoder.encode(passwd);
	
}
@Bean
public CorsFilter corsFilter() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
    corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
            "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
            "Access-Control-Request-Method", "Access-Control-Request-Headers"));
    corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
            "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
    return new CorsFilter(urlBasedCorsConfigurationSource);
}
	
     
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception{ 
		//auth.inMemoryAuthentication().withUser("admin").password(encoder("123")).roles("VERWALTER", "LEHRER");//identification en utilisant les users stockés en mémoire
		auth.jdbcAuthentication()
	     .dataSource(dataSource)
	    // .passwordEncoder(passwordEncoder())//encodage en BCRYPT du mdp.Très important
	      .usersByUsernameQuery("select username as principal ,password as credentials ,true from personne where username= ?")
	      .authoritiesByUsernameQuery("select pr.username as principal ,r.role as role  from  roles r inner join personne_roles pr on pr.code_role=r.code_role where username=?" )
	      .rolePrefix("ROLE_");//ceci veut dire d'ajouter ROLE_ au role stocké dans la BD.En effet, par defaut Spring l'ajoute au role
	
	
	}
	/*
	
	@Override
	@Autowired
	protected void configure (AuthenticationManagerBuilder auth)throws Exception{
		auth.inMemoryAuthentication().
		withUser("flessent@dre.org").password(encoder("merkel007")).roles("LEITER");
	}
	
	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
	    http.csrf().disable();
	    http.cors();
	    http.httpBasic();
	    http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/login", "/**").permitAll();
	    http.authorizeRequests().antMatchers(HttpMethod.GET, "/", "/**").permitAll()

	            .anyRequest()
	            .authenticated()
	            .and()
	            .httpBasic();
	    
	}


}

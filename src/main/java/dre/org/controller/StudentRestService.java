package dre.org.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dre.org.dto.StudentDTO;
import dre.org.entities.Niveau;
import dre.org.entities.Roles;
import dre.org.entities.Sprache;
import dre.org.entities.Student;
import dre.org.services.LehrerServices;
import dre.org.services.NiveauServices;
import dre.org.services.RolesServices;
import dre.org.services.SpracheServices;
import dre.org.services.StudentServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/student")
public class StudentRestService extends RuntimeException{
	@Autowired
	private StudentServices studentServices;
	
	
	
    @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@PostMapping(path = "/saveStudent")
	public @ResponseBody ResponseEntity<?> saveStudent(@RequestBody @Valid StudentDTO studentDTO) {
		System.out.println("Voici studentDTO"+studentDTO.getNumTelFinanceurCours());
		

	return new ResponseEntity<>(studentServices.saveStudent( this.studentServices.convertDtoToEntity(studentDTO)), HttpStatus.OK);
	}
	
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@GetMapping(path = "/listStudenten")
	public ResponseEntity<List <StudentDTO>> listStudenten(){
	
		
		List<StudentDTO> listeStudenten=studentServices.listStudenten().stream().map(studentDTO->this.studentServices. convertEntityToDto(studentDTO) ).collect(Collectors.toList());;
		System.out.println("Das ist die Liste von Studenten"+listeStudenten);
		listeStudenten.forEach(student -> {
			System.out.print("voic la liste"+student.getNom());
		});
		return new ResponseEntity<>(listeStudenten,HttpStatus.OK);
	}
	
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@PatchMapping(path = "/updateStudent")
	
	public ResponseEntity<?> updateStudent ( @RequestBody StudentDTO studentDTO){
		return new ResponseEntity<> (this.studentServices.updateStudent(this.studentServices.convertDtoToEntity(studentDTO)), HttpStatus.OK);
	}
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@DeleteMapping(path  = "/deleteStudent/{username}", produces = "text/plain")
	public ResponseEntity<?> deleteStudent(@PathVariable("username") String username){
		 this.studentServices.deletePersonne(username);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
}

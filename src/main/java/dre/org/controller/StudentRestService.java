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
	public SpracheServices spracheServices;
	@Autowired
	public NiveauServices niveauServices;
	private static final long serialVersionUID = 1L;

	@Autowired
    private  ModelMapper modelMapper;

@Autowired(required = false)
private StudentServices studentServices;

@Autowired
private RolesServices rolesServices;
@Autowired
private LehrerServices lehrerServices;

Converter<Sprache, UUID> uuidConverterSprache = new AbstractConverter<Sprache, UUID>() {
    protected UUID convert(Sprache source) {
        return UUID.fromString(source.getCodeSprache().toString());
    }
};
Converter<Niveau, UUID> uuidConverterNiveau = new AbstractConverter<Niveau, UUID>() {
    protected UUID convert(Niveau source) {
        return UUID.fromString(source.getCodeNiveau().toString());
    }
};

    private StudentDTO convertEntityToDto(Student student){
    	modelMapper.addConverter(uuidConverterSprache);
    	modelMapper.addConverter(uuidConverterNiveau);

    	modelMapper.getConfiguration()
    	.setMatchingStrategy(MatchingStrategies.LOOSE)
    	  .setFieldMatchingEnabled(true);

    	StudentDTO studentDTO=new StudentDTO();

    	studentDTO=modelMapper.map(student, StudentDTO.class);
    	System.out.print("isActivad dans studentDTO"+studentDTO.isAccountActivated());
    	return studentDTO;
    } 

   
   
    
    private Student convertDtoToEntity(StudentDTO studentDTO){
    	//modelMapper.addConverter(convert(spracheDTO.getCodeSprache())  );

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Student student = new Student();
        student = modelMapper.map(studentDTO, Student.class);
        Set<Niveau> listeNiveau=niveauServices.getAllNiveauByUUID(studentDTO.getNiveau());
        student.setNiveau(listeNiveau);    
        Set<Sprache> sprache = spracheServices.getAllSprachenByCodeSprachen(studentDTO.getSprache());
        student.setSprache(sprache);
        Set<Roles> roles=rolesServices.getAllRolesByUUID(studentDTO.getRoles());
        student.setRoles(roles);
        return student;
    }
	
	
	
	
    @Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@PostMapping(path = "/saveStudent")
	public @ResponseBody Object saveStudent(@RequestBody @Valid StudentDTO studentDTO) {
		System.out.println("Voici studentDTO"+studentDTO.getNumTelFinanceurCours());
		

	return lehrerServices.saveLehrer(convertDtoToEntity(studentDTO));
	}
	
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@GetMapping(path = "/listStudenten")
	public ResponseEntity<List <StudentDTO>> listStudenten(){
	
		
		List<StudentDTO> listeStudenten=lehrerServices.getListStudent().stream().map(this::convertEntityToDto).collect(Collectors.toList());;
		System.out.println("Das ist die Liste von Studenten"+listeStudenten);
		listeStudenten.forEach(student -> {
			System.out.print("voic la liste"+student.getNom());
		});
		return new ResponseEntity<>(listeStudenten,HttpStatus.OK);
	}
	
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@PatchMapping(path = "/updateStudent")
	
	public ResponseEntity<?> updateStudent ( @RequestBody StudentDTO studentDTO){
		return new ResponseEntity<> (lehrerServices.updateLehrer(convertDtoToEntity(studentDTO)), HttpStatus.OK);
	}
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@DeleteMapping(path  = "/deleteStudent/{username}", produces = "text/plain")
	public ResponseEntity<?> deleteStudent(@PathVariable("username") String username){
		lehrerServices.deleteLehrer(username);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
}

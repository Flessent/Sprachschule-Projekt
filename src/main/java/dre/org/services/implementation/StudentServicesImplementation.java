package dre.org.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import dre.org.dao.StudentRepository;
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
@Service
public class StudentServicesImplementation implements StudentServices {
	
	
	
@Autowired
private StudentRepository studentRepository;
@Autowired
public SpracheServices spracheServices;
@Autowired
public NiveauServices niveauServices;
private static final long serialVersionUID = 1L;

@Autowired
private  ModelMapper modelMapper;


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
@Override

public StudentDTO convertEntityToDto(Student student){
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



@Override
public Student convertDtoToEntity(StudentDTO studentDTO){
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

	@Override
	public int saveStudent(Student student) {
		return this.lehrerServices.saveLehrer(student);
			}
	@Override
	public List<Student> listStudenten() {
		
		return studentRepository.getAllStudenten();
	}
	@Override
	public Student updateStudent(Student student) {
		Student updatedStudent =studentRepository.save(student);

	    return updatedStudent;
	}
	@Override
	public void deletePersonne(String useranme) {
		studentRepository.deletePersonne(useranme);;
		
	}

}

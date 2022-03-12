package dre.org.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import dre.org.dao.StudentRepository;
import dre.org.entities.Student;
import dre.org.services.StudentServices;
@Service
public class StudentServicesImplementation implements StudentServices {
@Autowired
private StudentRepository studentRepository;
	@Override
	public Object saveStudent(Student student, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, Object> errors=new HashMap<>();
			errors.put("errors", true);
			for(FieldError fieldError:bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
				System.out.println("Voici les erreurs"+errors);
			}

		return errors;
		}
		System.out.println("Informations bien enregistrées"+student.getActiviteEnParalelle());
		 		
		 return studentRepository.save(student);
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
	public void deleteStudent(String useranme) {
		studentRepository.deletePersonne(useranme);;
		
	}

}

package dre.org.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import dre.org.dto.StudentDTO;
import dre.org.entities.Student;

public interface StudentServices {
	
	public int saveStudent(Student student);
public List<Student> listStudenten();
public Student updateStudent(Student student);
public void deletePersonne(String useranme);
Student convertDtoToEntity(StudentDTO studentDTO);
StudentDTO convertEntityToDto(Student student);
}

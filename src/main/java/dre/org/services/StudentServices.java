package dre.org.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import dre.org.entities.Student;

public interface StudentServices {
	
	public Object saveStudent(Student student, BindingResult bindingResult);
public List<Student> listStudenten();
public Student updateStudent(Student student);
public void deleteStudent(String useranme);
}

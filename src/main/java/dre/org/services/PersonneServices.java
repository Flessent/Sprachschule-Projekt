package dre.org.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import dre.org.dto.PersonneDTO;
import dre.org.entities.Personne;
public interface PersonneServices {
public Object savePersonne(Personne personne, BindingResult bindingResult);
public List<PersonneDTO> listeBestimmtePersone(String type);
}

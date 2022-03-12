package dre.org.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import dre.org.dao.RolesRepository;
import dre.org.entities.Raum;
import dre.org.entities.Roles;
import dre.org.services.RolesServices;

@Service
public class RolesServicesImplementation implements RolesServices{
@Autowired
private RolesRepository rolesRepository;
	@Override
	public Object saveRoles(Roles roles, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, Object> errors=new HashMap<>();
			errors.put("errors", true);
			for(FieldError fieldError:bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
				System.out.println("Voici les erreurs"+roles);
			}

		return errors;
		}
		System.out.println("Informations bien enregistrées"+roles);
		return rolesRepository.save(roles);
	}

	@Override
	public Roles updateRoles(Roles roles) {
		Roles updatedRoles =rolesRepository.save(roles);
		return updatedRoles;
	}

	@Override
	public void deleteRoles(UUID codeRoles) {
		System.out.print("voici le codeRaum struit"+ codeRoles);
		rolesRepository.deleteRoles(codeRoles);
		
	}

	@Override
	public List<Roles> listRoles() {
		List<Roles> listRoles= rolesRepository.findAll();
		return listRoles;
	}

	@Override
	public Roles getOneRolesByCodeRoles(UUID codeRoles) {
		Roles roles= rolesRepository.getRolesByCodeRole(codeRoles);
        System.out.print("voici le roles"+roles);
        return roles; 
	}

	@Override
	public Set<Roles> getAllRolesByUUID(Set<UUID> listeUUIDRoles) {
		return rolesRepository.findByCodeRoleIn(listeUUIDRoles);
	}

	
}

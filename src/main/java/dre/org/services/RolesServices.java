package dre.org.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.validation.BindingResult;

import dre.org.entities.Roles;


public interface RolesServices {
	public Object saveRoles(Roles roles,BindingResult result);
	public Roles updateRoles(Roles roles);
	public void deleteRoles(UUID codeRoles);
	//public Set <Niveau >listNiveauByLibelleSprache (String libelleSprache);
	public   List<Roles> listRoles();
	public Roles getOneRolesByCodeRoles(UUID codeRoles);
	public Set<Roles> getAllRolesByUUID(Set<UUID> listeUUIDRoles);
	
}

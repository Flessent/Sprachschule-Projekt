package dre.org.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import dre.org.dto.RolesDTO;
import dre.org.entities.Roles;
import dre.org.services.RolesServices;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/roles")
public class RolesRestService extends RuntimeException {
	@Autowired
	public RolesServices rolesServices;
	@Autowired
	private ModelMapper modelMapper;
	//modelMapper.getConfiguration().setAmbiguityIgnored(true);
	private RolesDTO convertEntityToDto(Roles roles){
	    modelMapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.LOOSE);
	    RolesDTO rolesDTO = new RolesDTO();
	    rolesDTO = modelMapper.map(roles, RolesDTO.class);
	    return rolesDTO;
	}

	private Roles convertDtoToEntity(RolesDTO rolesDTO){
	    modelMapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.LOOSE);
	    Roles roles = new Roles();
	    roles = modelMapper.map(rolesDTO, Roles.class);
	    return roles;
	}
	
	
	
		@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@PostMapping(path = "/saveRoles")
		public @ResponseBody Object saveRoles(@RequestBody @Valid RolesDTO roles,BindingResult bindingResult) {
			return rolesServices.saveRoles(convertDtoToEntity(roles), bindingResult);
		}

		
		@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@PatchMapping(path = "/updateRoles")
		public ResponseEntity<?> updateRoles(@RequestBody RolesDTO roles) {

		    return new  ResponseEntity<>(rolesServices.updateRoles(convertDtoToEntity(roles)),HttpStatus.OK);
		}
		@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@DeleteMapping(path  = "/deleteRoles/{codeRoles}",produces = "application/json")
		public ResponseEntity<?> deleteRoles(@PathVariable("codeRoles") UUID codeRoles){
			rolesServices.deleteRoles(codeRoles);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		

		
		@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@GetMapping(path = "/listRoles")
		public ResponseEntity<List <RolesDTO>> listRaume(){
			List<RolesDTO> listeRoles=rolesServices.listRoles().stream().map(this::convertEntityToDto).collect(Collectors.toList());;
			return new ResponseEntity<>(listeRoles,HttpStatus.OK);
		}
		
		
		
		@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
		@GetMapping(path = "/getRolesByCodeRole/{codeRole}")
		public ResponseEntity<RolesDTO> getOneRoles(@PathVariable("codeRole")  UUID codeRole){
		
			return new ResponseEntity<>(convertEntityToDto(rolesServices.getOneRolesByCodeRoles(codeRole)),HttpStatus.OK);
		}
		

}

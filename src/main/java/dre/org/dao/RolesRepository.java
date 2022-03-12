package dre.org.dao;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dre.org.entities.Roles;
@Repository
public interface RolesRepository extends JpaRepository<Roles, UUID>{
	void	deleteRolesByCodeRole(UUID codeRoles);
	Roles getRolesByCodeRole(UUID codeRoles);
	@Transactional
	@Modifying
	@Query("delete from Roles r where r.codeRole=:codeRole")
	void deleteRoles(@Param("codeRole") UUID codeRole);
    Set<Roles> findByCodeRoleIn(Set <UUID> listeUUIDRoles);
    @Modifying
    @Transactional
    	@Query(value="select  n.libelle from Niveau n inner join kursniveau kn on kn.codeNiveau=n.codeNiveau", nativeQuery = true )
    	List<String> getNiveauInfo();
    List<Roles> findByRoleIn(List<String> listeNomRoles);
    
    public Set<Roles> findByCodeRoleIn(List<UUID> listCodeRoles);
    @Transactional
    @Query("select codeRole  from Roles r where r.role=:role ")
    public UUID getCodeRoleByRole(String role);
}

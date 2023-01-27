package ecom.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ecom.sid.entities.Role;
import ecom.sid.entities.User;
import ecom.sid.entities.cartItem;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	  @Query("SELECT r FROM Role r WHERE r.Name = ?1")
	  public Role findByName(String name);
	  
}

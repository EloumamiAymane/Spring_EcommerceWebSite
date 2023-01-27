package ecom.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ecom.sid.entities.User;
import ecom.sid.entities.cartItem;

@Repository
public interface cartItemRepository  extends JpaRepository<cartItem, Long>{

	List<cartItem>findByUser(User user);
	@Transactional
	@Modifying
	@Query("UPDATE cartItem c SET c.quantity = :quantity, c.total= :quantity * :prix WHERE c.id = :id")
	int updateQuantityById(@Param("quantity")int quantity,@Param("id") Long id,@Param("prix")double prix);
	
	@Transactional
	@Modifying
	@Query("UPDATE cartItem c SET c.total= :quantity * :prix WHERE c.id = :id")
	int updateTotalById(@Param("quantity")int quantity,@Param("id") Long id,@Param("prix")double prix);
	
	int countByUser(User user);
}

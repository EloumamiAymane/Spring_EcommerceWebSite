package ecom.sid.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ecom.sid.entities.Produit;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	public Page<Produit>findByDesignationContains(String motCle,Pageable pageable);
	
	@Query(value="SELECT * FROM Produit ORDER BY RAND() LIMIT 8", nativeQuery = true) 
	
	public List<Produit>findProduit();
	@Query(value="SELECT * FROM Produit ORDER BY(id)desc LIMIT 8", nativeQuery = true) 
	public List<Produit>findJustArrivedProduit();
	@Query(value="SELECT p FROM Produit p WHERE p.prix between :prix0 AND :prix1") 
	public Page<Produit>findByPrix(@Param("prix0") double prix0,@Param("prix1") double prix1,Pageable pageable);
}

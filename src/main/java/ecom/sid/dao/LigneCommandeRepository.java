package ecom.sid.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ecom.sid.entities.LigneCommande;
import ecom.sid.entities.Produit;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long>{
	public Page<LigneCommande>findAll(Pageable pageable);
}

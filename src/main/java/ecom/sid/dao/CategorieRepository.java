package ecom.sid.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecom.sid.entities.Categorie;
@Repository
public interface CategorieRepository  extends JpaRepository<Categorie, Long> {
public Page<Categorie>findByNomCategorieContains(String motCle,Pageable pageable);
}

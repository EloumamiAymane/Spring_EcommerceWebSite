package ecom.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.sid.entities.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

}

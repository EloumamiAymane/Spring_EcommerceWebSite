package ecom.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.sid.entities.Payement;

public interface PayementRepository extends JpaRepository<Payement,Long> {

}

package ecom.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.sid.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

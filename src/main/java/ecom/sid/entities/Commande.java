
package ecom.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity


public class Commande implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCommande;
	@Temporal(TemporalType.DATE)
	private Date dateCommande;
	

	@ManyToOne

	@JoinColumn(name = "idClient")
	private Client client;

	@OneToMany(mappedBy = "commande", fetch = FetchType.LAZY)
	private Collection<LigneCommande> ligneCommades;

	public Long getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<LigneCommande> getLigneCommades() {
		return ligneCommades;
	}

	public void setLigneCommades(Collection<LigneCommande> ligneCommades) {
		this.ligneCommades = ligneCommades;
	}

	public Commande(Long idCommande, Date dateCommande, Client client, Collection<LigneCommande> ligneCommades) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.client = client;
		this.ligneCommades = ligneCommades;
	}
	public Commande(Long idCommande, Date dateCommande, Client client) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.client = client;
		
	}
	public Commande() {
		super();
	}
	
}

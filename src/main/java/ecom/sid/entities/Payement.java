package ecom.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Payement {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String NameCard;
	private int NumeroCard;
	private int cvv;
	private String Exp;
	@OneToOne
	private Client client;
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameCard() {
		return NameCard;
	}
	public void setNameCard(String nameCard) {
		NameCard = nameCard;
	}
	public int getNumeroCard() {
		return NumeroCard;
	}
	public void setNumeroCard(int numeroCard) {
		NumeroCard = numeroCard;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getExp() {
		return Exp;
	}
	public void setExp(String exp) {
		Exp = exp;
	}

	public Payement(Long id, String nameCard, int numeroCard, int cvv, String exp, Client client) {
		super();
		this.id = id;
		NameCard = nameCard;
		NumeroCard = numeroCard;
		this.cvv = cvv;
		Exp = exp;
		this.client = client;
	}
	public Payement() {
		super();
	}
	
}

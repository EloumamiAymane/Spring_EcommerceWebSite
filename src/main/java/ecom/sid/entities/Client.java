
  package ecom.sid.entities;
  
  import java.io.Serializable; import java.util.Collection;
  
  import javax.persistence.Entity; import javax.persistence.FetchType; import
  javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
  import javax.persistence.Id; import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo; import
  com.fasterxml.jackson.annotation.ObjectIdGenerators;
 
  
	@Entity


	public class Client implements Serializable {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long idclient;
		private String Name;
		private String Username;
		private String Adresse;
		private String Email;
		private String City;
		private String Country;
		private int Zip;
		private String Tel;

		@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
		private Collection<Commande> commandes;
        @OneToOne(mappedBy = "client")
		private Payement payement;
        
		public Payement getPayement() {
			return payement;
		}

		public void setPayement(Payement payement) {
			this.payement = payement;
		}

		public Long getIdclient() {
			return idclient;
		}

		public void setIdclient(Long idclient) {
			this.idclient = idclient;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getUsername() {
			return Username;
		}

		public void setUsername(String username) {
			Username = username;
		}

		public String getAdresse() {
			return Adresse;
		}

		public void setAdresse(String adresse) {
			Adresse = adresse;
		}

		public String getEmail() {
			return Email;
		}

		public void setEmail(String email) {
			Email = email;
		}

		public String getCity() {
			return City;
		}

		public void setCity(String city) {
			City = city;
		}

		public String getCountry() {
			return Country;
		}

		public void setCountry(String country) {
			Country = country;
		}

		public int getZip() {
			return Zip;
		}

		public void setZip(int zip) {
			Zip = zip;
		}

		public String getTel() {
			return Tel;
		}

		public void setTel(String tel) {
			Tel = tel;
		}

		public Collection<Commande> getCommandes() {
			return commandes;
		}

		public void setCommandes(Collection<Commande> commandes) {
			this.commandes = commandes;
		}

		public Client(Long idclient, String name, String username, String adresse, String email, String city,
				String country, int zip, String tel, Collection<Commande> commandes) {
			super();
			this.idclient = idclient;
			Name = name;
			Username = username;
			Adresse = adresse;
			Email = email;
			City = city;
			Country = country;
			Zip = zip;
			Tel = tel;
			this.commandes = commandes;
		}
		public Client() {
			
		}
  
  
  }
 
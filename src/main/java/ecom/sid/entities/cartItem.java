package ecom.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

public class cartItem {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
private double total;
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
@ManyToOne

@JoinColumn(name="product_id")

	private Produit product;

@ManyToOne

@JoinColumn(name="user_id")

	private User user;
private int quantity;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Produit getProduct() {
	return product;
}
public void setProduct(Produit product) {
	this.product = product;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public cartItem() {
	super();
}


}


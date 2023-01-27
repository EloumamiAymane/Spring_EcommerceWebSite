
  package ecom.sid.entities;
  
  import java.io.Serializable; import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity; import javax.persistence.FetchType; import
  javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
  import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo; import
  com.fasterxml.jackson.annotation.ObjectIdGenerators;
  
 
  

  
 //@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
 // property="id")
  

 
 @Entity
 //@Table(name="User",uniqueConstraints=@UniqueConstraint(columnNames = "email"))
  public class User implements Serializable{
  
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false,length = 35)
  private String FirstName; 
  @Column(nullable = false,length = 35)
  private String LastName; 
  @Column(nullable = false,length = 35,unique = true)
  private String email;
  @Column(nullable = false,length = 65)
  private String Password;
  
  @OneToMany(mappedBy ="user")
  
	private Collection<cartItem> carts;
	
	  @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	
	  @JoinTable( name="User_role", joinColumns=@JoinColumn(
	  
	  name="user_id",referencedColumnName = "id" ), inverseJoinColumns
	  = @JoinColumn( name="role_id",referencedColumnName = "id" )
	  
	  )
	  
	  private Set<Role>roles =new HashSet<>();
	  
 
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFirstName() {
	return FirstName;
}
public void setFirstName(String firstName) {
	FirstName = firstName;
}
public String getLastName() {
	return LastName;
}
public void setLastName(String lastName) {
	LastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public Collection<cartItem> getCarts() {
	return carts;
}
public void setCarts(Collection<cartItem> carts) {
	this.carts = carts;
}
public User() {
	super();
}
public Collection<Role> getRoles() {
	return roles;
}

public void setRoles(Set<Role> roles) {
	this.roles = roles;
}
public void addRole(Role role) {
this.roles.add(role);
}
@Override
public String toString() {
	return "User [id=" + id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", email=" + email + ", Password="
			+ Password + ", carts=" + carts + ", roles=" + roles + "]";
}
	
 }
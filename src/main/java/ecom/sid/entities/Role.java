
  package ecom.sid.entities;
  
  import java.io.Serializable;
  
  import javax.persistence.Entity; import javax.persistence.GeneratedValue;
  import javax.persistence.GenerationType; import javax.persistence.Id;
  
  
  
  
  
  
  
  
  // @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
  
  @Entity public class Role implements Serializable {
  
  public Role() { super(); }
  
  public Long getId() { return id; }
  
  public void setId(Long id) { this.id = id; }
  
  public String getRoleName() { return Name; }
  
  public void setRoleName(String Name) { this.Name = Name; }
  
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  
  private String Name;

@Override
public String toString() {
	return "  " + Name + "";
}
  
  }
 
 
 
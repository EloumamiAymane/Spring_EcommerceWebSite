package ecom.sid;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import ecom.sid.dao.CategorieRepository;
import ecom.sid.entities.Categorie;
import ecom.sid.services.IboutiqueMetier;

@SpringBootApplication
public class EcomWebSiteApplication  {
@Autowired
	private CategorieRepository categorieRepository;
@Autowired
	private IboutiqueMetier  iboutiqueMetier;
	public static void main(String[] args) {
		SpringApplication.run(EcomWebSiteApplication.class, args);
	}
}

/*
 * @Override public void run(String... args) throws Exception {
 * 
 * List<Categorie> categorie=categorieRepository.findAll();
 * 
 * categorie.forEach(c-> { System.out.println(c.toString()); });
 * 
 * 
 * 
 * }
 * 
 * }
 */

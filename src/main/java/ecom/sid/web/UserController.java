package ecom.sid.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecom.sid.dao.UserRepository;
import ecom.sid.entities.Categorie;
import ecom.sid.entities.Produit;
import ecom.sid.entities.Role;
import ecom.sid.entities.User;
import ecom.sid.services.IboutiqueMetier;
import ecom.sid.services.IboutiqueMetierImpl;

@Controller
public class UserController {
@Autowired
private UserRepository userRepo;
@Autowired
private IboutiqueMetier iboutiqueMetier;
	@GetMapping("/user")
	  public String ListUser(Model model,
			  
			  @RequestParam(name="motCle",defaultValue = "") String motCle,
			  
			  @RequestParam(name="page",defaultValue = "0") int page,
			  
			  @RequestParam(name="size",defaultValue = "5") int size) {
					
				
					
				  Page <User>
			  PageUser=userRepo.findByEmailContains(motCle, PageRequest.of(page, size));
			  
			  model.addAttribute("listUser",PageUser);
			  model.addAttribute("pages", new int[PageUser.getTotalPages()]);
			  model.addAttribute("currentPage", page); model.addAttribute("motCle",
			  motCle); model.addAttribute("size", size); User user=new User();
			  model.addAttribute("user", user);
			return "user";
}
	
	
	@GetMapping("/DetailUser/{id}")
	public String DetailUser(Model model,@PathVariable("id")Long id) {
		User user=userRepo.findById(id).get();
		model.addAttribute("user", user);
		List<Role>roles=iboutiqueMetier.getAll();	
		model.addAttribute("listRoles", roles);
		return "DetailUser";
	}
	
	@PostMapping(path="/saved/{id}")
	public String Register(Model model,@ModelAttribute(name="user") User user ,@PathVariable("id")Long id){
		
		
		
		/*
		 * BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(); String
		 * EncoderPass=encoder.encode(user.getPassword());
		 * user.setPassword(EncoderPass);
		 */
		 
		 
		 
		 //System.out.println(id);
	
		 
		 
		iboutiqueMetier.save(user);
		return "redirect:/DetailUser/"+id;
	}
	
	/*
	 * @GetMapping(path="/deleteUser/{id}") public String delete(@PathVariable Long
	 * id) { iboutiqueMetier.SupprrimerUser(id); return "redirect:/user";
	 * 
	 * }
	 */
	
}

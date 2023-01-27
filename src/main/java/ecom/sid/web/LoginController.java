package ecom.sid.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ecom.sid.dao.RoleRepository;
import ecom.sid.entities.Categorie;
import ecom.sid.entities.Role;
import ecom.sid.entities.User;
import ecom.sid.services.IboutiqueMetier;

@Controller
public class LoginController {
	@Autowired
	private IboutiqueMetier iboutiqueMetier;
	@Autowired
	private RoleRepository roleRep;
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping(path = "/connexion")
	public String index(Model model) {
		model.addAttribute("user", new User());

		return "Register";
	}

	@GetMapping(path = "/login")
	public String indexx(Model model) {

		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping(path = "/Register")
	public String Register(Model model, @ModelAttribute(name = "user") User user) {

		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String EncoderPass = encoder.encode(user.getPassword());
		user.setPassword(EncoderPass);
		Role userRole = roleRep.findByName("user");

		// System.out.println(userRole.toString());
		user.addRole(userRole);

		iboutiqueMetier.save(user);
		return "redirect:/login?success";
	}

}
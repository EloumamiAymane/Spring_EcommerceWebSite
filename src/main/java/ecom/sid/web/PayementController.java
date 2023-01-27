package ecom.sid.web;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ecom.sid.dao.ClientRepository;
import ecom.sid.dao.CommandeRepository;
import ecom.sid.dao.LigneCommandeRepository;
import ecom.sid.dao.PayementRepository;
import ecom.sid.dao.UserRepository;
import ecom.sid.dao.cartItemRepository;
import ecom.sid.entities.Client;
import ecom.sid.entities.Commande;
import ecom.sid.entities.LigneCommande;
import ecom.sid.entities.Payement;
import ecom.sid.entities.Produit;
import ecom.sid.entities.User;
import ecom.sid.entities.cartItem;
import ecom.sid.services.IboutiqueMetier;

@Controller
public class PayementController {

	@Autowired
	private cartItemRepository cartRepo;
	@Autowired
	private IboutiqueMetier iboutiqueMetier;
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    private PayementRepository payementRepos;
    @Autowired
	private UserRepository userRepo;
	
	@PostMapping("/saveC")
	public String savePayement( Payement payement,Client client, Principal principal,String Payement) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userRepo.findByEmail(currentPrincipalName);
		List<cartItem> carts = iboutiqueMetier.listCartItem(user);
		
		client.setName(user.getFirstName());
		client.setUsername(user.getLastName());
		clientRepository.save(client);
		
		payement.setClient(client);
		
		payementRepos.save(payement);
		Commande c=commandeRepository.save(new Commande(null,new Date(),client));
		for(cartItem cart:carts) {
			Produit p=cart.getProduct();
		ligneCommandeRepository.save(new LigneCommande(null,cart.getQuantity(),p.getPrix(),c,p,"en cours"));
		}
		
		  for(cartItem cart:carts) { cartRepo.delete(cart); }
		 
		return "redirect:/checkout";
	}
	
	@GetMapping(path = "/checkout")
	public String checkout(Model model, Principal principal) {
		
		if (principal == null)
			return "redirect:/login";
		double Totale=0;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userRepo.findByEmail(currentPrincipalName);
		List<cartItem> cart = iboutiqueMetier.listCartItem(user);
		Client client=new Client();
		model.addAttribute("client", client);
		for (cartItem cartItem : cart) {
			Totale+=cartItem.getTotal();
		}
		LigneCommande commande=new LigneCommande();
		model.addAttribute("commande", commande);
		double tot=Totale+10;
		model.addAttribute("tot", tot);
		model.addAttribute("total", Totale);
		model.addAttribute("carts", cart);
		model.addAttribute("payement", new Payement());
		return "checkout";
	}
}

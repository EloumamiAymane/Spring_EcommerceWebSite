package ecom.sid.web;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ecom.sid.dao.ClientRepository;
import ecom.sid.dao.CommandeRepository;
import ecom.sid.dao.LigneCommandeRepository;
import ecom.sid.dao.PayementRepository;
import ecom.sid.dao.ProduitRepository;
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
public class CartController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProduitRepository prodRepo;
	@Autowired
	private cartItemRepository cartRepo;
	@Autowired
	private IboutiqueMetier iboutiqueMetier;
	
	
	@GetMapping("/cart")

	public String showCart(Model model, Principal principal, HttpSession session) {
		if (principal == null)
			return "redirect:/login";
		double Totale=0;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userRepo.findByEmail(currentPrincipalName);

		List<cartItem> cart = iboutiqueMetier.listCartItem(user);
for (cartItem cartItem : cart) {
	Totale+=cartItem.getTotal();
}
double tot=Totale+10;
model.addAttribute("tot", tot);
model.addAttribute("total", Totale);
		int TotalCartItems = cartRepo.countByUser(user);
		session.setAttribute("TotalCartItems", TotalCartItems);
		model.addAttribute("carts", cart);

		return "cart";

	}

	@PostMapping("/addToCart")

	public String showCat(Model model, Principal principal, @RequestParam("id") Long ProductId,
			@RequestParam("quantity") int quantity, double total) {
		

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userRepo.findByEmail(currentPrincipalName);

		cartItem cart = iboutiqueMetier.AddProductToCart(ProductId, user, quantity, total);
		Long id1 = cart.getId();
		int x = iboutiqueMetier.updateTotal(quantity, id1, total);

		return "redirect:/shop";

	}

	@GetMapping("/deleteCart")

	public String DeleteCart(Model model, Long id) {
		iboutiqueMetier.deleteCardItem(id);
		return "redirect:/cart";

	}

	@PostMapping("/update")
	public String UpdateCart(Model model, @RequestParam("id") Long id, @RequestParam("quantity") int quantity,
			@RequestParam("prix") double prix) {

		int c = iboutiqueMetier.updateCart(quantity, id, prix);
		return "redirect:/cart";

	}


}
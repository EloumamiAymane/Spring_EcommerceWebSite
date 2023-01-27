package ecom.sid.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecom.sid.dao.LigneCommandeRepository;
import ecom.sid.dao.ProduitRepository;
import ecom.sid.dao.UserRepository;
import ecom.sid.entities.Categorie;
import ecom.sid.entities.Client;
import ecom.sid.entities.LigneCommande;
import ecom.sid.entities.Payement;
import ecom.sid.entities.Produit;
import ecom.sid.entities.User;
import ecom.sid.entities.cartItem;
import ecom.sid.services.IboutiqueMetier;
@Controller
public class HomeController {
	@Autowired
private IboutiqueMetier iboutiqueMetier;
	
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private LigneCommandeRepository LC;
	@GetMapping(path="/index")
	public String index(Model model,Principal principal) {
		List<Produit> produits=produitRepository.findProduit();
		model.addAttribute("listProduit", produits);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userRepo.findByEmail(currentPrincipalName);
model.addAttribute("user", user);
		List<Produit>prods=produitRepository.findJustArrivedProduit();
		model.addAttribute("listProds", prods);
		return "index";
	}
	
	  @GetMapping(path="/search") public String search(Model model,
			  
			  @RequestParam(name="motCle",defaultValue = "") String motCle,
			  
			  @RequestParam(name="page",defaultValue = "0") int page,
			  
			  @RequestParam(name="size",defaultValue = "8") int size) {
				
				  Page <Produit>
			  PageProduit=produitRepository.findByDesignationContains(motCle,
			  PageRequest.of(page, size)); model.addAttribute("listProduit",PageProduit);
			  model.addAttribute("pages", new int[PageProduit.getTotalPages()]);
			  model.addAttribute("currentPage", page); model.addAttribute("motCle",
			  motCle); model.addAttribute("size", size); Produit produit =new Produit();
			  model.addAttribute("produit", produit);
			 // System.out.println();
			  if(PageProduit.getNumberOfElements()==0) return "404";
			   return "catalogue"; }
	
	
	@GetMapping(path="/contact")
	public String contact() {
		return "contact";
	}



	@GetMapping(path = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		Produit produit = produitRepository.findById(id).get();
		cartItem cart = new cartItem();
		model.addAttribute("cart", cart);
		model.addAttribute("produit", produit);
		return "detail";
	}

	@GetMapping(path = "/detailProduct")
	public String detailProd(Model model, Long id) {
		Produit prod = produitRepository.findById(id).get();
		model.addAttribute("produit", prod);

		return "redirect:/detail";
	}

	@GetMapping(path = "/shop")
	public String AddCategorie(Model model,

			@RequestParam(name = "motCle", defaultValue = "") String motCle,

			@RequestParam(name = "page", defaultValue = "0") int page,

			@RequestParam(name = "size", defaultValue = "5") int size) {

		model.addAttribute("produit", new Produit());
		Page<Produit> PageProduit = produitRepository.findByDesignationContains(motCle, PageRequest.of(page, size));
		model.addAttribute("listProduit", PageProduit);
		model.addAttribute("pages", new int[PageProduit.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("motCle", motCle);
		model.addAttribute("size", size);
		Produit produit = new Produit();
		model.addAttribute("produit", produit);

		return "shop";
	}
	
	@GetMapping("/CommandeForm")
	public String CommandeForm(Model model,

			

			@RequestParam(name = "page", defaultValue = "0") int page,

			@RequestParam(name = "size", defaultValue = "5") int size) {

	
		Page<LigneCommande> commande =LC.findAll(PageRequest.of(page, size));
		model.addAttribute("commande", commande);
		model.addAttribute("pages", new int[commande.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		
		return "CommandeForm";
	}
	
	@GetMapping("/updateCmd/{id}")
	public String update(@PathVariable Long id) {
		LigneCommande ligneCmd=LC.getById(id);
		ligneCmd.setStatut("validé");
		LC.save(ligneCmd);
		return"redirect:/CommandeForm";
	}
	@GetMapping("/updateCmd1/{id}")
	public String update2(@PathVariable Long id) {
		LigneCommande ligneCmd=LC.getById(id);
		ligneCmd.setStatut("rejeté");
		LC.save(ligneCmd);
		
		
		return"redirect:/CommandeForm";
	}
	
	
	
	/*
	 * @GetMapping("/template") public String tmp(Model model) { Produit produit =
	 * new Produit(); model.addAttribute("produit", produit); return "template"; }
	 */

	@GetMapping("/404")
	public String error() {
		return "404";
	}
	


}

package ecom.sid.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecom.sid.dao.CategorieRepository;
import ecom.sid.dao.ProduitRepository;
import ecom.sid.dao.RoleRepository;
import ecom.sid.dao.UserRepository;
import ecom.sid.dao.cartItemRepository;
import ecom.sid.entities.Categorie;
import ecom.sid.entities.Produit;
import ecom.sid.entities.Role;
import ecom.sid.entities.User;
import ecom.sid.entities.cartItem;

@Service
@Transactional
public class IboutiqueMetierImpl implements IboutiqueMetier {
	@Autowired
private CategorieRepository  categorieRepository ;
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private UserRepository  userRepository;
	@Autowired
	private cartItemRepository cartRepo;
	@Autowired
	private RoleRepository roleRep;
	@Override
	public Categorie ajouterCategorie(Categorie c) {
		
		return categorieRepository.save(c);
	}

	@Override
	public List<Categorie> ListeCategorie() {
		
		return categorieRepository.findAll();
	}

	@Override
	public Categorie getCategorie(Long idc) {
		
		return  categorieRepository.findById(idc).get();
	}

	

	@Override
	public void SupprrimerCategorie(Long idc) {
		categorieRepository.deleteById(idc);
		
	}

	@Override
	public Produit ajouterProduit(Produit p) {
		
		return produitRepository.save(p);
	}

	@Override
	public List<Produit> ListeProduit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit getProduit(Long idp) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void SupprrimerProduit(Long idp) {
		produitRepository.deleteById(idp);
		
	}

	@Override
	public User save(User user) {
		userRepository.save(user);
		return null;
	}
	
	public cartItem AddProductToCart(Long productId,User user,int Qty,double total) {
		Produit produit=produitRepository.findById(productId).get();
		cartItem cart=new cartItem();
		cart.setProduct(produit);
		cart.setQuantity(Qty);
		cart.setUser(user);
		cart.setTotal(total);
		cartItem cart1=cartRepo.save(cart);
		
		return cart1;
		
	}
	public List<cartItem> listCartItem(User user){ return
			  cartRepo.findByUser(user); }

	@Override
	public void deleteCardItem(Long id) {
		cartRepo.deleteById(id);
		
	}

	@Override
	public int updateCart(int quantity, Long id,double prix) {
		return cartRepo.updateQuantityById(quantity, id,prix);
		
	}

	@Override
	public int updateTotal(int quantity, Long id, double prix) {
		
		return cartRepo.updateTotalById(quantity, id, prix);
	}

	@Override
	public List<Role> getAll() {
		
		return roleRep.findAll();
	}

	@Override
	public void SupprrimerUser(Long id) {
	userRepository.deleteById(id);
		
	}

}

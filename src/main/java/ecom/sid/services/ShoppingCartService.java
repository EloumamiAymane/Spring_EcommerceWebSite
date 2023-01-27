/*
 * package ecom.sid.services;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import ecom.sid.dao.ProduitRepository; import
 * ecom.sid.dao.cartItemRepository; import ecom.sid.entities.Produit; import
 * ecom.sid.entities.User; import ecom.sid.entities.cartItem;
 * 
 * public class ShoppingCartService {
 * 
 * @Autowired private cartItemRepository cartRepo;
 * 
 * @Autowired ProduitRepository prodRepo;
 * 
 * 
 * public List<cartItem> listCartItem(User user){ return
 * cartRepo.findByUser(user); }
 * 
 * public cartItem AddProductToCart(Long productId,User user,int Qty) { Produit
 * produit=prodRepo.findById(productId).get(); cartItem cart=new cartItem();
 * cart.setProduct(produit); cart.setQuantity(Qty); cart.setUser(user); cartItem
 * cart1=cartRepo.save(cart);
 * 
 * return cart1;
 * 
 * }
 * 
 * 
 * 
 * }
 */
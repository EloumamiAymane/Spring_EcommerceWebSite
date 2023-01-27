package ecom.sid.services;


import java.util.List;

import org.springframework.stereotype.Service;

import ecom.sid.entities.Categorie;
import ecom.sid.entities.Produit;
import ecom.sid.entities.Role;
import ecom.sid.entities.User;
import ecom.sid.entities.cartItem;
@Service
public interface IboutiqueMetier {
//crud Categorie
public Categorie ajouterCategorie(Categorie c);
public List<Categorie>ListeCategorie();
public Categorie getCategorie(Long idc);

public void SupprrimerCategorie(Long idp);

//crud produit
public Produit ajouterProduit(Produit p);
public List<Produit>ListeProduit();
public Produit getProduit(Long idp);

public void SupprrimerProduit(Long idp);
//register form
public User save(User user);
public void SupprrimerUser(Long id);
public cartItem AddProductToCart(Long productId,User user,int Qty,double total);
public List<cartItem> listCartItem(User user);
public void deleteCardItem(Long id);


public int updateCart(int quantity,Long id,double prix);
public int updateTotal(int quantity,Long id,double prix);

public List<Role>getAll();
}

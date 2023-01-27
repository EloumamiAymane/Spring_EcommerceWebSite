package ecom.sid.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ecom.sid.dao.CategorieRepository;
import ecom.sid.dao.ProduitRepository;
import ecom.sid.entities.Categorie;
import ecom.sid.entities.Produit;
import ecom.sid.services.IboutiqueMetier;

@Controller
public class ProductController {
	@Autowired
	private IboutiqueMetier iboutiqueMetier;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private ProduitRepository produitRepository;
	@Value("${dir.image}")
	private String dirImage;
	
	/*
	 * @GetMapping(path="/ProductForm") public String index(Model model) {
	 * 
	 * return "ProductForm"; }
	 */
		
		@PostMapping(path="/saveProduit")
		public String save(Model model,@ModelAttribute(name="produit") Produit produit,
				@RequestParam(name="photo")MultipartFile multipartFile
				
				) throws IOException {
		
			if(!(multipartFile.isEmpty())) {	produit.setImage(multipartFile.getOriginalFilename());}
			
			iboutiqueMetier.ajouterProduit(produit);
			if(!(multipartFile.isEmpty())) {
				produit.setImage(multipartFile.getOriginalFilename());
				multipartFile.transferTo(new File(dirImage+produit.getId()));
			}
	
		
			
			
			
			
			//model.addAttribute("produit",iboutiqueMetier.ajouterProduit(produit));	
			
			return "redirect:/ProductForm";
		}
		
	
		
		
		
		  @GetMapping(path="/ProductForm") public String AddCategorie(Model model,
		  
		  @RequestParam(name="motCle",defaultValue = "") String motCle,
		  
		  @RequestParam(name="page",defaultValue = "0") int page,
		  
		  @RequestParam(name="size",defaultValue = "5") int size) {
				model.addAttribute("listCat", categorieRepository.findAll());
				model.addAttribute("categorie", new Categorie());
				model.addAttribute("produit", new Produit());
			  Page <Produit>
		  PageProduit=produitRepository.findByDesignationContains(motCle,
		  PageRequest.of(page, size)); model.addAttribute("listProduit",PageProduit);
		  model.addAttribute("pages", new int[PageProduit.getTotalPages()]);
		  model.addAttribute("currentPage", page); model.addAttribute("motCle",
		  motCle); model.addAttribute("size", size); Produit produit =new Produit();
		  model.addAttribute("produit", produit);
		  
		  return "ProductForm"; }
		  
		  
		 
		  @RequestMapping(value="/getPhoto",produces = MediaType.IMAGE_JPEG_VALUE)
		  @ResponseBody	  
		  
		  public byte [] getPhoto(Long id) throws Exception{
			  File f=new File(dirImage+id);
			  System.out.println(dirImage);
			 //return IOUtils.toByteArray(new FileInputStream(f));
			  return org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(f));
		  }
		  
		  @GetMapping(path="/deleteProduit/{id}")
		  public String delete(@PathVariable Long id) {
			  iboutiqueMetier.SupprrimerProduit(id);
			  return "redirect:/ProductForm";
			  
		  }
			@GetMapping(path="/editproduit")
			public String editCategorie(Model model,  Long id) {
				Produit produit=produitRepository.findById(id).get();
				model.addAttribute("listCat", categorieRepository.findAll());
				model.addAttribute("produit", produit);
				
				return "ProductForm";
			}
			
			@GetMapping(path = "/filter")
			public String filter(Model model,

					@RequestParam(name = "prix0", defaultValue = "") String motCle,

					@RequestParam(name = "page", defaultValue = "0") int page,

					@RequestParam(name = "size", defaultValue = "5") int size,
					@RequestParam(value = "prix0") double prix0,

					@RequestParam(value = "prix1") double prix1) {

				model.addAttribute("produit", new Produit());
				Page<Produit> PageProduit = produitRepository.findByPrix(prix0, prix1,PageRequest.of(page, size));
				model.addAttribute("listProduit", PageProduit);
				model.addAttribute("pages", new int[PageProduit.getTotalPages()]);
				model.addAttribute("currentPage", page);
				model.addAttribute("motCle", motCle);
				model.addAttribute("size", size);
				Produit produit = new Produit();
				model.addAttribute("produit", produit);

				return "shop";
			}
		  
		
}

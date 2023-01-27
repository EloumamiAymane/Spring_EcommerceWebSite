package ecom.sid.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


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
import ecom.sid.entities.Categorie;
import ecom.sid.services.IboutiqueMetier;
@Controller
public class CategoryController {
	@Autowired
private IboutiqueMetier iboutiqueMetier;
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Value("${x}")
	private String dir;
	@PostMapping(path="/saveCategorie")
	public String save(Model model,@ModelAttribute(name="categorie") Categorie categorie,
			@RequestParam("ImageFile")MultipartFile multipartFile
			
			) throws IOException {
		
		if(!(multipartFile.isEmpty())) {	 categorie.setPhoto(multipartFile.getOriginalFilename());}
		
		iboutiqueMetier.ajouterCategorie( categorie);
		if(!(multipartFile.isEmpty())) {
			 categorie.setPhoto(multipartFile.getOriginalFilename());
			multipartFile.transferTo(new File(dir+ categorie.getIdCategorie()));
		}
		//model.addAttribute("categorie",iboutiqueMetier.ajouterCategorie(categorie));	
		
		return "redirect:/CategorieForm";
	}
	
	
	
	
	@GetMapping(path="/CategorieForm")
	public String AddCategorie(Model model,
			@RequestParam(name="motCle",defaultValue = "") String motCle,
			@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="size",defaultValue = "5") int size) {
		Page <Categorie> PageCategorie=categorieRepository.findByNomCategorieContains(motCle, PageRequest.of(page, size));
		model.addAttribute("listCategorie", PageCategorie);
		model.addAttribute("pages", new int[PageCategorie.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("motCle", motCle);
		model.addAttribute("size", size);
		Categorie categorie=new Categorie();
		model.addAttribute("categorie", categorie);

		return "CategorieForm";
	}
	
	@GetMapping("/deleteCaregorie/{id}")
	public String DeleteCategorie(@PathVariable Long id) {
		iboutiqueMetier.SupprrimerCategorie(id);
		return "redirect:/CategorieForm";
	}

	@GetMapping(path="/editCategorie")
	public String editCategorie(Model model,  Long id) {
		Categorie categorie=categorieRepository.findById(id).get();
		model.addAttribute("categorie", categorie);
		
		return "CategorieForm";
	}
	
	 @RequestMapping(value="/getImage",produces = MediaType.IMAGE_JPEG_VALUE)
	  @ResponseBody	  
	  
	  public byte [] getPhoto(Long id) throws Exception{
		  File f=new File(dir+id);
		 //return IOUtils.toByteArray(new FileInputStream(f));
		  return org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(f));
	  }
	
	
	
}

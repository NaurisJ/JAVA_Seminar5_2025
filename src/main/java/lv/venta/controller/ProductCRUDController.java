package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {

	@Autowired
	private IProductCRUDService prodService;

	@GetMapping("/all") // localhost:8080/product/crud/all
	public String getControllerGetAllProducts(Model model) {

		try {
			ArrayList<Product> allProducts = prodService.retrieveAll();
			model.addAttribute("package", allProducts);
			return "show-multiple-products";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}

	@GetMapping("/one") // localhost:8080/product/crud/one?id=1
	public String getControllerGetOneProductById(@RequestParam(name = "id") long id, Model model) {
		try {
			Product productFound = prodService.retrieveById(id);
			model.addAttribute("package", productFound);
			return "show-one-product";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/product/crud/all/1
	public String getControllerGetOneProductById2(@PathVariable(name = "id") long id, Model model) {

		try {
			Product productFound = prodService.retrieveById(id);
			model.addAttribute("package", productFound);
			return "show-one-product";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}
	
	
	@GetMapping("/create")//localhost:8080/product/crud/create
	public String getControllerCreateNewProduct(Model model) {
		model.addAttribute("product", new Product());
		return "create-product";//parādīs create-product.html lapu
	}
	@PostMapping("/create")
	public String postControllerCreateNewProduct(@Valid Product product, BindingResult result, Model model) {//tiek iegūsts jau aizpildītais produkts
		
		if (result.hasErrors()) {
			return "create-product"; // ja bus validaciju parkapumi, tad paliekam taja pasa lapa
		}
		
		
		try {
			prodService.createProduct(product.getTitle(), product.getDescription(),
					product.getPrice(), product.getQuantity());
			
			return "redirect:/product/crud/all";//pāŗslēdzams uz all url adresi
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
		
		
		
		

		
		
	}
	
	
	@GetMapping("/update/{id}")
	public String getControllerUpdateProduct(@PathVariable(name = "id") long id, Model model) {
		try {
			Product productToUpdate = prodService.retrieveById(id);
			model.addAttribute("product", productToUpdate);
			model.addAttribute("title",productToUpdate.getTitle());
			return "update-product"; //paradis update-product.html
		} catch (Exception e) {
			model.addAttribute("package",e.getMessage());
			return "show-error";
		}

	}
	
	@PostMapping("/update/{id}")
	public String postControllerUpdateProductById
	(@PathVariable(name = "id") long id, @Valid Product product, BindingResult result, Model model)
	{
		if(result.hasErrors())
		{
			try
			{
				Product p = prodService.retrieveById(id);
				model.addAttribute("title", p.getTitle());
				return "update-product";
			}catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "show-error";
			}
			
		}
		
		
		try {
			prodService.updateProductById(id, product.getDescription(), product.getPrice(), product.getQuantity());
			return "redirect:/product/crud/all";//pāŗslēdzams uz all url adresi
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}
	
	
	@GetMapping("/delete/{id}")
	public String getControllerDeleteProductById(@PathVariable(name = "id") long id, Model model) {
		try {
			prodService.deleteById(id);
			model.addAttribute("package", prodService.retrieveAll());
			return "show-multiple-products";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}
	
	//izveidot get mapping prieks dzesanas, kur tiek pardots ari id
	//meginat dzest, bet ja ir kludas, paradit show-error lapu
	

}
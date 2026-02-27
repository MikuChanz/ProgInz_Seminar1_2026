package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.model.Category;
import lv.venta.model.Product;
//kontrolieru klase ar endpointiem
@Controller
public class SimpleController {
	
	
	ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(
			new Product("Ābols", 0.99f, "Salds un garšīgs", Category.fruit, 10),
			new Product("Burkāns", 2.84f, "Oranžš", Category.vegetable, 40),
			new Product("Vīnogas", 4.99f, "Violetas", Category.fruit, 3)
			)
			);
	
	
	@GetMapping("/showmessage") //localhost:8080/showmessage
	public String getShowMessage() {
		System.out.println("Mans pirmais kontrolieris ir izsaukts");
		return "msg";//šī koda rinda parādāsi msg.html lapu
		
	}
	
	@GetMapping("/showdata")//localhost:8080/showdata
	public String getShowData(Model model) {
		System.out.println("Otrais kontrolieris ir izsaukts");
		Random rand = new Random();
		String text = "Karina " + rand.nextInt();
		model.addAttribute("package", text);
		return "data";//sī koda rinda parādīs data.html lapu
	}
	
	
	@GetMapping("/getproduct")//localhost:8080/getproduct
	private String getGetProduct(Model model) {
		Product prod = new Product("Ābols", 0.99f, "Salds un garšīgs", Category.fruit, 10);
		model.addAttribute("package", prod);
		return "show-one-product";//sī koda rinda parādīs show-one-product.html lapu
		
	}
	
	
	@GetMapping("/getallproducts")//localhost:8080/getallproducts
	public String getGetAllProducts(Model model) {
		model.addAttribute("package",allProducts);
		return "show-all-products";//sī koda rinda parādīs show-all-products.html lapu
	}
	
	@GetMapping("/getallproducts/{id}")//localhost:8080/getallproducts/1
	public String getGetAllProductsById(@PathVariable(name = "id") int id, Model model) {
		if(id < 0) {
			model.addAttribute("package", "Produkta id nevar būt negatīvs");
			return "error-page";//šī koda rinda parādīs error-page.html lapu ar ziņū, ka id nevar būt negatīvs
		}
		
		for(Product tempP : allProducts) {
			if(tempP.getId()==id) {
				model.addAttribute("package", tempP);
				return "show-one-product";//sī koda rinda parādīs show-one-product.html lapu
			}
		}
		
		model.addAttribute("package", "Produkts ar tādu id neeksistē");
		return "error-page";
		
		
	}
	
	//iesakuma parada lapu kura vares ievadit jaunu produktu. Lidzi lapai padodam tuksu produktu
	@GetMapping("/add") //localhost:8080/add
	public String getAddProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", Category.values());
		return "add-one-product";
	}
	
	
	//pec submit pogas nospiesanas html puse sanemam jau aizpilditu produktu
	@PostMapping("/add")
	public String postAddProduct(Product product) {
		//TODO veikt validacijas
		System.out.println(product);
		
		Product newProduct = new Product(product.getTitle(), product.getPrice(), product.getDescription(),
				product.getCategory(), product.getQuantity());
		
		allProducts.add(newProduct);
		return "redirect:/getallproducts";
	}
	
	

}
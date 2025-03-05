package lv.venta.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyFirstController {
	
	private Random rand = new Random();
	
	@GetMapping("/simple") // localhost:8080/simple
	public String myFirstGetController() {
		System.out.println("Pirmais kontrolieris nostradaja");
		return "simple-page"; // paradit simple-page.html lapu
	}
	
	@GetMapping("/getData")
	public String getControllerSendData(Model model) {
		System.out.println("Send data kontrolieris nostradaja");
		String data = "Karina -> " + rand.nextInt(0, 101);
		model.addAttribute("package");
		return "show-data-page"; // paradis show-data-page.html failu, kura bus jau ieveitots data vertiba
	}

}

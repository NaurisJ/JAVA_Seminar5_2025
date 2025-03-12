package lv.venta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Seminar5Application {

	public static void main(String[] args) {
		SpringApplication.run(Seminar5Application.class, args);
	}

	
	public CommandLineRunner testDB(IProductRepo prodRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String...args) throws Exception {
				Product p1 = new Product("Ābols", "Garšīgs", 0.99f, 4);
				Product p2 = new Product("Gurķis", "Zaļš", 1.99f, 10);
				Product p3 = new Product("Tomāts", "Sarkans", 3.49f, 100);
				
				//saglabajam produktus ari datu baze
				prodRepo.save(p1);
				prodRepo.save(p2);
				prodRepo.save(p3);
				
				System.out.println("CIk produkti ir DB: " + prodRepo.count());
			}
		};
	}
}

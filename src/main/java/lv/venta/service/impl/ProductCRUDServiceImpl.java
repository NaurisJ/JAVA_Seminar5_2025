package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductCRUDService;

@Service
public class ProductCRUDServiceImpl implements IProductCRUDService{
	
	@Autowired
	private IProductRepo prodRepo;

	@Override
	public void createProduct(String title, String description, float price, int quantity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if (prodRepo.count() == 0 ) {
			throw new Exception("Product table is empty!");
		}
		
		ArrayList<Product> result = (ArrayList<Product>) prodRepo.findAll();
		
		return result;
	}

	@Override
	public Product retrieveById(long id) throws Exception {
		if (id <= 0) {
			throw new Exception("Negative or '0' id!");
		}
		
		if (!prodRepo.existsById(id)) {
			throw new Exception("Product with ID doesn't exist");
		}

		return prodRepo.findById(id).get();
		
		
	}

	@Override
	public void updateProductByID(long id, String title, String description, float price, int quantity) throws Exception {
		// sameklejam produktu, kuru gribam rediget - var izmantot retrieve byid
		
		Product productToUpdate = retrieveById(id);
		
		// veicam parbaudes uz input parametriem (iznemot id)
		if (description == null || !description.matches("[A-Za-z :;]{3,20}") || price < 0 || price > 1000
				|| quantity < 0 || quantity > 100) {
			throw new Exception("Incorrect input params");
		}
		
		if (!productToUpdate.getDescription().equals(description)) {
			productToUpdate.setDescription(description);
		}
		
		if (productToUpdate.getPrice() != price) {
			productToUpdate.setPrice(price);
		}
		
		if (productToUpdate.getQuantity() != quantity) {
			productToUpdate.setQuantity(quantity);
		}
		
		prodRepo.save(productToUpdate); // te nostradas kaa sql vaicajums
		
	}

	@Override
	public void deleteByID(int id) throws Exception {
		
		
		Product productToDelete = retrieveById(id);
		
		prodRepo.delete(productToDelete);
		
	}

}

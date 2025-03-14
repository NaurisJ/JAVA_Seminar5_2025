package lv.venta.repo;


import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

//Product - uz kuru modelu klasi/tabulu sis repo stradas
// Long - pRoduct klase primary key ir ka long datu tips (seit jaizmanto referencu ...)
public interface IProductRepo extends CrudRepository<Product, Long>{

	
	// SELECT * FROM product_table WHERE title = ?1 and description = ?2 and price = ?3
	// ?1 - pirmais padotais paramters
	// ?2 - otrais
	// ?3 - tresais
	
	public abstract Product findByTitleAndDescriptionAndPrice(String title, String description, float price);

	public abstract boolean existsByTitleAndDescriptionAndPrice(String title, String description, int quantity);

}

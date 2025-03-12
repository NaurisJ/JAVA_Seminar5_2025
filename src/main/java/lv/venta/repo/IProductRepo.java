package lv.venta.repo;


import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

//Product - uz kuru modelu klasi/tabulu sis repo stradas
// Long - pRoduct klase primary key ir ka long datu tips (seit jaizmanto referencu ...)
public interface IProductRepo extends CrudRepository<Product, Long>{

}

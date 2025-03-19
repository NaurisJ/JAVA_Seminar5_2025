package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IProductCRUDService {

	//CRUD
	//C - create
	public abstract void 
	createProduct(String title, String description, float price, int quantity) throws Exception;
	//R - retrieve all
	public abstract ArrayList<Product> retrieveAll() throws Exception;	
	//R - retrieve by id
	public abstract Product retrieveById(long id) throws Exception;
	//U - update
	public abstract void 
	updateProductById(long id, String description, float price, int quantity) throws Exception;
	//D - delete
	public abstract void deleteById(long id) throws Exception;
	
}
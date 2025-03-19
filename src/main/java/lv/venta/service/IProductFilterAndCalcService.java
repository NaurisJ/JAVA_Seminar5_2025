package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IProductFilterAndCalcService {

	public abstract ArrayList<Product> getAllProductsWherePriceLessThan(float threshold) throws Exception;
	
	
	public abstract ArrayList<Product> getAllProductsWhereQuantityLargerThan(int threshold) throws Exception;
	
	public abstract ArrayList<Product> getAllProductsWhereTitleOrDescriptionLikeContains(String text);
	
	public abstract float getIncomeFromProducts() throws Exception;
	
	
}

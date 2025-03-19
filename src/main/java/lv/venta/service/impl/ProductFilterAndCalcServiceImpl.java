package lv.venta.service.impl;

import java.util.ArrayList;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductFilterAndCalcService;

@Service
public class ProductFilterAndCalcServiceImpl implements IProductFilterAndCalcService{

	@Autowired
	private IProductRepo prodRepo;
	
	@Override
	public ArrayList<Product> getAllProductsWherePriceLessThan(float threshold) throws Exception{
		if (threshold < 0 || threshold > 1000) {
			throw new Exception("Incorrect threshold");
		}
		
		ArrayList<Product> result = prodRepo.findByPriceLessThanEqual(threshold);
		if (result.isEmpty()) {
			throw new Exception("There is no product that price is smaller than " + threshold);
		}
		
		return result;
	}
	
	
	
	
	
	public  ArrayList<Product> getAllProductsWhereQuantityLargerThan(int threshold) throws Exception{
		if (threshold < 0 || threshold > 1000) {
			throw new Exception("Incorrect threshold");
		}
		
		ArrayList<Product> result = prodRepo.findByQuantityGreaterThan(threshold);
		
		if (result.isEmpty()) {
			throw new Exception("There is no quantity larger than " + threshold);
		}
		
		return result;
		
	}
	
	@Override
	public ArrayList<Product> getAllProductsWhereTitleOrDescriptionContains(String text) throws Exception {
		if(text == null)//TODO var pievienot pārbaudi, ka text ir vismaz 3 simboli
		{
			throw new Exception("Incorrect input param");
		}
		ArrayList<Product> result = 
		prodRepo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(text, text);
		
		if(result.isEmpty())
		{
			throw new Exception("There is no product that has text: " + text);
		}
		
		return result;
		
		
	}
	
	public  float getIncomeFromProducts() throws Exception {
		if (prodRepo.count() == 0) {
			throw new Exception("There is no product in DB");
		}
		return prodRepo.calculateSumOfProductValues();
		
	}
	
}

package lv.venta.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {

	@Setter(value = AccessLevel.NONE)
	private long id;
	
	
	private String title;
	private String description;
	private float price;
	private int quantity;
	
	
	
	
	public Product(String inputTitle, String inputDescription, float inputPrice, int inputQuantity) {
		setTitle(inputTitle);
		setDescription(inputDescription);
		setPrice(inputPrice);
		setQuantity(inputQuantity);
	}
	
	
}

package com.shoppingcart.assembler;

import com.shoppingcart.dto.ProductDto;
import com.shoppingcart.model.Product;

public class ProductAssembler {

	public Product writeDto(ProductDto productDto) {
		Product product = new Product();
		product.setProductName(productDto.getProductName());
		product.setProductDescription(productDto.getProductDescription());
		return product;
	}
}

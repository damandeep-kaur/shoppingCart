package com.shoppingcart.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {
	private static final long serialVersionUID = 4145574083833380135L;
	private Integer productId;
	private String productName;
	private String productDescription;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public ProductDto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + "]";
	}
}

package com.shoppingcart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.assembler.ProductAssembler;

import com.shoppingcart.dto.ProductDto;
import com.shoppingcart.model.Product;

import com.shoppingcart.response.Response;
import com.shoppingcart.service.IProductService;
import com.shoppingcart.service.IRedis;

@RestController
@RequestMapping("/api2")
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private IRedis iredis;
	ProductAssembler productAssembler = new ProductAssembler();

	@PostMapping("/add")
	public Response add(HttpServletRequest req, @RequestBody ProductDto productDto) {
		Product product = productAssembler.writeDto(productDto);
		Object token = iredis.getValue(req.getHeader("Authorization"));
		Response response = new Response();
		if (token == null) {
			response.setCode(HttpStatus.UNAUTHORIZED.value());
			response.setMessage("You are not allowed to enter the product");
			response.setResponse("You have to Login again");

			return response;
		} else {
			response.setCode(HttpStatus.ACCEPTED.value());
			response.setMessage("Product added successfully");
			response.setResponse("You may Logout Now");
			productService.addProduct(product);
			return response;
		}

	}

}

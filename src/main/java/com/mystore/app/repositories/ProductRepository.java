package com.mystore.app.repositories;

import com.mystore.app.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    // TODO
	List<Product> findByname(String name);
	
	List<Product> findByCategory(String category);
	
	List<Product> findByPriceBetween(Double minPrice,Double maxPrice);
	
	List<Product> findByStockQuantityBetween(Integer minStock,Integer maxPrice);

}

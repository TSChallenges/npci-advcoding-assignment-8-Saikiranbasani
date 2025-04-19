package com.mystore.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mystore.app.entity.Product;
import com.mystore.app.repositories.ProductRepository;


@Service
public class ProductService {

    private Integer currentId = 1;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setId(currentId++);
        productRepository.save(product);
        return product;
    }

    public Page<Product> getAllProducts(int page, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, pageSize, sort); //  This is valid
        return productRepository.findAll(pageable);               // findAll expects Pageable
    }


    public Product getProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.get();
    }

    public Product updateProduct(Integer id, Product product) {
        Product p = productRepository.findById(id).get();
        if (p == null) return null;
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        p.setStockQuantity(product.getStockQuantity());
        productRepository.save(p);
        return p;
    }

    public String deleteProduct(Integer id) {
        Product p = productRepository.findById(id).get();
        if (p == null) return "Product Not Found";
        productRepository.delete(p);
        return "Product Deleted Successfully";
    }

    // TODO: Method to search products by name
    public List<Product> searchByName(String name){
    	return productRepository.findByname(name);
    }


    // TODO: Method to filter products by category
    public List<Product> filtebycategory(String category){
    	return productRepository.findByCategory(category);
    }


    // TODO: Method to filter products by price range
    public List<Product> filterbyPrice(Double minPrice,Double maxPrice){
    	 return productRepository.findByPriceBetween(minPrice, maxPrice);
    }


    // TODO: Method to filter products by stock quantity range
    public List<Product> filterByStockPrice(Integer minStock,Integer maxStock){
    	return productRepository.findByStockQuantityBetween(minStock, maxStock);
    }


}

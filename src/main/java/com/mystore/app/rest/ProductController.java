package com.mystore.app.rest;

import com.mystore.app.entity.Product;
import com.mystore.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
 // TODO: API to filter products by category
    @GetMapping("/filter/category")
    public ResponseEntity<List<Product>> getbycategory(@RequestParam String category){
    	List<Product> products=productService.filtebycategory(category);
    	return ResponseEntity.ok(products);
    }
    
    // TODO: API to search products by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchbyname(@RequestParam String name){
    	List<Product> products=productService.searchByName(name);
    	return ResponseEntity.ok(products);
    }
    

    


    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product) {
        Product p = productService.addProduct(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

//    @GetMapping("")
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }
    @GetMapping("")
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        return productService.getAllProducts(page, pageSize, sortBy, sortDir);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product p = productService.getProduct(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product product) {
        Product p = productService.updateProduct(id, product);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

   



    // TODO: API to filter products by price range
    @GetMapping("/filter/price")
    public ResponseEntity<List<Product>> findByPriceRange(@Valid @RequestParam Double minPrice,@RequestParam Double maxprice){
    	List<Product> products=productService.filterbyPrice(minPrice, maxprice);
    	return ResponseEntity.ok(products);
    }


    // TODO: API to filter products by stock quantity range
    @GetMapping("/filter/stock")
    public ResponseEntity<List<Product>> findByStockRange(@RequestParam Integer minStock,@RequestParam Integer maxStock){
    	List<Product> products=productService.filterByStockPrice(minStock, maxStock);
    	return ResponseEntity.ok(products);

    }


}

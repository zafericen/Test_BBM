package me.nullpointerexception.backend.controller;
import me.nullpointerexception.backend.model.product.Product;
import me.nullpointerexception.backend.pojo.AddProductInfo;
import me.nullpointerexception.backend.pojo.UpdateProductInfo;
import me.nullpointerexception.backend.repository.ProductRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    ProductRepository productRepository;

    @PostMapping(value = "merchant/addProduct")
    public ResponseEntity<Object> addProduct (@RequestBody AddProductInfo addProductInfo){
        Product product = new Product(UUID.randomUUID(), addProductInfo.getName(), addProductInfo.getDescription(), addProductInfo.getPrice(), 0, addProductInfo.getCategory(), 1);
        try {
            productRepository.addProduct(product);
            return new ResponseEntity<>("Product added successfully.", HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Failed to add the product.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "user/getProduct/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable String id){
        Product product = productRepository.getProduct(id);
        if (product == null) {
            return new ResponseEntity<>("Product not found.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(value = "merchant/deleteProduct/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id){
        try {
            productRepository.removeProduct(id);
            return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Failed to delete the product.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "merchant/updateProduct/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody UpdateProductInfo updateProductInfo, @PathVariable String id){
        Product product = new Product(UUID.fromString(id), updateProductInfo.getName(), updateProductInfo.getDescription(), updateProductInfo.getPrice(), 0, UUID.fromString(updateProductInfo.getCategoryID()), 1);
        try {
            productRepository.updateProduct(product);
            return new ResponseEntity<>("Product updated successfully.", HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Failed to update the product.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "user/search/{keywords}")
    public ResponseEntity<Object> searchProduct(@PathVariable String keywords){
        productRepository.searchProduct(keywords);
        if (productRepository.searchProduct(keywords).isEmpty()) {
            return new ResponseEntity<>("No product found.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productRepository.searchProduct(keywords), HttpStatus.OK);
    }

    @GetMapping(value = "home/for-you/{userID}")
    public ResponseEntity<Object> recommendProduct(@PathVariable String userID){
        List<Product> productList = productRepository.getProducts(productRepository.recommendProduct(userID));
        if (productList.isEmpty()) {
            return new ResponseEntity<>("No product found.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
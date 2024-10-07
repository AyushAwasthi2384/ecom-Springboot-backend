// package com.clothing.shoppingwebapp.service;

// import com.clothing.shoppingwebapp.model.Product;
// import com.clothing.shoppingwebapp.repository.ProductRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ProductService {

//     private final ProductRepository productRepository;

//     @Autowired
//     public ProductService(ProductRepository productRepository) {
//         this.productRepository = productRepository;
//     }

//     public List<Product> getAllProducts() {
//         return productRepository.findAll(); // Fetch all products from the database
//     }

//     public Product getProductById(Long id) {
//         // Fetch a product by ID and throw an exception if not found
//         return productRepository.findById(id)
//                 .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
//     }
// }


package com.clothing.shoppingwebapp.service;

import com.clothing.shoppingwebapp.model.Product;
import com.clothing.shoppingwebapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get a product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Update an existing product
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setImageUrl(productDetails.getImageUrl());
        return productRepository.save(product);
    }

    // Delete a product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

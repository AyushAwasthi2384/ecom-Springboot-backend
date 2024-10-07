// package com.clothing.shoppingwebapp.controller;

// import com.clothing.shoppingwebapp.model.Product;
// import com.clothing.shoppingwebapp.service.ProductService;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// @Controller
// public class ProductController {

//     private final ProductService productService;

//     public ProductController(ProductService productService) {
//         this.productService = productService;
//     }

//     @GetMapping("/products")
//     public String productList(Model model) {
//         model.addAttribute("products", productService.getAllProducts());
//         return "productList"; // Thymeleaf will look for productList.html in the templates folder
//     }

//     @GetMapping("/product/{id}")
//     public String productDetail(@PathVariable Long id, Model model) {
//         Product product = productService.getProductById(id);
//         model.addAttribute("product", product); // This should be 'product', not 'products'
//         return "productDetail"; // Thymeleaf will look for productDetail.html in the templates folder
//     }
// }


package com.clothing.shoppingwebapp.controller;

import com.clothing.shoppingwebapp.model.Product;
import com.clothing.shoppingwebapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get a specific product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Update a product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

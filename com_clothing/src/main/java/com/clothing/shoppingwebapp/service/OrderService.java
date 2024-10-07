// package com.clothing.shoppingwebapp.service;

// import com.clothing.shoppingwebapp.model.Order;
// import com.clothing.shoppingwebapp.model.Product; 
// import com.clothing.shoppingwebapp.repository.OrderRepository;
// import com.clothing.shoppingwebapp.repository.ProductRepository; 
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.math.BigDecimal;
// import java.util.List;

// @Service
// public class OrderService {

//     private final OrderRepository orderRepository;
//     private final ProductRepository productRepository; 

//     @Autowired
//     public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
//         this.orderRepository = orderRepository; 
//         this.productRepository = productRepository; 
//     }

//     public Order placeOrder(Order order) {
//         // Validate order quantity
//         if (order.getQuantity() <= 0) {
//             throw new IllegalArgumentException("Quantity must be greater than zero");
//         }

//         // Validate customer details
//         if (order.getCustomerName() == null || order.getCustomerName().isEmpty()) {
//             throw new IllegalArgumentException("Customer name is required");
//         }
//         if (order.getCustomerEmail() == null || order.getCustomerEmail().isEmpty()) {
//             throw new IllegalArgumentException("Customer email is required");
//         }
//         if (order.getCustomerAddress() == null || order.getCustomerAddress().isEmpty()) {
//             throw new IllegalArgumentException("Customer address is required");
//         }

//         // Fetch product details to calculate total price
//         Product product = productRepository.findById(order.getProduct().getId())
//                 .orElseThrow(() -> new IllegalArgumentException("Product not found"));

//         // Convert product price to BigDecimal and calculate total price
//         BigDecimal productPrice = BigDecimal.valueOf(product.getPrice()); // Convert Double to BigDecimal
//         BigDecimal totalPrice = productPrice.multiply(BigDecimal.valueOf(order.getQuantity())); // Calculate total price

//         order.setTotalPrice(totalPrice); // Set the calculated total price

//         return orderRepository.save(order); // Save the order with the total price
//     }

//     public List<Order> getAllOrders() {
//         return orderRepository.findAll();
//     }
// }

package com.clothing.shoppingwebapp.service;

import com.clothing.shoppingwebapp.model.Order;
import com.clothing.shoppingwebapp.model.Product;
import com.clothing.shoppingwebapp.repository.OrderRepository;
import com.clothing.shoppingwebapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Place a new order
    public Order placeOrder(Order order) {
        if (order.getProduct() == null) {
            throw new IllegalArgumentException("Order product cannot be null");
        }
        validateOrder(order);
        Product product = productRepository.findById(order.getProduct().getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        BigDecimal totalPrice = calculateTotalPrice(product.getPrice(), order.getQuantity());
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    // Get an order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
    }

    // Get all orders (Admin)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Update an order
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        order.setQuantity(orderDetails.getQuantity());
        order.setCustomerName(orderDetails.getCustomerName());
        order.setCustomerEmail(orderDetails.getCustomerEmail());
        order.setCustomerAddress(orderDetails.getCustomerAddress());
        return orderRepository.save(order);
    }

    // Delete an order
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Validate the order before saving
    private void validateOrder(Order order) {
        if (order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }

    // Calculate total price
    private BigDecimal calculateTotalPrice(Double productPrice, int quantity) {
        return BigDecimal.valueOf(productPrice).multiply(BigDecimal.valueOf(quantity));
    }
}

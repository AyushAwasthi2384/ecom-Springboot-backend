// package com.clothing.shoppingwebapp.controller;

// import com.clothing.shoppingwebapp.model.Order;
// import com.clothing.shoppingwebapp.service.OrderService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import java.time.LocalDateTime;

// @Controller
// @RequestMapping("/orders")
// public class OrderController {

//     private final OrderService orderService;

//     @Autowired
//     public OrderController(OrderService orderService) {
//         this.orderService = orderService;
//     }

//     @PostMapping("/place")
//     public String placeOrder(Order order, Model model) {
//         // Set the order date
//         order.setOrderDate(LocalDateTime.now());

//         // Validate the order details before saving
//         try {
//             // Place the order using the service
//             orderService.placeOrder(order);
//         } catch (IllegalArgumentException e) {
//             model.addAttribute("error", e.getMessage()); // Set the error message from the exception
//             return "checkout"; // Redirect back to the checkout page with an error message
//         }

//         // Redirect to a confirmation page after successful order placement
//         return "confirmation"; // Use redirect to ensure new GET request is made
//     }

//     @GetMapping
//     public String getAllOrders(Model model) {
//         model.addAttribute("orders", orderService.getAllOrders());
//         return "orderList"; // Return the order list view
//     }

//     // New endpoint to view the orders list
//     @GetMapping("/list")
//     public String viewOrdersList(Model model) {
//         model.addAttribute("orders", orderService.getAllOrders());
//         return "orderList"; // Return the order list view
//     }
// }

package com.clothing.shoppingwebapp.controller;

import com.clothing.shoppingwebapp.model.Order;
import com.clothing.shoppingwebapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        try {
            Order savedOrder = orderService.placeOrder(order);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            // Log the error
            System.err.println("Error placing order: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
    // // Check if the product is being set correctly
    // if (order.getProduct() == null) {
    // return ResponseEntity.badRequest().body(null);
    // }
    // Order savedOrder = orderService.placeOrder(order);
    // return ResponseEntity.ok(savedOrder);
    // }
    // public Order placeOrder(@RequestBody Order order) {
    // return orderService.placeOrder(order);
    // }

    // Get a specific order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // Get all orders (Admin view)
    @GetMapping("/admin")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Update an order
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderService.updateOrder(id, orderDetails);
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}

package com.clothing.shoppingwebapp.controller;

import com.clothing.shoppingwebapp.model.Order;
import com.clothing.shoppingwebapp.service.OrderService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final OrderService orderService;

    @Autowired
    public CheckoutController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String checkoutPage(Model model) {
        // Prepare the model for the checkout page if necessary
        return "checkout";
    }

    @PostMapping
    public String processCheckout(Order order, Model model) {
        // Perform any necessary validations
        if (order.getQuantity() <= 0) {
            model.addAttribute("error", "Quantity must be greater than zero.");
            return "checkout"; // Return to checkout with an error message
        }

        // Set order date
        order.setOrderDate(LocalDateTime.now());

        // Place the order using the OrderService
        try {
            orderService.placeOrder(order);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "checkout"; // Redirect back to the checkout page with an error message
        }

        return "redirect:/confirmation"; // Redirect to the confirmation page after successful order placement
    }
}

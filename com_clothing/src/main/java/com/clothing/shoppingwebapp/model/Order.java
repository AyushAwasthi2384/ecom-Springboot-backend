package com.clothing.shoppingwebapp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date; // If needed
import java.time.ZoneId; // If needed


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;
    private BigDecimal totalPrice;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private LocalDateTime orderDate; // This will store the order date and time

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}



// {
//     "id": 1,
//     "product": {
//         "id": 1,
//         "name": "Product1",
//         "description": null,
//         "imageUrl": null,
//         "price": 100.0
//     },
//     "quantity": 2,
//     "totalPrice": 200.0,
//     "customerName": null,
//     "customerEmail": null,
//     "customerAddress": null,
//     "orderDate": null
// }




// payload
// {
//     "product": {
//         "id": 1,
//         "name": "Product1",
//         "price": 100.00
//     },
//     "quantity": 1,
//     "customerId": 1
// }


# **Clothing Store Web Application Using Springboot - Documentation**

## **Table of Contents**
1. [Project Overview](#1-project-overview)
2. [System Architecture](#2-system-architecture)
3. [Technologies Used](#3-technologies-used)
4. [Project Setup and Installation](#4-project-setup-and-installation)
5. [Database Configuration](#5-database-configuration)
6. [API Documentation](#6-api-documentation)
   - [Customer APIs](#customer-apis)
   - [Product APIs](#product-apis)
   - [Order APIs](#order-apis)
7. [Testing](#7-testing)
8. [Security](#8-security)
9. [Error Handling](#9-error-handling)
10. [Future Enhancements](#10-future-enhancements)
11. [Credits](#11-credits)

---

## **1. Project Overview**

This project is a **Clothing Store Web Application** built with **Spring Boot** as the backend. The application manages customers, products, and orders with a RESTful API to handle the business logic. It is integrated with a MySQL database for persistent data storage.

The key features of the application include:
- User registration and login for customers.
- Management of products (CRUD operations).
- Order placement and management.
- Integration with a **React** frontend (for future integration).
- Admin access to view and manage all users.
- Unit and integration testing.
- Optional security with Spring Security.

---

## **2. System Architecture**

This is a **three-tier architecture**:
1. **Presentation Layer**: (React Frontend - to be integrated).
2. **Application Layer**: Spring Boot backend with RESTful APIs.
3. **Data Layer**: MySQL database.

The flow of the system:
- Users interact with the frontend to register, log in, browse products, and place orders.
- The frontend sends API requests to the Spring Boot backend.
- The backend processes these requests, interacts with the database, and sends the appropriate response back to the frontend.

---

## **3. Technologies Used**

- **Spring Boot** (version X.X): For building the backend APIs.
- **Spring Data JPA**: For ORM and database interaction.
- **MySQL**: Database for storing customer, product, and order data.
- **Spring Security** (Optional): To secure the APIs and handle user authentication and authorization.
- **Maven**: Build automation tool.
- **JUnit & Mockito**: For unit and integration testing.
- **Tomcat**: Embedded server for running the Spring Boot application.
- **React** (Optional, for frontend integration).

---

## **4. Project Setup and Installation**

### **Prerequisites**
- **Java JDK 17** or later installed.
- **MySQL Server** running on your machine or cloud.
- **Maven** for building the project.

### **Steps for Setup**
1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repo/clothing-store-app.git
   cd clothing-store-app
   ```

2. **Configure the Database**:
   Update the `application.properties` file in the `src/main/resources` directory with your MySQL database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/clothing_store
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Install Dependencies**:
   Run the following command to install all necessary dependencies:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   Use the following command to run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**:
   The application will be running on `http://localhost:8080`.

---

## **5. Database Configuration**

The project uses **MySQL** to manage the database. It will automatically configure the DB.

---

## **6. API Documentation**

## **Customer APIs**

### 1. **Signup Customer**
- **Method**: `POST`
- **Endpoint**: `/api/customers/signup`
- **Description**: Registers a new customer.
- **Request Body**:
  ```json
  {
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "address": "123 Main St"
  }
  ```
- **Responses**:
  - **201 Created**: Customer successfully registered.
    ```json
    {
      "id": 1,
      "name": "John Doe",
      "email": "john@example.com",
      "address": "123 Main St"
    }
    ```
  - **400 Bad Request**: Invalid input.

### 2. **Login Customer**
- **Method**: `POST`
- **Endpoint**: `/api/customers/login`
- **Description**: Authenticates a customer.
- **Request Body**:
  ```json
  {
    "email": "john@example.com",
    "password": "password123"
  }
  ```
- **Responses**:
  - **200 OK**: Authentication successful.
    ```json
    {
      "id": 1,
      "name": "John Doe",
      "email": "john@example.com"
    }
    ```
  - **401 Unauthorized**: Invalid credentials.

### 3. **Get Customer by ID**
- **Method**: `GET`
- **Endpoint**: `/api/customers/{id}`
- **Description**: Retrieves customer details by ID.
- **Path Parameter**:
  - `id`: The ID of the customer.
- **Responses**:
  - **200 OK**: Returns customer details.
    ```json
    {
      "id": 1,
      "name": "John Doe",
      "email": "john@example.com",
      "address": "123 Main St"
    }
    ```
  - **404 Not Found**: Customer not found.

### 4. **Update Customer**
- **Method**: `PUT`
- **Endpoint**: `/api/customers/{id}`
- **Description**: Updates customer details.
- **Path Parameter**:
  - `id`: The ID of the customer.
- **Request Body**:
  ```json
  {
    "name": "John Doe",
    "email": "john@example.com",
    "address": "456 Elm St"
  }
  ```
- **Responses**:
  - **200 OK**: Customer updated successfully.
    ```json
    {
      "id": 1,
      "name": "John Doe",
      "email": "john@example.com",
      "address": "456 Elm St"
    }
    ```
  - **404 Not Found**: Customer not found.

---

## **Product APIs**

### 1. **Get All Products**
- **Method**: `GET`
- **Endpoint**: `/api/products`
- **Description**: Retrieves a list of all products.
- **Responses**:
  - **200 OK**: Returns a list of products.
    ```json
    [
      {
        "id": 1,
        "name": "T-Shirt",
        "price": 19.99,
        "description": "A cool t-shirt"
      },
      {
        "id": 2,
        "name": "Jeans",
        "price": 49.99,
        "description": "Blue jeans"
      }
    ]
    ```

### 2. **Get Product by ID**
- **Method**: `GET`
- **Endpoint**: `/api/products/{id}`
- **Description**: Retrieves a product by its ID.
- **Path Parameter**:
  - `id`: The ID of the product.
- **Responses**:
  - **200 OK**: Returns the product details.
    ```json
    {
      "id": 1,
      "name": "T-Shirt",
      "price": 19.99,
      "description": "A cool t-shirt"
    }
    ```
  - **404 Not Found**: Product not found.

### 3. **Create Product**
- **Method**: `POST`
- **Endpoint**: `/api/products`
- **Description**: Adds a new product.
- **Request Body**:
  ```json
  {
    "name": "Sneakers",
    "price": 59.99,
    "description": "A pair of sneakers"
  }
  ```
- **Responses**:
  - **201 Created**: Product created successfully.
    ```json
    {
      "id": 3,
      "name": "Sneakers",
      "price": 59.99,
      "description": "A pair of sneakers"
    }
    ```

### 4. **Update Product**
- **Method**: `PUT`
- **Endpoint**: `/api/products/{id}`
- **Description**: Updates product details.
- **Path Parameter**:
  - `id`: The ID of the product.
- **Request Body**:
  ```json
  {
    "name": "Sneakers",
    "price": 49.99,
    "description": "Discounted sneakers"
  }
  ```
- **Responses**:
  - **200 OK**: Product updated successfully.
    ```json
    {
      "id": 3,
      "name": "Sneakers",
      "price": 49.99,
      "description": "Discounted sneakers"
    }
    ```
  - **404 Not Found**: Product not found.

---

## **Order APIs**

### 1. **Place Order**
- **Method**: `POST`
- **Endpoint**: `/api/orders/place`
- **Description**: Places a new order for a product.
- **Request Body**:
  ```json
  {
    "product": {
      "id": 1
    },
    "quantity": 2,
    "customerId": 123
  }
  ```
- **Responses**:
  - **201 Created**: Order placed successfully.
    ```json
    {
      "id": 101,
      "product": {
        "id": 1,
        "name": "T-Shirt",
        "price": 19.99
      },
      "quantity": 2,
      "customer": {
        "id": 123,
        "name": "John Doe"
      }
    }
    ```
  - **400 Bad Request**: Invalid input (e.g., product not found, customer not found).

### 2. **Get All Orders**
- **Method**: `GET`
- **Endpoint**: `/api/orders`
- **Description**: Retrieves a list of all orders.
- **Responses**:
  - **200 OK**: Returns a list of orders.
    ```json
    [
      {
        "id": 101,
        "product": {
          "id": 1,
          "name": "T-Shirt",
          "price": 19.99
        },
        "quantity": 2,
        "customer": {
          "id": 123,
          "name": "John Doe"
        }
      }
    ]
    ```

### 3. **Get Order by ID**
- **Method**: `GET`
- **Endpoint**: `/api/orders/{id}`
- **Description**: Retrieves an order by its ID.
- **Path Parameter**:
  - `id`: The ID of the order.
- **Responses**:
  - **200 OK**: Returns the order details.
    ```json
    {
      "id": 101,
      "product": {
        "id": 1,
        "name": "T-Shirt",
        "price": 19.99
      },
      "quantity": 2,
      "customer": {
        "id": 123,
        "name": "John Doe"
      }
    }
    ```
  - **404 Not Found**: Order not found.

---

## Status Codes Summary
- **200 OK**: Request was successful.
- **201 Created**: Resource was created successfully.
- **400 Bad Request**: Invalid request data.
- **401 Unauthorized**: Authentication failed or required.
- **404 Not Found**: Resource not found.

---

## **7. Testing**

The application includes unit and integration tests to ensure functionality.

### **Unit Testing**
- Unit tests are written using **JUnit** and **Mockito** for testing service methods.
- Test cases cover validation logic, business rules, and edge cases.

### **Integration Testing**
- Integration tests ensure the application’s services interact with the database correctly.
- Mock databases or in-memory databases (H2) can be used to simulate MySQL in the test environment.

**Run Tests**:
```bash
mvn test
```

---

## **8. Security**

The application can be secured using **Spring Security**.

### **Customer Authentication**:
- Basic authentication is implemented for login and signup endpoints.
- You can extend this by integrating **JWT tokens** for stateless authentication.

### **Admin Access**:
- Specific routes can be protected by assigning different roles (`USER`, `ADMIN`).
- The `/api/admin/**` routes could be restricted to `ADMIN` users.

Example of security configuration (if used):
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/api/admin/**").hasRole("ADMIN")
            .antMatchers("/api/customers/**").permitAll()
            .and().formLogin();
    }
}
```

---

## **9. Error Handling**

Custom error handling is implemented using `@ControllerAdvice` to return meaningful error messages in case of exceptions.

### **Common Errors**:
- **400 Bad Request**: Validation errors or invalid input.
- **404 Not Found**: Resource not found (customer, product, or order).
- **500 Internal Server Error**: Server-side errors.

Example of error response:
```json
{
  "timestamp": "2024-10-07T12:34:56",
  "status": 404,
  "error": "Not Found",
  "message": "Customer not found",
  "path": "/api/customers/123"
}
```

---

## **10. Future Enhancements**

Some planned improvements for future releases:
- **Integration with React frontend**.
- **Payment Gateway** integration for processing payments.
- **JWT Authentication** for better security and stateless session handling.
- **Product reviews and ratings**.
- **Shopping Cart** functionality.
- **Pagination and Sorting** for product listings.
- **Improved error logging** using external services like **ELK Stack** (Elasticsearch, Logstash, and Kibana).

---

## **11. Credits**

- **Development and Documentation** [AYUSH AWASTHI🚀](https://github.com/AyushAwasthi2384/)

---

### **Note**
I have added comments in all the files for you, you can refer to them and proceed accordingly!
Feel free to reach out to me on my [mail(ayushawasthicoding@gmail.com)](mailto:ayushawasthicoding@gmail.com) or on [LinkedIn](https://www.linkedin.com/in/ayush2384) if you have any issue in this!

**THANKYOU!**

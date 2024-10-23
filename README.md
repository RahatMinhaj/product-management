
# Product Management System

This is a Product Management application built using Domain-Driven Design (DDD) principles with Spring Boot. It allows users to manage products, including CRUD operations on products, and stock updates. The application follows a well-defined architecture, separating concerns across layers for better scalability and maintainability.

## Table of Contents

- [Architecture](#architecture)
- [Domain Model](#domain-model)
- [Application Services](#application-services)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Technologies Used](#technologies-used)

## Architecture

This project follows a **Domain-Driven Design** (DDD) architecture, which focuses on creating a rich domain model and clearly separating the business logic from other concerns. Below is a high-level overview of the architecture:

1. **Domain Layer**: Contains the core business logic and domain models. This layer includes the entities, value objects, and domain services.
   
2. **Application Layer**: Contains services that implements the business logic by interacting with domain models and services. The application layer serves as a bridge between the domain and presentation layers.


4. **Service Layer**: This layer interacts with application layer and infrastructure layer. And can implement any logical operation before hitting the infrastructure layer. 

5. **Infrastructure Layer**: Responsible for interacting with external systems such as databases. It also contains repository implementations for domain models.

6. **Controller Layer**: The REST API that exposes application services to the outside world. This layer uses Spring Boot and exposes HTTP endpoints.

## Domain Model

### Product

The main entity of the system is `Product`. It contains the following attributes:

- `name`: A unique and non-nullable field representing the product's name.
- `description`: A description of the product.
- `price`: A non-nullable `BigDecimal` value representing the product price, which must be greater than zero.
- `stockQuantity`: The stock level of the product, which cannot be negative.
- `category`: An embedded `Category` object representing the product category.

The `Product` class also contains the following methods:
- `create(ProductParam productParam)`: A factory method to create a `Product` from a `ProductParam` object. It validates the fields before creating the product.
- `update(ProductParam param)`: Updates the existing product details.
- `updateStock(Integer stockQuantity)`: Updates the stock quantity with validation to ensure it is non-negative.

### Category

The `Category` class is a value object, and it is embedded inside the `Product` entity. It only has a `name` field to represent the category name.

### Exception Handling

The domain uses a custom Exceptions to handle validation errors.

## Getting Started

### Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17** or later
- **Maven** (for building the project)
- **Git** (for cloning the project)

### Running the Application

#### 1. Clone the repository

```bash
git clone https://github.com/RahatMinhaj/product-management.git
cd product-management
```

#### 2. Build the application

```bash
mvn clean install
```

#### 3. Run the application

```bash
mvn spring-boot:run
```

The application should now be running on `http://localhost:8080`.

## API Endpoints

Once the application is running, you can interact with the following Swagger url:


- ** http://localhost:8080/swagger-ui/index.html

You can use tools like **Postman** or **cURL** to test these endpoints.

## Technologies Used

- **Spring Boot**: Framework for building the REST API.
- **Spring Data JPA**: For database interaction and repository management.
- **Domain-Driven Design (DDD)**: Architectural pattern followed in the application.
- **Lombok**: To reduce boilerplate cod. 
- **H2 in memory Database**
- **Swagger**: For API documentation and testing.

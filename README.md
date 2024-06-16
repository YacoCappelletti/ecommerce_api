# Ecommerce API

Ecommerce API is an API developed with Spring Boot to manage an ecommerce platform. This API allows you to handle products, categories, brands, and sales, as well as generate sales reports in Excel format.

## Table of Contents

-   [Features](#features)
-   [Prerequisites](#prerequisites)
-   [Installation](#installation)
-   [Configuration](#configuration)
-   [Running the Application](#running-the-application)
-   [Usage](#usage)
-   [API Documentation](#api-documentation)
-   [Dockerization](#dockerization)
-   [License](#license)

## Features

-   Management of products, categories, and brands
-   Product search by name, category, and brand
-   Sales management and sales report generation in Excel
-   API documentation using OpenAPI (Swagger)

## Prerequisites

-   Java 17 or higher
-   Maven
-   Docker (optional, for containerized execution)
-   MySQL

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/YacoCappelletti/ecommerce_api.git
    cd ecommerce_api
    ```

2. Build and package the application with Maven:

    ```sh
        mvn clean package
    ```

## Configuration

Configure the database in the application.properties file:

```
spring.datasource.url=${YOUR_DATA_SOURCE_URL}
spring.datasource.username=${USERNAME}
spring.datasource.password=${PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/docs
```

## Running the Application

### Locally

Run the application:

```
java -jar target/ecommerce_api-0.0.1-SNAPSHOT.jar
```

Access the API at http://localhost:8080.

## Dockerization

### Building the Docker Image

Build the Docker image:

```
docker build -t ecommerce_api .
```

### Running with Docker Compose

Run docker-compose:

```sh
docker-compose up
```

Access the API at http://localhost:8080.

## Usage

You can use tools like Postman or cURL to interact with the API. Below are some examples of using the API:

### List Products

```sh

curl -X GET "http://localhost:8080/api/v1/products" -H "accept: application/json"
```

### Search Products by Name

```sh

curl -X GET "http://localhost:8080/api/v1/products/name/{name}" -H "accept: application/json"
```

### API Documentation

The API documentation is available at:

```bash
http://localhost:8080/docs
```

## Dockerization

The project includes a Dockerfile and a docker-compose.yml to facilitate running in containers.

### Dockerfile

```docker

# Use an official OpenJDK runtime as a parent image

FROM openjdk:17-jdk-slim

# Set the working directory in the container

WORKDIR /app

# Copy the project files to the working directory

COPY target/ecommerce_api-0.0.1-SNAPSHOT.jar app.jar

# Make port 8080 available to the world outside this container

EXPOSE 8080

# Run the jar file

ENTRYPOINT ["java", "-jar", "app.jar"]

```

### compose.yml

```yaml

version: '3.8'
services:
    app:
        image: ecommerce_api:latest
        build: .
        ports: - "8080:8080"
        environment:
        - SPRING_DATASOURCE_URL=${YOUR_DATA_SOURCE_URL}
        - SPRING_DATASOURCE_USERNAME=${USERNAME}
        - SPRING_DATASOURCE_PASSWORD=${PASSWORD}
    db:
        image: mysql:8.0
        environment:
        MYSQL_ROOT_PASSWORD: root
        MYSQL_DATABASE: ecommerce
        ports: - "3306:3306"

```

## License

This project is licensed under the MIT License. See the [LICENSE](https://opensource.org/licenses/MIT) file for details.

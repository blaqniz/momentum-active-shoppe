# momentum-active-shoppe
Momentum Active Shoppe

Special Instructions:

# latest version of maven and jdk 11 are needed to build the application

Please clone down the project, open in an IDE or via terminal.
git clone https://github.com/blaqniz/momentum-active-shoppe.git

# Build the project using maven command:
mvn clean install

# Execute the jar file using the following command:
mvn spring-boot:run

# h2 db:
console url: http://localhost:8080/h2-console

Driver class: org.h2.Driver

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: Blank (No password required)

# Endpoints:


# List all available products
  GET http://localhost:8080/api/products
  Example: http://localhost:8080/api/products

# Purchase a product
  PUT http://localhost:8080/api/products/{customerId}/{productId}/{quantity}
  Example:  http://localhost:8080/api/products/1/CKE/3

# Purchase products
  PUT http://localhost:8080/api/products/{customerId}/{productIds}
  Example:  http://localhost:8080/api/products/1/CKE




Thank you.

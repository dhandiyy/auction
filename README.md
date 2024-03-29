# Auction Application API

The API Auction application allows users to have both owner and bidder roles, or just one of them. Owners can only make offer and process transactions on their own items, and each item can only have one offer. Bidders can make transactions on all offers, but the system will perform several checks on the price offered to ensure a successful transaction.

### API DOCUMENTATION
Postman: https://documenter.getpostman.com/view/32333974/2sA35G32Fd

### Instalation
1. Clone the repo
2. Edit application.properties
    ```properties
    spring.application.name=auction-app
    spring.datasource.username=YOUR_DATABASE_USERNAME
    spring.datasource.password=YOUR_DATABASE_PASSWORD
    spring.datasource.url=jdbc:postgresql://localhost:5432/YOUR_DATABASE_NAME
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    app.shopee.jwt.jwt-secret = thisSecret123
    app.shopee.jwt.jwt-name = Auction Application
    app.shopee.jwt.jwt-expired = 3600
    ```
3. Reload Maven with reload pom.xml
4. Run the project


### Tech
This project created with:
- Java 17
- SpringBoot 3.2.3
- Maven 4.0
- JPA
- Postgres
# Bookstore Application Setup Guide

## Frontend Setup

### Prerequisites
- Node.js and npm (Node Package Manager)

### Steps
1. Navigate to the frontend directory:
    ```bash 
    cd frontend
2. Install the required npm packages:
    ```bash
    npm install
3. Start the frontend application:
    ```bash
    npm start
## Backend Setup

### Prerequisites
- Java JDK
- Maven

### Steps
1. Navigate to the backend directory:
    ```bash
    cd backend
2. Build the project using Maven:
    ```bash
    mvn clean install
3. Run the Spring Boot application:
    ```bash
        mvn spring-boot:run
## Configurations

Inside the `application.properties` file in the backend, you will find all necessary configurations for the services used. Make sure to change them according to your setup, including endpoints and configurations for Kafka, MySQL, Redis, Neo4j, GraphQL, and MongoDB.

## Additional Setup for Services

For detailed instructions on installing and setting up external services, please refer to the following official documentation:

- [Kafka](https://kafka.apache.org/documentation/)
- [MySQL](https://dev.mysql.com/doc/)
- [Redis](https://redis.io/documentation)
- [Neo4j](https://neo4j.com/docs/)
- [GraphQL](https://graphql.org/learn/)
- [MongoDB](https://docs.mongodb.com/manual/)

Ensure that these services are correctly set up and running before starting the application.
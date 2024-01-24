# Bookstore Application

## Introduction

![Alt text](docs/demo.gif)
This repository hosts a fully functional website for a Bookstore online shop. The website allows users to register, explore a wide range of books, add them to their shopping cart, and make purchases. It also includes features for administrators to manage book inventories and provides comprehensive statistics on sales and user activities. This project was developed as part of two courses at Shanghai Jiao Tong University (SJTU): Web Application Development (SE2321) and Architecture of Enterprise Application (SE3353). [`docs`](./docs) folder  details all the features added in a step-by-step manner through homework tasks. This includes code snippets, demonstrations, and answered questions about various topics covered in the development process.
For detailed instructions on how to configure and start the application , please refer to the [Setup Guide](./docs/setup.md) in the `docs` folder.

### SE2321 - Web Application Development

During the first course, the basic features of the site were developed, covering a range of modern web development technologies and frameworks:

- **React.js**: Utilized for the frontend development, incorporating the Fetch API for server communication and Ant Design for the user interface.
- **Spring Boot**: Employed for the backend development, focusing on Java Persistence API (JPA) and Inversion of Control (IoC).
- **MySQL**: Used as the Relational Database Management System (RDBMS) for storing and managing application data.

#### Key Features Developed:

- **User Management**: Implementation of customer and administrator roles, with administrative functionalities like disabling/enabling user accounts.
- **Login and Registration**: Developed a user authentication system, handling special cases for disabled accounts and providing distinct interfaces for different user roles.
- **Book Management**: Enabled administrators to manage book details, including features to browse, search, modify, and delete books in the database.
- **Browsing Books**: Both customers and administrators can view and search for books, with detailed information available for each book.
- **Purchasing Books**: Developed functionalities for users to add books to a shopping cart, manage the cart, and complete purchases, affecting inventory and generating orders.
- **Order Management**: Implemented features for viewing and searching order history with various filters.
- **Statistics**: Created tools for analyzing sales and user consumption statistics, presenting the data in graphical and tabular formats.

### SE3353 - Architecture of Enterprise Application

#### Topics Covered:

In this advanced course, we delved into more complex topics and technologies, including:

- **Messaging & Kafka**: Implementing asynchronous communication and order processing using Kafka.
- **Transactions & Caching**: Ensuring transactional integrity and enhancing performance with caching strategies.
- **Microservices Architecture**: Adopting microservices for modular and scalable application development.
- **Database Technologies & Optimizations**:
  - **MySQL Optimizations**: Enhancing performance in relational database operations.
  - **NoSQL Databases**: Incorporating various NoSQL databases for specialized data needs:
    - **Time Series DB (InfluxDB)**
    - **Graph DB (Neo4j)**
    - **Document DB (MongoDB)**
- **Clustering with Nginx**: Using Nginx for load balancing and traffic management.
- **Containerization with Docker**: Employing Docker for application deployment and environment consistency.
- **GraphQL**: Implementing GraphQL for efficient and flexible data queries.
- **Big Data Technologies**:
  - **Hadoop Ecosystem**: Exploring Hadoop, HDFS, HBase, and Hive for big data processing and storage.
  - **Spark**: Utilizing Spark for large-scale data processing.
  - **Storm**: Real-time data processing with Apache Storm.

#### Key Features Added:

- **Asynchronous Ordering Function**: Utilizing Kafka for handling order processes asynchronously.
- **User Identification and Session Management**: Implementing Spring Boot scopes and WebSocket for user session tracking.
- **Caching with Redis**: Developing a caching system using Redis for efficient book retrieval.
- **Unstructured Data Management with MongoDB**: Integrating MongoDB to manage unstructured data, like book cover pictures.
- **Genre Analysis with Neo4j**: Using Neo4j graph database to identify subgenres and recommend books accordingly.
- **Word Count Analysis with Spark**: Employing Spark for a classic word count problem to analyze the frequency of words in book descriptions.


## Acknowledgements

Special thanks to the faculty and teaching assistants at SJTU for their guidance and support throughout these courses. Their commitment has been instrumental in providing a solid foundation in web application development and enterprise application architecture.


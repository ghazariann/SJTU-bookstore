# Bookstore API Usage Guide

This guide provides step-by-step instructions on how to use the Bookstore API endpoints using Postman.

## Prerequisites

- Postman application
- Running Bookstore backend application

## Getting Started

1. Open the Postman application and create a new request.
2. Set the HTTP method to `GET`.
3. Enter the endpoint URL `http://localhost:8080/books`.
4. Click the "Send" button to make the request and retrieve a list of all books.

## Adding a New Book

1. Create a new request in Postman.
2. Set the HTTP method to `POST`.
3. Enter the endpoint URL `http://localhost:8080/books`.
4. In the "Body" tab, select the "raw" option and choose the "JSON" data type.
5. Enter the new book information in JSON format, for example:

    ```json
   {
    "name": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "description": "A novel about the decadence and excess of the Jazz Age",
    "price": 10.99,
    "image": "https://images.example.com/great-gatsby.jpg",
    "type": "Fiction"
    }
    ```
    6. Click the "Send" button to add the new book.

## Retrieving a Book by ID

1. Create a new request in Postman.
2. Set the HTTP method to `GET`.
3. Enter the endpoint URL `http://localhost:8080/books/{id}`, where `{id}` is the ID of the book you want to retrieve.
4. Click the "Send" button to retrieve the book.

## Updating a Book

1. Create a new request in Postman.
2. Set the HTTP method to `PUT`.
3. Enter the endpoint URL `http://localhost:8080/books/{id}`, where `{id}` is the ID of the book you want to update.
4. In the "Body" tab, select the "raw" option and choose the "JSON" data type.
5. Enter the updated book information in JSON format, for example:

    ```json
        {
        "name": "The Great Gatsby",
        "author": "F. Scott Fitzgerald",
        "description": "A novel about the decadence and excess of the Jazz Age",
        "price": 10.99,
        "image": "https://images.example.com/great-gatsby.jpg",
        "type": "Fiction"
   }
6. Click the "Send" button to update the book.

## Deleting a Book

1. Create a new request in Postman.
2. Set the HTTP method to `DELETE`.
3. Enter the endpoint URL `http://localhost:8080/books/{id}`, where `{id}` is the ID of the book you want to delete.
4. Click the "Send" button to delete the book.

Cart

add to cart: GET http://127.0.0.1:8080/cartItems
```json
{
    "book":{
        "id": 2
    },
    "user": {
        "id": 1
    },
    "quantity": 2,
    "price": 35.4
}
```
update cart item: PUT http://127.0.0.1:8080/cartItems/2
```json
{
    "quantity": 1
}
```

delete cart item: DELETE http://127.0.0.1:8080/cartItems/1

list cart items: GET http://127.0.0.1:8080/cartItems


UserAuth 

register: POST http://127.0.0.1:8080/userAuth
```json
{
   "email": "user2",
   "password": "password1",
   "user": {
         "id": 1
   }
}
```

update: PUT http://127.0.0.1:8080/userAuth/1

```json
{
   "password": "password1"
}
```

list userAuth: GET http://127.0.0.1:8080/userAuth

delete  item: DELETE http://127.0.0.1:8080/userAuth/1

users

add user: POST http:////127.0.0.1:8080/users
```json
{
   "name": "user8",
   "telephone": "123456",
   "address": "abcd",
   "userAuth":{
      "email": "df@abs",
      "password": "ddfgs"
   }
}
```

Order

add order: POST http://127.0.0.1:8080/orders

```json
{
   "user": {
      "id": 1
   },
   "orderDate": "2023-05-28",
   "totalPrice": 50.00,
   "shippingAddress": "aaa",
   "orderItems": [
      {
         "book": {
            "id": 1
         },
         
         "quantity": 2,
         "price": 25.00
      },
      {
         "book": {
            "id": 2
         },
         "quantity": 1,
         "price": 25.00
      }
   ]
}
```
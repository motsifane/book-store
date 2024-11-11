# Book Store Backend Application Documentation

## Overview

The Book Store Application is a Spring Boot application that implements CRUD operations for books. Users can view available books, add book to the book store, update a book, and delete a book. The application uses postgres database, Spring Web, Hibernate and JPA.

## Table of Contents

- [Features](#features)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Data Model](#data-model)
- [Testing](#testing)
- [Swagger](#swagger)

## Features

- View available books in the book store.
- Add a book to the book store.
- Update a book in the book store.
- Delete a book in the book store.

## Setup and Installation

1. Clone the repository: `git clone https://github.com/motsifane/book-store.git`
2. Navigate to the project directory: `cd book-store`
3. Build and run the application: `./mvnw spring-boot:run`

## Endpoints

- GET `/api/v1/books`: Displays the list of available books.
- POST `/api/v1/store-book`: Adds book to the book store.
- PUT `/api/v1/books/{id}`: Updates book in the book store.
- DELETE `/api/v1/books/{id}`: Deletes book in the book store.

## Data Model

The application uses a postgres database to store books, with database name [book_db].

### Book Entity

- `id`: Unique identifier
- `title`: Title of the book
- `author`: Author of the book
- `isbn`: Unique generated value

## Testing

Unit tests have been implemented for the Book Store Service and Controller classes.

## Swagger

You can access the Swagger UI by navigating to http://localhost:8082/swagger-ui/index.html. 


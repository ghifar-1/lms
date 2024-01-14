<h1 align="center">
    <br>
    Library Management System
    <br>
</h1>


[![Spring Boot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)]()
[![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)]()
[![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)]()
[![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)]()

an application using Spring Boot, and MySQL. 
* This is a Library Management System with an Admin and a Patron side for the application. 
* Admin can perform CRUD with books/Patrons. 
* Patron can borrow and return a book. 
* Admin and Patron JWT to authenticate login.
* Uses BCryptPasswordEncoder to encrypt the password stored in the database.
* Redirects to forbidden page if a role doesn't have access to the url.
<br>

# APIs
## Authenticate
* singUp.
![singUp](./screenshots/singup.png "singUp")
<br>

* singIn.
![singIn](./screenshots/singin.png "singIn")
<br>

* singOut.
![singOut](./screenshots/singup.png "singOut")
<br>

## Book
### All books present
![Book List ](./screenshots/book_list.png "Book List")

### Book By id 
![Book Details ](./screenshots/book_Details.png "Book Details")

### Adding a book
![Add Book](./screenshots/book_add.png "Add Book")

### Updating book details
![Update Book](./screenshots/book_update.png "Update Book")

### Deleteing book 
![Delete Book](./screenshots/book_delete.png "Delete Book")

## Patrons

### All Patrons 
![Patrons List ](./screenshots/patron_list.png "Patrons List")

### Patron By id 
![Patron Details ](./screenshots/patron_Details.png "Patron Details")

### Adding a Patron By Admin
![Add Patron](./screenshots/patron_add.png "Add Patron")

### Updating patron details
![Update Patron](./screenshots/patron_update.png "Update Patron")

### Deleteing Patron 
![Delete Patron](./screenshots/patron_delete.png "Delete Patron")

## Borrow
### Borrow book
![Borrow Book](./screenshots/borrow_book.png "Borrow Book Page")

### Borrow history of a book
![Borrow history](./screenshots/books_borrow_history.png "Borrow history")

### Book Borrow By User
![Book Borrow By User](./screenshots/books_borrow_by_user.png "Book Borrow By User")

### Return book
![Return Book](./screenshots/return_book.png "Return Book ")

### Unauthorized
![Unauthorized](./screenshots/Unauthorized.png "Unauthorized ")


# Application Properties
```
spring.datasource.url = jdbc:mysql://localhost:3306/yourSchemaName
spring.datasource.username = yourUsername
spring.datasource.password = yourPassword
```
## Using Library Management System
Intellij/Eclipse-->
1. you must create your database first and add database configurations to Application Properties file
2. Let maven resolve dependencies 
3. import lms.postman_collection.json file into postman to communicate with APIs
4. run SpringBootApplication

Prepared By :E.Ghifar Mhd

# 📚 Library Management System (Java)

A console-based Library Management System built with Java, designed to simulate real-world library operations such as managing books, users, and transactions.

---

## 🚀 Features

* 📖 Add and remove books
* 👤 Register users with validation (unique ID & email)
* 🔄 Rent, return, and buy books
* 🧾 Transaction history tracking
* 🔍 Search books by title and author
* 📊 Sort books by title and price
* ⚠️ Robust exception handling system
* 🧠 Clean OOP-based architecture

---

## 🛠️ Technologies Used

* Java (JDK 21)
* Object-Oriented Programming (OOP)
* Collections Framework (HashMap, ArrayList)
* Exception Handling
* IntelliJ IDEA

---

## 🧩 Project Structure

```
src
 ├── main
 │    └── Main.java
 ├── model
 │    ├── Book.java
 │    ├── User.java
 │    ├── Transaction.java
 │    └── TransactionType.java
 ├── service
 │    └── Library.java
 └── exception
      └── LibraryException.java
```

---

## 🖥️ How It Works

The application runs as a console-based interactive menu system:

```
1  - Add Book
2  - Register User
3  - Rent Book
4  - Return Book
5  - Buy Book
6  - Show All Books
7  - Show All Users
8  - Show Transaction History
9  - Search Book By Title
10 - Search Book By Author
11 - Sort Books by Title
12 - Sort Books by Price
0  - Exit
```

Users can perform operations by selecting options from the menu.

---

## 🔐 Error Handling

The system uses a custom exception (`LibraryException`) to handle:

* Duplicate user registration
* Invalid user or book IDs
* Renting unavailable books
* Returning books not owned by the user

---

## 🧪 Sample Scenarios

* Renting a book successfully creates a transaction record
* Returning a book updates availability and logs the transaction
* Attempting invalid operations (e.g. renting the same book twice) throws meaningful errors

---

## 📈 Future Improvements

* REST API version with Spring Boot
* Database integration (MySQL / PostgreSQL)
* Pagination and filtering
* File-based data persistence
* Authentication system

---

## 👨‍💻 Author

Developed by [@memreeger](https://github.com/memreeger)

---

## ⭐️ Final Note

This project demonstrates core backend development concepts such as:

* Designing scalable systems
* Applying OOP principles
* Managing state and transactions
* Handling edge cases and errors effectively

---

# Notes Maker with MySQL (Java + JDBC) - CLI Version

A command-line Java application that allows multiple users to register, log in, and manage their personal notes — all stored in a MySQL database. This project uses JDBC for database connectivity, and passwords are securely hashed for security.

## Features

- User registration and login
- Add, display, edit, delete, and search notes
- Passwords stored securely using salted PBKDF2 hashing
- MySQL database integration using JDBC
- Modular structure for maintainability

## Setup Instructions

### 1. Prerequisites

- Java JDK 8 or higher
- MySQL Server installed
- MySQL JDBC driver (`mysql-connector-j-9.3.0.jar`)

### 2. Database Setup
- Run Database.sql File in MySQL Platform

### 3. Configure DB in `DBConnection.java`
Edit your DB URL, username, and password:

```java
public static final String url = "jdbc:mysql://localhost:3306/userdata";
public static final String username = "root";
public static final String password = "your_mysql_password";
```

### 4. Run the Project
1. Compile the project Java files.
   Command: `javac -cp .;mysql-connector-j-9.3.0.jar *.java`
2. Run NoteApp main class.
   Command: `java -cp .;mysql-connector-j-9.3.0.jar NoteApp`
3. Follow the on-screen menu to register or login and manage your notes.
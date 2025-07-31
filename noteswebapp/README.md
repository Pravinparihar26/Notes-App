# NoteWebApp

A secure and simple note-taking web application that allows users to register, log in, and manage their personal notes — all from a single-page interface.

## Features

- **User Registration & Login** with password hashing
- **Add, Edit, Delete, and View Notes**
- **Search notes** by key
- **Single Page Application (SPA)** experience with JavaScript
- **Session-based Authentication** using Java HttpSession
- Backend tested with **Postman**

## Tech Stack

**Frontend:**
- HTML
- CSS
- JavaScript

**Backend:**
- Java
- Spring Boot
- JDBC
- MySQL

**Tools:**
- Postman (for API testing)

## Security

- Passwords are hashed using **PBKDF2**
- Session stored using **HttpSession**
- Session invalidation on logout

## Setup Instructions

### 1. Install the following :

- Java JDK 8 or higher
- MySQL Server
- Maven (comes bundled in many IDEs like IntelliJ or Eclipse)

### 2. Clone the Repository

```bash
git clone https://github.com/Pravinparihar26/Notes-App.git
cd Notes-App/noteswebapp
```

### 3. Database Setup

1. Open MySQL client (Workbench, CLI, etc.)
2. Run the `database.sql` file

### 4. Configure DB in `DBConnection.java`
Edit your Database connection details:

```java
public static final String url = "jdbc:mysql://localhost:3306/userdata";
public static final String username = "root";
public static final String password = "your_mysql_password";
```

### 5. Run the Application (Spring Boot)

1. Open terminal in the project root directory 
2. Run the Spring Boot app using command `./mvnw spring-boot:run`
3. Open `http://localhost:8080/index.html` in a browser
4. Use the App
## Postman Collection

To test the APIs of this project, you can use the Postman collection provided below.

### Download Collection
[Download Postman Collection](https://github.com/atharvaraskar-bntsoft/UserManagementSystemWithSpringSecurity/blob/main/UserManagementSystemCollection.json)

### How to Use
1. Download the collection file.
2. Open Postman and import the collection:
   - Go to **File** > **Import**.
   - Select the downloaded `.json` file.
3. Test the APIs in Postman.

# User Management System with Spring Security

This project is a User Management System implemented using Spring Boot and Spring Security. It provides a secure, role-based system with APIs for user registration, login, and management.


## Features
- Secure user authentication with JWT tokens.
- Role-based access control (Admin and User roles).
- CRUD operations for user accounts.
- APIs for login, user management, and role-based restrictions.

## Project Tasks

### 1. Set Up the Project
- Create a Spring Boot project using Maven or Gradle.
- Add the following dependencies:
  - Spring Security
  - Spring Web
  - Spring Data JPA
  - H2 (In-memory database)

### 2. Define User and Role Models
- Create `User` and `Role` entities with the following structure:

#### User Entity
- `id` (primary key)
- `username` (unique)
- `password`
- `email`
- `roles` (many-to-many relationship)

#### Role Entity
- `id` (primary key)
- `name` (e.g., "USER", "ADMIN")

### 3. Configure Spring Security
- Use `BCryptPasswordEncoder` for password hashing.
- Implement a custom user details service (`UserDetailsService`) to load user details from the database.
- Configure Spring Security to allow role-based access to APIs.

### 4. Implement the Following APIs
## API Endpoint

#### a. User Registration API
- **Endpoint**: `POST /api/register`
- **Description**: Allow new users to register by providing a username, email, and password.
- **Access**: Public
- **Example Input**:
  ```json
  {
    "username": "john_doe",
    "password": "password123",
    "email": "john@example.com"
  }


### **b. Login API**
- **Endpoint:** `POST /api/login`
- **Description:** Authenticate users and return a JWT token.
- **Access:** Public

### **c. Get All Users API (Admin Only)**
- **Endpoint:** `GET /api/users`
- **Description:** Retrieve a list of all registered users. Only accessible to users with the "ADMIN" role.
- **Access:** Admin

### **d. Update User Details API**
- **Endpoint:** `PUT /api/users/{id}`
- **Description:** Allow users to update their details, such as email.
- **Access:** User (only their own account)

### **e. Delete User API (Admin Only)**
- **Endpoint:** `DELETE /api/users/{id}`
- **Description:** Allow admins to delete any user account by ID.
- **Access:** Admin

---

### 5. Implement JWT Token Authentication (Optional Advanced Task)
- Use Spring Security’s JWT support to secure APIs.
- Include token-based login and role validation.

---

### Expected Outcome
- A working User Management System secured with Spring Security.
- APIs should be fully functional with proper authentication and authorization.
- Demonstrate role-based restrictions with Admin and User roles.


---

## Getting Started

### Prerequisites
- Java 21
- Spring Boot
- Gradle
- A database (e.g., MySQL, PostgreSQL)

### Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/atharvaraskar-bntsoft/UserManagementSystemWithSpringSecurity.git

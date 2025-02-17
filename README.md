# Ai cust - Spring Boot with MongoDB Integration

A simple Spring Boot application demonstrating integration with MongoDB. This project includes creating a model, saving data in MongoDB, and providing RESTful APIs for CRUD operations.

---

## 🚀 Features

- **Spring Boot** for backend development.
- **MongoDB** integration for data storage.
- REST API for creating and retrieving data.
- Proper separation of concerns using Controller, Service, and Repository layers.

---

## 🔧 Technologies Used

- **Spring Boot 3.x**
- **MongoDB** (NoSQL Database)
- **Java 17**
- **Maven** for dependency management
- **RESTful APIs**

---

## 🛠️ Setup & Installation

### Prerequisites

- Java 17 or later
- MongoDB installed and running locally (or a MongoDB Atlas account)
- Maven installed on your machine

### Steps to Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/demo2.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd demo2
   ```

3. **Configure MongoDB Connection:**

   Open the `src/main/resources/application.properties` file and update the connection URI:

   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/demo2
   spring.data.mongodb.database=demo2
   ```

   If using MongoDB Atlas, replace the URI with your connection string.

4. **Build and Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application:**

   The application will start on port **8080** (unless otherwise configured).

---

## 🌐 API Endpoints

### Create a New Model

- **URL:** `/api/yourmodel/create`
- **Method:** `POST`
- **Request Body:**

  ```json
  {
    "name": "John Doe",
    "age": 30
  }
  ```

- **Success Response:**

  ```json
  {
    "id": "generated-id",
    "name": "John Doe",
    "age": 30
  }
  ```

### Additional Endpoints

You can add more endpoints for `GET`, `PUT`, and `DELETE` operations in a similar manner following RESTful conventions.

---

## 🤝 Contributing

Contributions are welcome! Please fork the repository, create a new branch for your feature or bug fix, and submit a pull request.

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 📝 Support

If you have any questions or need help, feel free to open an issue or contact the repository maintainer.

---

Happy coding! 🚀
```` ▋

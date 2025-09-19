# Inventory
=======
# Inventory Management System

A robust Spring Boot application for tracking product inventory with MySQL database integration, RESTful APIs, and comprehensive testing.

## 🚀 Features

- **Complete CRUD Operations** - Create, Read, Update, and Delete products
- **RESTful API** - Well-structured REST endpoints with standardized responses
- **MySQL Integration** - Persistent data storage with JPA/Hibernate
- **Data Validation** - Input validation using Jakarta validation annotations
- **Exception Handling** - Custom exception handling for better error management
- **Comprehensive Testing** - Unit tests covering all application layers
- **Docker Support** - Containerized deployment with Docker Compose
- **CI/CD Pipeline** - Automated testing and deployment with GitHub Actions

## 🛠️ Technology Stack

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **MySQL 8.0**
- **Maven**
- **Docker & Docker Compose**
- **JUnit 5** for testing
- **Mockito** for mocking
- **Lombok** for reducing boilerplate code

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0
- Docker (optional, for containerized deployment)

## 🚀 Getting Started

### Local Development Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd inventory-management
   ```

2. **Configure MySQL Database**
   - Create a MySQL database named `Inventory`
   - Update database credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/Inventory?createDatabaseIfNotExist=TRUE
   spring.datasource.username=root
   spring.datasource.password=new_password
   ```

3. **Build the application**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

### Docker Deployment

1. **Using Docker Compose**
   ```bash
   cd docker
   docker-compose up -d
   ```

This will start both the MySQL database and the Spring Boot application in containers.

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/v1
```

### Endpoints

#### Get All Products
```http
GET /products
```

**Response:**
```json
{
  "status": "success",
  "message": "Request was successful",
  "data": [
    {
      "id": 1,
      "name": "Product Name",
      "description": "Product Description",
      "price": 99.99,
      "quantity": 100
    }
  ]
}
```

#### Add New Product
```http
POST /product
Content-Type: application/json

{
  "name": "Product Name",
  "description": "Product Description",
  "price": 99.99,
  "quantity": 100
}
```

#### Update Product
```http
PUT /update/{id}
Content-Type: application/json

{
  "name": "Updated Product Name",
  "description": "Updated Description",
  "price": 149.99,
  "quantity": 50
}
```

#### Delete Product
```http
DELETE /{id}
```

### Response Format

All API responses follow a standardized format:

```json
{
  "status": "success|error|failed",
  "message": "Descriptive message",
  "data": "Response data (if applicable)"
}
```

## 🧪 Testing

### Run Unit Tests
```bash
mvn test
```

### Run Specific Test Suite
```bash
# Unit tests only
mvn clean -Dtest=com.stores.Inventory.Unittest.*Test test

# Using Makefile
make run_unittest
```

### Test Coverage
The application includes comprehensive unit tests for:
- Controllers (API endpoints)
- Services (Business logic)
- Models (Entity and DTO classes)
- Repository layer
- Exception handling
- API response formatting

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/stores/Inventory/
│   │   ├── controller/          # REST Controllers
│   │   ├── model/              # Entity and DTO classes
│   │   ├── repository/         # Data access layer
│   │   ├── service/            # Business logic
│   │   ├── response/           # API response wrapper
│   │   ├── exception/          # Custom exceptions
│   │   └── InventoryApplication.java
│   └── resources/
│       └── application.properties
└── test/
    ├── java/com/stores/Inventory/Unittest/  # Unit tests
    └── resources/                           # Test configuration
```

## 🔧 Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/Inventory?createDatabaseIfNotExist=TRUE
spring.datasource.username=root
spring.datasource.password=new_password

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Environment Variables
For production deployment, use environment variables:
- `DB_URL` - Database connection URL
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password

## 🚀 Deployment

### GitHub Actions CI/CD
The project includes automated CI/CD pipeline that:
- Runs unit tests on every push/PR
- Builds Docker image
- Pushes to Docker Hub
- Supports automated deployment

### Manual Deployment
1. Build the JAR file:
   ```bash
   mvn clean package -DskipTests
   ```

2. Run with Java:
   ```bash
   java -jar target/stores-inventory.jar
   ```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 API Examples

### Using cURL

**Get all products:**
```bash
curl -X GET http://localhost:8080/api/v1/products
```

**Add a new product:**
```bash
curl -X POST http://localhost:8080/api/v1/product \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop",
    "description": "High-performance laptop",
    "price": 999.99,
    "quantity": 10
  }'
```

**Update a product:**
```bash
curl -X PUT http://localhost:8080/api/v1/update/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Laptop",
    "description": "Updated description",
    "price": 1199.99,
    "quantity": 5
  }'
```

**Delete a product:**
```bash
curl -X DELETE http://localhost:8080/api/v1/1
```

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Issues**
   - Ensure MySQL is running
   - Verify database credentials
   - Check if the database exists

2. **Port Already in Use**
   - Change the port in `application.properties`:
     ```properties
     server.port=8081
     ```

3. **Maven Build Issues**
   - Ensure Java 17 is installed
   - Clear Maven cache: `mvn clean`

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For support and questions:
- Create an issue in the GitHub repository
- Contact the development team

---

**Built with ❤️ using Spring Boot**
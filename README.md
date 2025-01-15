# E-Camp Spring Boot Module 6

**Repository to practice Spring Boot knowledge.**

## Description

This project is part of Module 6 of the eCamp course, focusing on backend application development using Spring Boot. The goal is to consolidate skills in building RESTful APIs, managing dependencies with Maven, and configuring Spring applications.

## Features

- **RESTful API**: Implementation of CRUD operations endpoints.
- **Persistence**: Integration with a database using Spring Data JPA.
- **Validation**: Handling input data validation.
- **Exception Handling**: Centralized error and exception management.
- **Documentation**: Interactive API documentation with Swagger.

## Requirements

- **Java 17** or higher
- **Maven 3.6** or higher
- **Spring Boot 2.5.4** or higher
- **Database**: PostgreSQL/MySQL (configurable)

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/PakoAraya/ecamp-springboot-m6.git
   cd ecamp-springboot-m6
   ```

2. **Set up the database**:

   - Create a database in PostgreSQL or MySQL.
   - Update the credentials in `src/main/resources/application.properties`:

     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build and run the application**:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the application**:

   - API available at: `http://localhost:8080/api`
   - Swagger documentation: `http://localhost:8080/swagger-ui.html`

## Usage

- **Get all resources**:

  ```http
  GET /api/resources
  ```

- **Create a new resource**:

  ```http
  POST /api/resources
  Content-Type: application/json

  {
    "name": "New Resource",
    "description": "Resource description"
  }
  ```

- **Update an existing resource**:

  ```http
  PUT /api/resources/{id}
  Content-Type: application/json

  {
    "name": "Updated Resource",
    "description": "Updated description"
  }
  ```

- **Delete a resource**:

  ```http
  DELETE /api/resources/{id}
  ```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push your changes (`git push origin feature/new-feature`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

**Francisco Javier Araya Hernández**  
[franarayah@gmail.com](mailto:franarayah@gmail.com)  
[LinkedIn](https://www.linkedin.com/in/franarayah/)

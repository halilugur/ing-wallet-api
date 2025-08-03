# 💳 Digital Wallet API

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-green.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

A robust Spring Boot REST API service for digital wallet management that enables secure financial transactions. The system supports role-based access control for customers and employees, providing comprehensive wallet and transaction management capabilities.

---

## 📦 Features

### Core Functionality
- ✅ **Multi-Currency Wallet Management** - Support for TRY, USD, EUR
- ✅ **Secure Transactions** - Deposit and withdrawal operations with approval workflow
- ✅ **Transaction Status Tracking** - Real-time status updates (`PENDING`, `APPROVED`, `DENIED`)
- ✅ **Role-Based Access Control** - Separate permissions for customers and employees
- ✅ **JWT Authentication** - Secure token-based authentication
- ✅ **RESTful API Design** - Clean and intuitive endpoint structure

### Security Features
- 🔐 Spring Security integration
- 🔑 JWT token authentication
- 👥 Role-based authorization (CUSTOMER/EMPLOYEE)
- 🛡️ Input validation and error handling

### Technical Features
- 📊 H2 database with file persistence
- 📚 OpenAPI/Swagger documentation
- 🧪 Comprehensive test coverage
- 🚀 Production-ready configuration

---

## 🏗️ Tech Stack

- Java 21
- Spring Boot 3.5.4
- Spring Security
- H2 Database
- Maven
- JPA (Hibernate)
- JWT (JSON Web Token)
- Springdoc OpenAPI 2.8.9

---

## 🚀 Quick Start

### Prerequisites
- Java 21 or higher
- Maven 3.6+

### Running the Application

1. **Clone the repository**
   ```bash
   git clone https://github.com/halilugur/ing-wallet-api.git
   cd wallet
   ```

2. **Build and run**
   ```bash
   ./mvnw clean spring-boot:run
   ```

3. **Access the API**
   - **Swagger UI**: `http://localhost:8080/swagger-ui.html`
   - **API Docs**: `http://localhost:8080/v3/api-docs`
   - **H2 Console**: `http://localhost:8080/h2-console`

### Alternative Build Options

```bash
# Build JAR file
./mvnw clean package

# Run JAR file
java -jar target/wallet-1.0.0.jar

# Run tests
./mvnw test
```

---

## 🔐 Authentication & Authorization

The API uses **JWT (JSON Web Token)** authentication. Users must sign in to receive a JWT token for accessing protected endpoints.

### Pre-loaded Test Users

| TC Number     | Password | Role     | Permissions |
|---------------|----------|----------|-------------|
| `10000000001` | `pass`   | CUSTOMER | Own wallets and transactions only |
| `10000000002` | `pass`   | EMPLOYEE | All customer wallets and transactions |

### Authentication Flow

1. **Sign In**: Call `/api/v1/auth/signin` with credentials
2. **Receive JWT**: Get JWT token in response
3. **Use Token**: Include JWT in `Authorization: Bearer <token>` header for protected endpoints

### JWT Token Usage
```bash
# After signing in, use the JWT token in requests
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 🧪 API Endpoints

### Authentication Endpoints
> **Public Access** - No authentication required

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/auth/signup` | Register new user |
| `POST` | `/api/v1/auth/signin` | User login |

### Customer Endpoints
> **Base Path**: `/api/v1/my/`  
> **Authentication**: JWT Token (CUSTOMER role)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/my/wallets` | Create a new wallet |
| `GET` | `/api/v1/my/wallets` | List own wallets |
| `POST` | `/api/v1/my/transactions/deposit` | Deposit money |
| `POST` | `/api/v1/my/transactions/withdraw` | Withdraw money |
| `GET` | `/api/v1/my/transactions/wallet/{walletId}` | Get wallet transactions |

### Employee Endpoints
> **Base Path**: `/api/v1/`  
> **Authentication**: JWT Token (EMPLOYEE role)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/wallets/{tckn}` | Create wallet for customer |
| `GET` | `/api/v1/wallets/{tckn}` | List customer wallets |
| `GET` | `/api/v1/transactions/{tckn}/wallet/{walletId}` | Get customer wallet transactions |
| `POST` | `/api/v1/transactions/{tckn}/deposit` | Process deposit for customer |
| `POST` | `/api/v1/transactions/{tckn}/withdraw` | Process withdrawal for customer |
| `POST` | `/api/v1/transactions/{transactionId}` | Approve/deny transaction by ID |

### Sample Requests

#### Sign In (Get JWT Token)
```bash
# Customer Login
curl -X POST http://localhost:8080/api/v1/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "tckn": "10000000001",
    "password": "pass"
  }'

# Response: {"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."}
```

```bash
# Employee Login  
curl -X POST http://localhost:8080/api/v1/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "tckn": "10000000002", 
    "password": "pass"
  }'
```

#### Register New User
```bash
curl -X POST http://localhost:8080/api/v1/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "tckn": "12345678901",
    "firstName": "John",
    "lastName": "Doe", 
    "password": "password123"
  }'
```

#### Create Wallet (Customer)
```bash
curl -X POST http://localhost:8080/api/v1/my/wallets \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{"currency": "USD"}'
```

#### Deposit Money (Customer)
```bash
curl -X POST http://localhost:8080/api/v1/my/transactions/deposit \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{"walletId": 1, "amount": 500.00}'
```

#### Create Wallet for Customer (Employee)
```bash
curl -X POST http://localhost:8080/api/v1/wallets/10000000001 \
  -H "Authorization: Bearer YOUR_EMPLOYEE_JWT_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{"currency": "EUR"}'
```

#### Approve Transaction (Employee)
```bash
curl -X POST http://localhost:8080/api/v1/transactions/123 \
  -H "Authorization: Bearer YOUR_EMPLOYEE_JWT_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{"status": "APPROVED"}'
```

---

## 💼 Business Logic

### Wallet Management
- Each wallet maintains two balance types:
  - **Balance**: Total amount in the wallet
  - **Usable Balance**: Available amount for transactions
- Supported currencies: `TRY`, `USD`, `EUR`
- Multiple wallets per customer allowed

### Transaction Workflow
1. **Transaction Creation**: Deposit/withdrawal requests are created
2. **Automatic Approval**: Transactions ≤ 1000 units are auto-approved
3. **Manual Review**: Transactions > 1000 units require manual approval
4. **Status Updates**: 
   - `PENDING`: Awaiting approval
   - `APPROVED`: Transaction completed successfully
   - `DENIED`: Transaction rejected

### Balance Updates
- **Approved Deposits**: Increase both balance and usable balance
- **Approved Withdrawals**: Decrease both balance and usable balance
- **Pending Transactions**: Reserve funds in usable balance

---

## 🛠️ Development

### Project Structure
```
src/
├── main/
│   ├── java/uk/ugurtech/wallet/
│   │   ├── config/          # Security & OpenAPI configuration
│   │   ├── exception/       # Global exception handling
│   │   ├── mapper/          # DTO mappers
│   │   ├── model/           # Entities, DTOs, requests/responses
│   │   ├── repository/      # JPA repositories
│   │   ├── rest/            # REST controllers
│   │   ├── service/         # Business logic
│   │   └── util/            # Utility classes
│   └── resources/
│       ├── application.yaml # Application configuration
│       └── data.sql         # Initial data
└── test/                    # Test classes
```

### Configuration
Key configuration files:
- `application.yaml`: Main application settings
- `SecurityConfig.java`: Security and authentication setup
- `OpenAPIConfig.java`: Swagger documentation configuration

### Database
- **Type**: H2 Database (file-based)
- **Console**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:file:./wallet-api`
- **Username**: `wallet`
- **Password**: `wallet`

## 🧪 Testing

```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=WalletServiceTest

# Run tests with coverage
./mvnw jacoco:prepare-agent test jacoco:report
```

## 📞 Support & Contact

- **Repository**: [ing-wallet-api](https://github.com/halilugur/ing-wallet-api)
- **Issues**: [Report bugs or request features](https://github.com/halilugur/ing-wallet-api/issues)
- **Developer**: Halil UGUR

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📝 License

Apache 2.0 License. Free to use and modify.

---

## 📅 Last Updated

August 4, 2025

---

README.md file has been written by AI. But has been coded by Halil UGUR.

# Customer Account Management API

## Overview
This Spring Boot application allows for managing customer accounts. It provides endpoints to create, retrieve, update, and delete customer accounts and associated account details.

## API Endpoints

### 1. Create Account
- **Method**: `POST`
- **URL**: `/api/customers`
- **Description**: Creates a new customer account.

### 2. Fetch Account Details
- **Method**: `GET`
- **URL**: `/api/accounts/details`
- **Description**: Retrieves account details for a customer using their email.
  
### 3. Check Account Number
- **Method**: `GET`
- **URL**: `/api/accounts/number`
- **Description**: Returns the account number for a customer using their email.

### 4. Update Account Details
- **Method**: `PUT`
- **URL**: `/api/accounts/update`
- **Description**: Updates the account details of a customer.

### 5. Delete Account
- **Method**: `DELETE`
- **URL**: `/api/accounts/delete`
- **Description**: Deletes an account for a customer using their email.

## Technologies Used
- **Spring Boot**: For building the RESTful API.
- **Hibernate**: For ORM (Object-Relational Mapping).
- **JPA**: For data persistence.
- **Validation Annotations**: For input validation.
- **ModelMapper**: For mapping between entity and DTO (Data Transfer Object) classes, simplifying object conversions.

## Setup
1. **Clone the repository**:
   ```bash
   git clone <repository-url>

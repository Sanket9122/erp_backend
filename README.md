# ERP System Documentation

## Overview
This is an Enterprise Resource Planning (ERP) system built with Spring Boot that manages projects, users, and accessories.

## Architecture
- **Backend**: Spring Boot with Spring Security
- **Database**: MySQL with JPA/Hibernate
- **Authentication**: Basic Auth (can be configured for JWT later)

## Key Features
1. User Management with Role-based Access
2. Project Management with Team Assignment
3. Accessory/Inventory Management
4. RESTful APIs for all operations

## Database Schema
- **users**: Stores user information and roles
- **projects**: Project details and status
- **accessories**: Inventory items with assignment tracking

## API Endpoints

### Public APIs (No Authentication Required)
- `GET /public/projects/{projectId}/member-count` - Get member count for a project

### User Project APIs (Authentication Required)
- `GET /api/user-projects/my-project` - Get current user's project
- `GET /api/user-projects/{projectId}/member-count` - Get member count for specific project

### Project Management APIs
- `GET /api/projects` - Get all projects
- `GET /api/projects/{id}` - Get project by ID
- `PUT /api/projects/{projectId}/assign-user/{userId}` - Assign user to project
- `PUT /api/projects/{projectId}/assign-accessory/{serialNumber}` - Assign accessory to project

### User Management APIs
- `POST /api/users` - Create new user
- `GET /api/users/available` - Get available users
- `GET /api/users/{id}` - Get user details

### Accessory Management APIs
- `POST /api/accessories` - Add new accessory
- `GET /api/accessories/in-stock` - Get available accessories

## Test Data
The system includes test data with:
- 5 users (Admin, Manager, 3 Developers)
- 3 projects with different statuses
- 5 accessories with various assignments

## Security Configuration
- Currently configured for development with Basic Auth
- Role-based access control for different endpoints
- CSRF disabled for API testing

## Getting Started
1. Configure MySQL database in `application.properties`
2. Run the Spring Boot application
3. Test APIs using Postman or browser

## Default Test Users
- Email: `admin@erp.com`, Password: `admin123`, Role: `ADMIN`
- Email: `manager@erp.com`, Password: `manager123`, Role: `MANAGER`
- Email: `john@erp.com`, Password: `john123`, Role: `USER`

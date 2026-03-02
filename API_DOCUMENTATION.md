# ERP System API Documentation

## Overview
This document provides detailed information about all available APIs in the ERP system.

## Base URL
```
http://localhost:8080
```

## Authentication
Currently configured for development with no authentication required.
For production, Basic Authentication will be enabled.

## Response Format
All APIs return JSON responses with appropriate HTTP status codes.

---

## Public APIs (No Authentication Required)

### Get Project Member Count
**Endpoint:** `GET /public/projects/{projectId}/member-count`

**Description:** Get the number of team members in a specific project

**Path Parameters:**
- `projectId` (Long): ID of the project

**Response:**
```json
{
  "projectId": 1,
  "memberCount": 3
}
```

**Example Request:**
```bash
GET /public/projects/1/member-count
```

---

## User Project APIs

### Get Current User's Project
**Endpoint:** `GET /api/user-projects/my-project`

**Description:** Get the project assigned to the currently authenticated user

**Authentication Required:** Yes

**Response:**
```json
{
  "id": 1,
  "name": "ERP System Development",
  "status": "IN_PROGRESS",
  "teamMemberNames": ["John Developer", "Sarah Developer"],
  "teamsize": 2,
  "accessorySerialNumbers": ["DLX-001", "MON-002"]
}
```

### Get Project Member Count
**Endpoint:** `GET /api/user-projects/{projectId}/member-count`

**Description:** Get the number of team members in a specific project

**Path Parameters:**
- `projectId` (Long): ID of the project

**Authentication Required:** Yes

**Response:**
```json
{
  "projectId": 1,
  "memberCount": 2
}
```

---

## Project Management APIs

### Get All Projects
**Endpoint:** `GET /api/projects`

**Description:** Get list of all projects in the system

**Authentication Required:** Yes (ADMIN or MANAGER role)

**Response:**
```json
[
  {
    "id": 1,
    "name": "ERP System Development",
    "status": "IN_PROGRESS",
    "teamMemberNames": ["John Developer", "Sarah Developer"],
    "teamsize": 2,
    "accessorySerialNumbers": ["DLX-001", "MON-002"]
  }
]
```

### Get Project by ID
**Endpoint:** `GET /api/projects/{id}`

**Description:** Get detailed information about a specific project

**Path Parameters:**
- `id` (Long): ID of the project

**Authentication Required:** Yes (ADMIN or MANAGER role)

### Assign User to Project
**Endpoint:** `PUT /api/projects/{projectId}/assign-user/{userId}`

**Description:** Assign a user to a project (enforces 1 user = 1 project)

**Path Parameters:**
- `projectId` (Long): ID of the project
- `userId` (Long): ID of the user

**Authentication Required:** Yes (ADMIN or MANAGER role)

**Response:**
```json
"User assigned successfully to project."
```

### Assign Accessory to Project
**Endpoint:** `PUT /api/projects/{projectId}/assign-accessory/{serialNumber}`

**Description:** Assign an accessory to a project by serial number

**Path Parameters:**
- `projectId` (Long): ID of the project
- `serialNumber` (String): Serial number of the accessory

**Authentication Required:** Yes (ADMIN or MANAGER role)

**Response:**
```json
"Accessory assigned successfully to project."
```

---

## User Management APIs

### Create User
**Endpoint:** `POST /api/users`

**Description:** Create a new user in the system

**Authentication Required:** Yes

**Request Body:**
```json
{
  "name": "New User",
  "email": "newuser@erp.com",
  "password": "password123",
  "role": "USER"
}
```

**Response:**
```json
{
  "userId": 6,
  "name": "New User",
  "email": "newuser@erp.com",
  "password": "password123",
  "role": "USER",
  "currentProject": null
}
```

### Get Available Users
**Endpoint:** `GET /api/users/available`

**Description:** Get list of users who are not assigned to any project

**Authentication Required:** Yes

**Response:**
```json
[
  {
    "userId": 3,
    "name": "Mike Developer",
    "email": "mike@erp.com",
    "role": "USER",
    "currentProject": null
  }
]
```

### Get User by ID
**Endpoint:** `GET /api/users/{id}`

**Description:** Get detailed information about a specific user

**Path Parameters:**
- `id` (Long): ID of the user

**Authentication Required:** Yes

**Response:**
```json
{
  "userId": 1,
  "name": "John Developer",
  "email": "john@erp.com",
  "role": "USER"
}
```

---

## Accessory Management APIs

### Add Accessory
**Endpoint:** `POST /api/accessories`

**Description:** Add a new accessory to the inventory

**Authentication Required:** Yes

**Request Body:**
```json
{
  "name": "New Laptop",
  "serialNumber": "LAP-006",
  "isAvailable": true
}
```

**Response:**
```json
{
  "accesoryId": 6,
  "name": "New Laptop",
  "serialNumber": "LAP-006",
  "isAvailable": true,
  "assignedProject": null
}
```

### Get Available Accessories
**Endpoint:** `GET /api/accessories/in-stock`

**Description:** Get list of accessories that are currently available

**Authentication Required:** Yes

**Response:**
```json
[
  {
    "accesoryId": 3,
    "name": "Keyboard Mechanical",
    "serialNumber": "KEY-003",
    "isAvailable": true,
    "assignedProject": null
  }
]
```

---

## Error Responses

### 404 Not Found
```json
{
  "timestamp": "2024-01-01T12:00:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found",
  "path": "/api/projects/999"
}
```

### 401 Unauthorized
```json
{
  "timestamp": "2024-01-01T12:00:00.000+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Authentication required",
  "path": "/api/projects"
}
```

### 403 Forbidden
```json
{
  "timestamp": "2024-01-01T12:00:00.000+00:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access denied",
  "path": "/api/projects"
}
```

---

## Test Data

### Default Users
- **Admin**: admin@erp.com / admin123 (ADMIN role)
- **Manager**: manager@erp.com / manager123 (MANAGER role)
- **User**: john@erp.com / john123 (USER role)

### Sample Projects
- **Project 1**: ERP System Development (2 members)
- **Project 2**: Mobile App Project (1 member)
- **Project 3**: Website Redesign (0 members)

---

## Usage Examples

### Using curl
```bash
# Get public member count
curl -X GET http://localhost:8080/public/projects/1/member-count

# Get all projects (with auth)
curl -X GET -u admin@erp.com:admin123 http://localhost:8080/api/projects

# Create new user
curl -X POST -u admin@erp.com:admin123 \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","email":"test@erp.com","password":"test123","role":"USER"}' \
  http://localhost:8080/api/users
```

### Using JavaScript/Fetch
```javascript
// Get member count
const response = await fetch('/public/projects/1/member-count');
const data = await response.json();

// Create user with authentication
const createResponse = await fetch('/api/users', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Basic ' + btoa('admin@erp.com:admin123')
  },
  body: JSON.stringify({
    name: 'New User',
    email: 'new@erp.com',
    password: 'password123',
    role: 'USER'
  })
});
```

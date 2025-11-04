# Admin Controller API Documentation

## Overview
The Admin Controller manages administrator users in the CareerExpo application. It provides endpoints for CRUD operations, authentication, and admin management.

## Base URL
\`\`\`
http://localhost:8080/api/admin
\`\`\`

## Endpoints Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/add` | Create a new admin |
| GET | `/all` | Retrieve all admins |
| GET | `/getById/{id}` | Get admin by ID |
| GET | `/getByEmail/{email}` | Get admin by email |
| PUT | `/update/{id}` | Update admin details |
| DELETE | `/delete/{id}` | Delete an admin |
| POST | `/login` | Admin login authentication |

---

## Detailed Endpoints

### 1. Create New Admin
**Endpoint:** `POST /api/admin/add`

**Description:** Creates a new administrator account.

**Request Body:**
\`\`\`json
{
  "name": "Ahmed Ali",
  "email": "ahmed.ali@careerexpo.com",
  "password": "SecurePassword123!",
  "phone": "+212612345678"
}
\`\`\`

**Success Response (201 Created):**
\`\`\`json
{
  "id": 1,
  "name": "Ahmed Ali",
  "email": "ahmed.ali@careerexpo.com",
  "password": "SecurePassword123!",
  "phone": "+212612345678",
  "createdAt": "2025-01-15T10:30:00"
}
\`\`\`

**Error Response (400 Bad Request):**
\`\`\`json
{
  "timestamp": "2025-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email already exists"
}
\`\`\`

**Postman Setup:**
- Method: `POST`
- URL: `http://localhost:8080/api/admin/add`
- Headers: `Content-Type: application/json`
- Body: Raw JSON (as shown above)

---

### 2. Get All Admins
**Endpoint:** `GET /api/admin/all`

**Description:** Retrieves a list of all administrators.

**Success Response (200 OK):**
\`\`\`json
[
  {
    "id": 1,
    "name": "Ahmed Ali",
    "email": "ahmed.ali@careerexpo.com",
    "password": "SecurePassword123!",
    "phone": "+212612345678",
    "createdAt": "2025-01-15T10:30:00"
  },
  {
    "id": 2,
    "name": "Fatima Hassan",
    "email": "fatima.hassan@careerexpo.com",
    "password": "AnotherPass456!",
    "phone": "+212698765432",
    "createdAt": "2025-01-14T14:20:00"
  }
]
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/admin/all`
- Headers: None required

---

### 3. Get Admin by ID
**Endpoint:** `GET /api/admin/getById/{id}`

**Description:** Retrieves a specific admin by their ID.

**URL Parameter:**
- `id` (required, integer): The admin's unique identifier

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "name": "Ahmed Ali",
  "email": "ahmed.ali@careerexpo.com",
  "password": "SecurePassword123!",
  "phone": "+212612345678",
  "createdAt": "2025-01-15T10:30:00"
}
\`\`\`

**Error Response (404 Not Found):**
\`\`\`json
{
  "timestamp": "2025-01-15T10:35:00",
  "status": 404,
  "error": "Not Found",
  "message": "Admin not found with id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/admin/getById/1`
- Headers: None required

---

### 4. Get Admin by Email
**Endpoint:** `GET /api/admin/getByEmail/{email}`

**Description:** Retrieves a specific admin by their email address.

**URL Parameter:**
- `email` (required, string): The admin's email address

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "name": "Ahmed Ali",
  "email": "ahmed.ali@careerexpo.com",
  "password": "SecurePassword123!",
  "phone": "+212612345678",
  "createdAt": "2025-01-15T10:30:00"
}
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/admin/getByEmail/ahmed.ali@careerexpo.com`
- Headers: None required

---

### 5. Update Admin
**Endpoint:** `PUT /api/admin/update/{id}`

**Description:** Updates admin information.

**URL Parameter:**
- `id` (required, integer): The admin's unique identifier

**Request Body:**
\`\`\`json
{
  "name": "Ahmed Ali Updated",
  "email": "ahmed.updated@careerexpo.com",
  "password": "NewPassword789!",
  "phone": "+212612999999"
}
\`\`\`

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "name": "Ahmed Ali Updated",
  "email": "ahmed.updated@careerexpo.com",
  "password": "NewPassword789!",
  "phone": "+212612999999",
  "createdAt": "2025-01-15T10:30:00"
}
\`\`\`

**Error Response (404 Not Found):**
\`\`\`json
{
  "timestamp": "2025-01-15T10:40:00",
  "status": 404,
  "error": "Not Found",
  "message": "Admin not found with id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `PUT`
- URL: `http://localhost:8080/api/admin/update/1`
- Headers: `Content-Type: application/json`
- Body: Raw JSON (as shown above)

---

### 6. Delete Admin
**Endpoint:** `DELETE /api/admin/delete/{id}`

**Description:** Deletes an administrator account.

**URL Parameter:**
- `id` (required, integer): The admin's unique identifier

**Success Response (204 No Content):**
\`\`\`
(Empty response body)
\`\`\`

**Error Response (404 Not Found):**
\`\`\`json
{
  "timestamp": "2025-01-15T10:45:00",
  "status": 404,
  "error": "Not Found",
  "message": "Admin not found with id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `DELETE`
- URL: `http://localhost:8080/api/admin/delete/1`
- Headers: None required

---

### 7. Admin Login
**Endpoint:** `POST /api/admin/login`

**Description:** Authenticates an admin and returns their profile if credentials are valid.

**Request Body:**
\`\`\`json
{
  "email": "ahmed.ali@careerexpo.com",
  "password": "SecurePassword123!"
}
\`\`\`

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "name": "Ahmed Ali",
  "email": "ahmed.ali@careerexpo.com",
  "password": "SecurePassword123!",
  "phone": "+212612345678",
  "createdAt": "2025-01-15T10:30:00"
}
\`\`\`

**Error Response (400 Bad Request - Invalid Credentials):**
\`\`\`json
{
  "timestamp": "2025-01-15T10:50:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid email or password"
}
\`\`\`

**Postman Setup:**
- Method: `POST`
- URL: `http://localhost:8080/api/admin/login`
- Headers: `Content-Type: application/json`
- Body: Raw JSON (as shown above)

---

## Testing in Postman

### Import Collection
1. Open Postman
2. Click "Import" → "Paste Raw Text"
3. Paste the endpoints URLs

### Environment Setup
Set a Postman environment variable for base URL:
\`\`\`
{{base_url}} = http://localhost:8080
\`\`\`

Then replace URLs with: `{{base_url}}/api/admin/...`

### Testing Workflow
1. **Create Admin** → Copy ID from response
2. **Get All Admins** → Verify creation
3. **Get by ID** → Use ID from step 1
4. **Update Admin** → Use ID from step 1
5. **Login** → Test authentication
6. **Delete Admin** → Use ID from step 1

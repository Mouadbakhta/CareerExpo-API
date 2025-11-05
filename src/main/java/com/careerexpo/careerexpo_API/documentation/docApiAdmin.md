# Admin file

This file contains requests related to admin management in the Career Expo API.

## Requests

### selectAdminID
- **Method:** GET
- **URL:** http://localhost:8080/api/admins/3
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### CreateAdmin
- **Method:** POST
- **URL:** http://localhost:8080/api/admins
- **Headers:**
  - Content-Type: application/json
- **Body:**
```json
{
  "email": "admin@example.com",
  "password": "securePassword123",
  "role": "ADMIN"
}
- **Example Response:** 
{
    "id": 5,
    "email": "admin@example.com",
    "role": "ADMIN",
    "competitions": []
}

### selectAdminEmail
- **Method:** GET
- **URL:** http://localhost:8080/api/admins/email/mouadbourquouquou@gmail.com
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### selectAllAdmins
- **Method:** GET
- **URL:** http://localhost:8080/api/admins
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### updateAdminID
- **Method:** PUT
- **URL:** http://localhost:8080/api/admins/3
- **Headers:**
  - Content-Type: application/json
- **Body:**
```json
{
  "email": "mouadbourquouquou@gmail.com",
  "password": "Mouado12",
  "role": "ADMIN"
}
```
- **Example Response:** No saved example yet

### deleteAdminID
- **Method:** DELETE
- **URL:** http://localhost:8080/api/admins/4
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

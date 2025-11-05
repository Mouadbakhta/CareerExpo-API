# Competition file

This file contains requests related to competition management in the Career Expo API.

## Requests

### createCompitetion
- **Method:** POST
- **URL:** http://localhost:8080/api/competitions
- **Headers:** None
- **Body:**
```json
{
  "description": "Annual Career Expo 2024 - Connecting students with top employers",
  "annee": "2024-03-15",
  "admin": {
    "id": 3
  }
}
```
- **Example Response:** No saved example yet

### selectCompID
- **Method:** GET
- **URL:** http://localhost:8080/api/competitions/3
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### selectAllComp
- **Method:** GET
- **URL:** http://localhost:8080/api/competitions
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### selectCompEtudiants
- **Method:** GET
- **URL:** http://localhost:8080/api/competitions/2/with-etudiants
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### selectCompYear
- **Method:** GET
- **URL:** http://localhost:8080/api/competitions/annee/2025
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### selectCompAdminID
- **Method:** GET
- **URL:** http://localhost:8080/api/competitions/admin/3
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### selectCompAdminIDdetails
- **Method:** GET
- **URL:** http://localhost:8080/api/competitions/admin/3/with-details
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### selectCompAdminEmail
- **Method:** GET
- **URL:** http://localhost:8080/api/competitions/admin-email/mouadbourquouquou@gmail.com
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### selecCompBetweenData
- **Method:** GET
- **URL:** http://localhost:8080/api/competitions/between?startDate=2025-01-01&endDate=2025-12-31
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

### updateComp
- **Method:** PUT
- **URL:** http://localhost:8080/api/competitions/3
- **Headers:** None
- **Body:**
```json
{
  "id": 3,
  "description": "Annual Career Expo 2024 - Connecting students with top employers",
  "annee": "2025-11-16",
  "admin": {
    "id": 3,
    "email": "mouadbourquouquou@gmail.com"
  },
  "etudiants": []
}
```
- **Example Response:** No saved example yet

### deleteComp
- **Method:** DELETE
- **URL:** http://localhost:8080/api/competitions/4
- **Headers:** None
- **Body:** None
- **Example Response:** No saved example yet

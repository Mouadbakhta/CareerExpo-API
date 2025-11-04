# Competition Controller API Documentation

## Overview
The Competition Controller manages competitions/events in the CareerExpo application. It handles competition CRUD operations, filtering, and retrieval of associated students and admins.

## Base URL
\`\`\`
http://localhost:8080/api/competition
\`\`\`

## Endpoints Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/add` | Create a new competition |
| GET | `/all` | Retrieve all competitions |
| GET | `/getById/{id}` | Get competition by ID |
| GET | `/getById/{id}-details` | Get competition with etudiants |
| GET | `/getByYear/{year}` | Get competitions by year |
| GET | `/getByAdmin/{adminId}` | Get competitions by admin ID |
| GET | `/getByDateRange` | Get competitions by date range |
| PUT | `/update/{id}` | Update competition |
| DELETE | `/delete/{id}` | Delete competition |

---

## Detailed Endpoints

### 1. Create New Competition
**Endpoint:** `POST /api/competition/add`

**Description:** Creates a new competition/event.

**Request Body:**
\`\`\`json
{
  "name": "Tech Career Expo 2025",
  "year": 2025,
  "date": "2025-03-15",
  "location": "Casablanca Convention Center",
  "description": "Annual technology career fair for students",
  "adminId": 1
}
\`\`\`

**Success Response (201 Created):**
\`\`\`json
{
  "id": 1,
  "name": "Tech Career Expo 2025",
  "year": 2025,
  "date": "2025-03-15",
  "location": "Casablanca Convention Center",
  "description": "Annual technology career fair for students",
  "admin": {
    "id": 1,
    "name": "Ahmed Ali",
    "email": "ahmed.ali@careerexpo.com"
  },
  "createdAt": "2025-01-15T11:00:00"
}
\`\`\`

**Error Response (400 Bad Request):**
\`\`\`json
{
  "timestamp": "2025-01-15T11:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Admin not found with id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `POST`
- URL: `http://localhost:8080/api/competition/add`
- Headers: `Content-Type: application/json`
- Body: Raw JSON (as shown above)

---

### 2. Get All Competitions
**Endpoint:** `GET /api/competition/all`

**Description:** Retrieves all competitions in the system.

**Success Response (200 OK):**
\`\`\`json
[
  {
    "id": 1,
    "name": "Tech Career Expo 2025",
    "year": 2025,
    "date": "2025-03-15",
    "location": "Casablanca Convention Center",
    "description": "Annual technology career fair for students",
    "admin": {
      "id": 1,
      "name": "Ahmed Ali",
      "email": "ahmed.ali@careerexpo.com"
    },
    "createdAt": "2025-01-15T11:00:00"
  },
  {
    "id": 2,
    "name": "Finance Career Expo 2025",
    "year": 2025,
    "date": "2025-04-20",
    "location": "Rabat Business Hub",
    "description": "Finance and banking career opportunities",
    "admin": {
      "id": 2,
      "name": "Fatima Hassan",
      "email": "fatima.hassan@careerexpo.com"
    },
    "createdAt": "2025-01-14T14:20:00"
  }
]
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/competition/all`
- Headers: None required

---

### 3. Get Competition by ID
**Endpoint:** `GET /api/competition/getById/{id}`

**Description:** Retrieves a specific competition without related etudiants data.

**URL Parameter:**
- `id` (required, integer): The competition's unique identifier

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "name": "Tech Career Expo 2025",
  "year": 2025,
  "date": "2025-03-15",
  "location": "Casablanca Convention Center",
  "description": "Annual technology career fair for students",
  "admin": {
    "id": 1,
    "name": "Ahmed Ali",
    "email": "ahmed.ali@careerexpo.com"
  },
  "createdAt": "2025-01-15T11:00:00"
}
\`\`\`

**Error Response (404 Not Found):**
\`\`\`json
{
  "timestamp": "2025-01-15T11:05:00",
  "status": 404,
  "error": "Not Found",
  "message": "Competition not found with id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/competition/getById/1`
- Headers: None required

---

### 4. Get Competition with Etudiants (Details)
**Endpoint:** `GET /api/competition/getById/{id}-details`

**Description:** Retrieves competition with all associated students (etudiants).

**URL Parameter:**
- `id` (required, integer): The competition's unique identifier

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "name": "Tech Career Expo 2025",
  "year": 2025,
  "date": "2025-03-15",
  "location": "Casablanca Convention Center",
  "description": "Annual technology career fair for students",
  "admin": {
    "id": 1,
    "name": "Ahmed Ali",
    "email": "ahmed.ali@careerexpo.com"
  },
  "etudiants": [
    {
      "id": 1,
      "firstName": "Hassan",
      "lastName": "Mohamed",
      "email": "hassan.mohamed@student.com",
      "establishment": "Faculty of Science, Fez",
      "cvPath": "uploads/cv/hassan_cv.pdf",
      "status": "ACCEPTED",
      "createdAt": "2025-01-10T09:30:00"
    },
    {
      "id": 2,
      "firstName": "Aisha",
      "lastName": "Ahmed",
      "email": "aisha.ahmed@student.com",
      "establishment": "Engineering School, Casablanca",
      "cvPath": "uploads/cv/aisha_cv.pdf",
      "status": "PENDING",
      "createdAt": "2025-01-12T13:45:00"
    }
  ],
  "createdAt": "2025-01-15T11:00:00"
}
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/competition/getById/1-details`
- Headers: None required

---

### 5. Get Competitions by Year
**Endpoint:** `GET /api/competition/getByYear/{year}`

**Description:** Retrieves all competitions for a specific year.

**URL Parameter:**
- `year` (required, integer): The year to filter by (e.g., 2025)

**Success Response (200 OK):**
\`\`\`json
[
  {
    "id": 1,
    "name": "Tech Career Expo 2025",
    "year": 2025,
    "date": "2025-03-15",
    "location": "Casablanca Convention Center",
    "description": "Annual technology career fair for students",
    "admin": {
      "id": 1,
      "name": "Ahmed Ali"
    },
    "createdAt": "2025-01-15T11:00:00"
  },
  {
    "id": 2,
    "name": "Finance Career Expo 2025",
    "year": 2025,
    "date": "2025-04-20",
    "location": "Rabat Business Hub",
    "description": "Finance and banking career opportunities",
    "admin": {
      "id": 2,
      "name": "Fatima Hassan"
    },
    "createdAt": "2025-01-14T14:20:00"
  }
]
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/competition/getByYear/2025`
- Headers: None required

---

### 6. Get Competitions by Admin
**Endpoint:** `GET /api/competition/getByAdmin/{adminId}`

**Description:** Retrieves all competitions managed by a specific admin.

**URL Parameter:**
- `adminId` (required, integer): The admin's unique identifier

**Success Response (200 OK):**
\`\`\`json
[
  {
    "id": 1,
    "name": "Tech Career Expo 2025",
    "year": 2025,
    "date": "2025-03-15",
    "location": "Casablanca Convention Center",
    "description": "Annual technology career fair for students",
    "admin": {
      "id": 1,
      "name": "Ahmed Ali",
      "email": "ahmed.ali@careerexpo.com"
    },
    "createdAt": "2025-01-15T11:00:00"
  }
]
\`\`\`

**Error Response (404 Not Found):**
\`\`\`json
{
  "timestamp": "2025-01-15T11:10:00",
  "status": 404,
  "error": "Not Found",
  "message": "No competitions found for admin id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/competition/getByAdmin/1`
- Headers: None required

---

### 7. Get Competitions by Date Range
**Endpoint:** `GET /api/competition/getByDateRange`

**Description:** Retrieves competitions within a specified date range.

**Query Parameters:**
- `startDate` (required, format: YYYY-MM-DD): Start date
- `endDate` (required, format: YYYY-MM-DD): End date

**Success Response (200 OK):**
\`\`\`json
[
  {
    "id": 1,
    "name": "Tech Career Expo 2025",
    "year": 2025,
    "date": "2025-03-15",
    "location": "Casablanca Convention Center",
    "description": "Annual technology career fair for students",
    "admin": {
      "id": 1,
      "name": "Ahmed Ali"
    },
    "createdAt": "2025-01-15T11:00:00"
  }
]
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/competition/getByDateRange?startDate=2025-01-01&endDate=2025-06-30`
- Headers: None required

---

### 8. Update Competition
**Endpoint:** `PUT /api/competition/update/{id}`

**Description:** Updates competition information.

**URL Parameter:**
- `id` (required, integer): The competition's unique identifier

**Request Body:**
\`\`\`json
{
  "name": "Tech Career Expo 2025 - Updated",
  "year": 2025,
  "date": "2025-03-20",
  "location": "Marrakech Convention Hall",
  "description": "Updated technology career fair",
  "adminId": 1
}
\`\`\`

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "name": "Tech Career Expo 2025 - Updated",
  "year": 2025,
  "date": "2025-03-20",
  "location": "Marrakech Convention Hall",
  "description": "Updated technology career fair",
  "admin": {
    "id": 1,
    "name": "Ahmed Ali"
  },
  "createdAt": "2025-01-15T11:00:00"
}
\`\`\`

**Postman Setup:**
- Method: `PUT`
- URL: `http://localhost:8080/api/competition/update/1`
- Headers: `Content-Type: application/json`
- Body: Raw JSON (as shown above)

---

### 9. Delete Competition
**Endpoint:** `DELETE /api/competition/delete/{id}`

**Description:** Deletes a competition and all associated data.

**URL Parameter:**
- `id` (required, integer): The competition's unique identifier

**Success Response (204 No Content):**
\`\`\`
(Empty response body)
\`\`\`

**Error Response (404 Not Found):**
\`\`\`json
{
  "timestamp": "2025-01-15T11:15:00",
  "status": 404,
  "error": "Not Found",
  "message": "Competition not found with id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `DELETE`
- URL: `http://localhost:8080/api/competition/delete/1`
- Headers: None required

---

## Testing Sequence in Postman

1. **Create Admin** (using Admin Controller) → Copy Admin ID
2. **Create Competition** → Use Admin ID from step 1
3. **Get All Competitions** → Verify creation
4. **Get Competition by ID** → Use Competition ID
5. **Get Competition by Year** → Filter by 2025
6. **Get Competitions by Admin** → Use Admin ID
7. **Update Competition** → Use Competition ID
8. **Get Competition Details** → Use Competition ID (with -details)
9. **Delete Competition** → Use Competition ID

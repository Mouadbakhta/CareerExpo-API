# Etudiant Controller API Documentation

## Overview
The Etudiant Controller manages student (etudiant) registrations and profiles in the CareerExpo application. It handles CV uploads, student validation, and status management.

## Base URL
\`\`\`
http://localhost:8080/api/etudiant
\`\`\`

## Endpoints Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register new student with CV upload |
| GET | `/all` | Retrieve all students |
| GET | `/getById/{id}` | Get student by ID |
| GET | `/getByCompetition/{competitionId}` | Get students by competition |
| GET | `/getByEstablishment/{establishment}` | Get students by establishment |
| GET | `/getByStatus/{status}` | Get students by status |
| PUT | `/update/{id}` | Update student profile |
| PUT | `/updateCv/{id}` | Upload/update student CV |
| PUT | `/validate/{id}` | Validate/accept student |
| PUT | `/refuse/{id}` | Refuse/reject student |
| DELETE | `/delete/{id}` | Delete student record |

---

## Detailed Endpoints

### 1. Register New Student
**Endpoint:** `POST /api/etudiant/register`

**Description:** Registers a new student with CV file upload.

**Content-Type:** `multipart/form-data`

**Form Fields:**
- `firstName` (text): Student's first name
- `lastName` (text): Student's last name
- `email` (text): Student's email address
- `establishment` (text): Educational establishment name
- `competitionId` (number): Competition registration ID
- `cv` (file): PDF CV file (max 10MB)

**Success Response (201 Created):**
\`\`\`json
{
  "id": 1,
  "firstName": "Hassan",
  "lastName": "Mohamed",
  "email": "hassan.mohamed@student.com",
  "establishment": "Faculty of Science, Fez",
  "competition": {
    "id": 1,
    "name": "Tech Career Expo 2025"
  },
  "cvPath": "uploads/cv/hassan_cv_1705318200.pdf",
  "status": "PENDING",
  "createdAt": "2025-01-15T12:30:00"
}
\`\`\`

**Error Response (400 Bad Request):**
\`\`\`json
{
  "timestamp": "2025-01-15T12:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Competition not found with id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `POST`
- URL: `http://localhost:8080/api/etudiant/register`
- Headers: (Auto-set by Postman for multipart/form-data)
- Body: form-data
  - `firstName`: Hassan (text)
  - `lastName`: Mohamed (text)
  - `email`: hassan.mohamed@student.com (text)
  - `establishment`: Faculty of Science, Fez (text)
  - `competitionId`: 1 (text/number)
  - `cv`: Select your CV file (file)

---

### 2. Get All Students
**Endpoint:** `GET /api/etudiant/all`

**Description:** Retrieves all registered students.

**Success Response (200 OK):**
\`\`\`json
[
  {
    "id": 1,
    "firstName": "Hassan",
    "lastName": "Mohamed",
    "email": "hassan.mohamed@student.com",
    "establishment": "Faculty of Science, Fez",
    "competition": {
      "id": 1,
      "name": "Tech Career Expo 2025"
    },
    "cvPath": "uploads/cv/hassan_cv.pdf",
    "status": "PENDING",
    "createdAt": "2025-01-10T09:30:00"
  },
  {
    "id": 2,
    "firstName": "Aisha",
    "lastName": "Ahmed",
    "email": "aisha.ahmed@student.com",
    "establishment": "Engineering School, Casablanca",
    "competition": {
      "id": 1,
      "name": "Tech Career Expo 2025"
    },
    "cvPath": "uploads/cv/aisha_cv.pdf",
    "status": "ACCEPTED",
    "createdAt": "2025-01-12T13:45:00"
  }
]
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/etudiant/all`
- Headers: None required

---

### 3. Get Student by ID
**Endpoint:** `GET /api/etudiant/getById/{id}`

**Description:** Retrieves a specific student by their ID.

**URL Parameter:**
- `id` (required, integer): The student's unique identifier

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "firstName": "Hassan",
  "lastName": "Mohamed",
  "email": "hassan.mohamed@student.com",
  "establishment": "Faculty of Science, Fez",
  "competition": {
    "id": 1,
    "name": "Tech Career Expo 2025"
  },
  "cvPath": "uploads/cv/hassan_cv.pdf",
  "status": "PENDING",
  "createdAt": "2025-01-10T09:30:00"
}
\`\`\`

**Error Response (404 Not Found):**
\`\`\`json
{
  "timestamp": "2025-01-15T12:35:00",
  "status": 404,
  "error": "Not Found",
  "message": "Etudiant not found with id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/etudiant/getById/1`
- Headers: None required

---

### 4. Get Students by Competition
**Endpoint:** `GET /api/etudiant/getByCompetition/{competitionId}`

**Description:** Retrieves all students registered for a specific competition.

**URL Parameter:**
- `competitionId` (required, integer): The competition's unique identifier

**Success Response (200 OK):**
\`\`\`json
[
  {
    "id": 1,
    "firstName": "Hassan",
    "lastName": "Mohamed",
    "email": "hassan.mohamed@student.com",
    "establishment": "Faculty of Science, Fez",
    "competition": {
      "id": 1,
      "name": "Tech Career Expo 2025"
    },
    "cvPath": "uploads/cv/hassan_cv.pdf",
    "status": "PENDING",
    "createdAt": "2025-01-10T09:30:00"
  },
  {
    "id": 2,
    "firstName": "Aisha",
    "lastName": "Ahmed",
    "email": "aisha.ahmed@student.com",
    "establishment": "Engineering School, Casablanca",
    "competition": {
      "id": 1,
      "name": "Tech Career Expo 2025"
    },
    "cvPath": "uploads/cv/aisha_cv.pdf",
    "status": "ACCEPTED",
    "createdAt": "2025-01-12T13:45:00"
  }
]
\`\`\`

**Error Response (404 Not Found):**
\`\`\`json
{
  "timestamp": "2025-01-15T12:40:00",
  "status": 404,
  "error": "Not Found",
  "message": "No etudiants found for competition id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/etudiant/getByCompetition/1`
- Headers: None required

---

### 5. Get Students by Establishment
**Endpoint:** `GET /api/etudiant/getByEstablishment/{establishment}`

**Description:** Retrieves all students from a specific educational establishment.

**URL Parameter:**
- `establishment` (required, string): The establishment name (URL-encoded if contains spaces)

**Success Response (200 OK):**
\`\`\`json
[
  {
    "id": 1,
    "firstName": "Hassan",
    "lastName": "Mohamed",
    "email": "hassan.mohamed@student.com",
    "establishment": "Faculty of Science, Fez",
    "competition": {
      "id": 1,
      "name": "Tech Career Expo 2025"
    },
    "cvPath": "uploads/cv/hassan_cv.pdf",
    "status": "PENDING",
    "createdAt": "2025-01-10T09:30:00"
  }
]
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/etudiant/getByEstablishment/Faculty%20of%20Science,%20Fez`
- Headers: None required
- Note: Use %20 for spaces in URL

---

### 6. Get Students by Status
**Endpoint:** `GET /api/etudiant/getByStatus/{status}`

**Description:** Retrieves all students with a specific status.

**URL Parameter:**
- `status` (required, string): Student status (PENDING, ACCEPTED, or REFUSED)

**Valid Status Values:**
- `PENDING` - Application under review
- `ACCEPTED` - Student accepted
- `REFUSED` - Student rejected

**Success Response (200 OK):**
\`\`\`json
[
  {
    "id": 1,
    "firstName": "Hassan",
    "lastName": "Mohamed",
    "email": "hassan.mohamed@student.com",
    "establishment": "Faculty of Science, Fez",
    "competition": {
      "id": 1,
      "name": "Tech Career Expo 2025"
    },
    "cvPath": "uploads/cv/hassan_cv.pdf",
    "status": "PENDING",
    "createdAt": "2025-01-10T09:30:00"
  }
]
\`\`\`

**Postman Setup:**
- Method: `GET`
- URL: `http://localhost:8080/api/etudiant/getByStatus/PENDING`
- Headers: None required

---

### 7. Update Student Profile
**Endpoint:** `PUT /api/etudiant/update/{id}`

**Description:** Updates student profile information (without CV).

**URL Parameter:**
- `id` (required, integer): The student's unique identifier

**Request Body:**
\`\`\`json
{
  "firstName": "Hassan",
  "lastName": "Mohamed",
  "email": "hassan.updated@student.com",
  "establishment": "Faculty of Science, Fez",
  "competitionId": 1
}
\`\`\`

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "firstName": "Hassan",
  "lastName": "Mohamed",
  "email": "hassan.updated@student.com",
  "establishment": "Faculty of Science, Fez",
  "competition": {
    "id": 1,
    "name": "Tech Career Expo 2025"
  },
  "cvPath": "uploads/cv/hassan_cv.pdf",
  "status": "PENDING",
  "createdAt": "2025-01-10T09:30:00"
}
\`\`\`

**Postman Setup:**
- Method: `PUT`
- URL: `http://localhost:8080/api/etudiant/update/1`
- Headers: `Content-Type: application/json`
- Body: Raw JSON (as shown above)

---

### 8. Upload/Update Student CV
**Endpoint:** `PUT /api/etudiant/updateCv/{id}`

**Description:** Updates or uploads a new CV for the student.

**Content-Type:** `multipart/form-data`

**URL Parameter:**
- `id` (required, integer): The student's unique identifier

**Form Fields:**
- `cv` (file): New PDF CV file (max 10MB)

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "firstName": "Hassan",
  "lastName": "Mohamed",
  "email": "hassan.mohamed@student.com",
  "establishment": "Faculty of Science, Fez",
  "competition": {
    "id": 1,
    "name": "Tech Career Expo 2025"
  },
  "cvPath": "uploads/cv/hassan_cv_1705320600.pdf",
  "status": "PENDING",
  "createdAt": "2025-01-10T09:30:00"
}
\`\`\`

**Error Response (400 Bad Request):**
\`\`\`json
{
  "timestamp": "2025-01-15T12:45:00",
  "status": 400,
  "error": "Bad Request",
  "message": "File upload failed: No file provided"
}
\`\`\`

**Postman Setup:**
- Method: `PUT`
- URL: `http://localhost:8080/api/etudiant/updateCv/1`
- Headers: (Auto-set by Postman for multipart/form-data)
- Body: form-data
  - `cv`: Select new CV file (file)

---

### 9. Validate Student (Accept)
**Endpoint:** `PUT /api/etudiant/validate/{id}`

**Description:** Accepts/validates a student application.

**URL Parameter:**
- `id` (required, integer): The student's unique identifier

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "firstName": "Hassan",
  "lastName": "Mohamed",
  "email": "hassan.mohamed@student.com",
  "establishment": "Faculty of Science, Fez",
  "competition": {
    "id": 1,
    "name": "Tech Career Expo 2025"
  },
  "cvPath": "uploads/cv/hassan_cv.pdf",
  "status": "ACCEPTED",
  "createdAt": "2025-01-10T09:30:00"
}
\`\`\`

**Error Response (404 Not Found):**
\`\`\`json
{
  "timestamp": "2025-01-15T12:50:00",
  "status": 404,
  "error": "Not Found",
  "message": "Etudiant not found with id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `PUT`
- URL: `http://localhost:8080/api/etudiant/validate/1`
- Headers: None required

---

### 10. Refuse Student (Reject)
**Endpoint:** `PUT /api/etudiant/refuse/{id}`

**Description:** Rejects/refuses a student application.

**URL Parameter:**
- `id` (required, integer): The student's unique identifier

**Success Response (200 OK):**
\`\`\`json
{
  "id": 1,
  "firstName": "Hassan",
  "lastName": "Mohamed",
  "email": "hassan.mohamed@student.com",
  "establishment": "Faculty of Science, Fez",
  "competition": {
    "id": 1,
    "name": "Tech Career Expo 2025"
  },
  "cvPath": "uploads/cv/hassan_cv.pdf",
  "status": "REFUSED",
  "createdAt": "2025-01-10T09:30:00"
}
\`\`\`

**Postman Setup:**
- Method: `PUT`
- URL: `http://localhost:8080/api/etudiant/refuse/1`
- Headers: None required

---

### 11. Delete Student
**Endpoint:** `DELETE /api/etudiant/delete/{id}`

**Description:** Deletes a student record from the system.

**URL Parameter:**
- `id` (required, integer): The student's unique identifier

**Success Response (204 No Content):**
\`\`\`
(Empty response body)
\`\`\`

**Error Response (404 Not Found):**
\`\`\`json
{
  "timestamp": "2025-01-15T12:55:00",
  "status": 404,
  "error": "Not Found",
  "message": "Etudiant not found with id: 999"
}
\`\`\`

**Postman Setup:**
- Method: `DELETE`
- URL: `http://localhost:8080/api/etudiant/delete/1`
- Headers: None required

---

## Complete Testing Workflow in Postman

### Step 1: Setup Prerequisites
1. Create an Admin (use Admin Controller)
2. Create a Competition (use Competition Controller with Admin ID from step 1)
3. Note down the Competition ID

### Step 2: Student Registration & Management
1. **Register Student** → Use Competition ID from prerequisites
   - Upload a real PDF file as CV
   - Note down Student ID from response

2. **Get All Students** → Verify registration

3. **Get Student by ID** → Use Student ID from step 2.1

4. **Get Students by Competition** → Use Competition ID

5. **Update Student Profile** → Use Student ID
   - Change email or establishment

6. **Upload New CV** → Use Student ID
   - Upload a different PDF file

### Step 3: Status Management
1. **Get Students by Status** → Filter by PENDING
   - Should show newly registered students

2. **Validate Student** → Accept student (use Student ID)
   - Status should change to ACCEPTED

3. **Get Students by Status** → Filter by ACCEPTED
   - Should show accepted student

### Step 4: Additional Filters
1. **Get Students by Establishment** → Filter by establishment name

2. **Get by Specific Status** → Try REFUSED status

### Step 5: Cleanup
1. **Refuse Another Student** → Test rejection workflow

2. **Delete Student** → Use Student ID

---

## File Upload Configuration

Your application is configured to:
- **Upload Directory:** `uploads/cv`
- **Max File Size:** 10MB per file
- **Max Request Size:** 10MB total

### Supported File Types
- PDF files (.pdf)

### CV Path Format
CVs are saved with naming pattern: `{studentFirstName}_{timestamp}.pdf`
Example: `hassan_1705318200.pdf`

---

## Response Status Codes

| Status Code | Meaning |
|-------------|---------|
| 200 | OK - Request successful |
| 201 | Created - Resource created successfully |
| 204 | No Content - Successful deletion |
| 400 | Bad Request - Invalid data or missing fields |
| 404 | Not Found - Resource doesn't exist |
| 500 | Internal Server Error - Server-side error |

---

## Error Handling

All error responses follow this format:
\`\`\`json
{
  "timestamp": "2025-01-15T13:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Detailed error message"
}
\`\`\`

Check the message field for specific details about what went wrong.

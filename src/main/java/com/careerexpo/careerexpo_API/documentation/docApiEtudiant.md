# Etudiant file

This file contains requests related to student (etudiant) management in the Career Expo API.

## Requests

### createEtudiant
- **Method:** POST
- **URL:** http://localhost:8080/api/etudiants
- **Headers:**
  - Content-Type: application/json
- **Body:**
```json
{
  "nom": "Bourquouquou",
  "prenom": "Mouad",
  "etablissement": "ENSA",
  "niveau": "BAC +4",
  "cvPath": "/uploads/cv/john_doe_cv.pdf",
  "status": "PENDING",
  "competition": {
    "id": 3
  }
}
```
- **Example Response:** No saved example yet

### createEtudiantCV
- **Method:** POST
- **URL:** http://localhost:8080/api/etudiants/with-cv
- **Headers:**
  - Content-Type: multipart/form-data
- **Body:**
  - etudiant: JSON string with student info
  - cv: file (PDF)
- **Example Response:** No saved example yet

### deleteEtudiant
- **Method:** DELETE
- **URL:** http://localhost:8080/api/etudiants/3
- **Headers:**
  - Content-Type: application/json
- **Body:** None
- **Example Response:** No saved example yet

### selectStudentComp
- **Method:** GET
- **URL:** http://localhost:8080/api/etudiants/2/with-competition
- **Headers:**
  - Content-Type: application/json
- **Body:** None
- **Example Response:** No saved example yet

### selectAll
- **Method:** GET
- **URL:** http://localhost:8080/api/etudiants
- **Headers:**
  - Content-Type: application/json
- **Body:** None
- **Example Response:** No saved example yet

### selectAllCompId
- **Method:** GET
- **URL:** http://localhost:8080/api/etudiants/competition/3
- **Headers:**
  - Content-Type: application/json
- **Body:** None
- **Example Response:** No saved example yet

### selectAllCompIdDesc
- **Method:** GET
- **URL:** http://localhost:8080/api/etudiants/competition/3/with-details
- **Headers:**
  - Content-Type: application/json
- **Body:** None
- **Example Response:** No saved example yet

### selectStudentName
- **Method:** GET
- **URL:** http://localhost:8080/api/etudiants/search?nom=Doe&prenom=John
- **Headers:**
  - Content-Type: application/json
- **Body:** None
- **Example Response:** No saved example yet

### updateStudnet
- **Method:** PUT
- **URL:** http://localhost:8080/api/etudiants/2
- **Headers:** None
- **Body:**
```json
{   "nom": "Doe",
    "prenom": "John",
    "etablissement": "New University",
    "niveau": "PhD",
    "cvPath": "/uploads/cv/john_doe_cv.pdf",
    "status": "ACCEPTED",
    "competition": { "id": 2 }
}
```
- **Example Response:** No saved example yet

### validateStudent
- **Method:** POST
- **URL:** http://localhost:8080/api/etudiants/3/validate
- **Headers:**
  - Content-Type: application/json
- **Body:** None
- **Example Response:** No saved example yet

### refuseStudent
- **Method:** POST
- **URL:** http://localhost:8080/api/etudiants/2/refuse
- **Headers:** None
- **Body:**
```json
{   "nom": "Doe",
    "prenom": "John",
    "etablissement": "New University",
    "niveau": "PhD",
    "cvPath": "/uploads/cv/john_doe_cv.pdf",
    "status": "ACCEPTED",
    "competition": { "id": 2 }
}
```
- **Example Response:** No saved example yet

### countStudentByComp
- **Method:** GET
- **URL:** http://localhost:8080/api/etudiants/competition/3/count
- **Headers:**
  - Content-Type: application/json
- **Body:** None
- **Example Response:** No saved example yet

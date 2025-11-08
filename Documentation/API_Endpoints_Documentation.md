Career Expo API Documentation
Overview
Welcome to the Career Expo API documentation. This collection provides a comprehensive set of APIs for managing a career expo platform, including admin management, competition organization, and student registration functionalities.

The Career Expo API is designed to help you:

Manage administrative users
Create and organize competitions
Handle student registrations and applications
Track student participation and validation status
Base URLs:

Port 8080: http://localhost:8080
Port 8081: http://localhost:8081


# Admin APIs

1. Create Admin
Creates a new administrator account.

Endpoint: POST http://localhost:8081/api/admins

Description: This is a POST request for submitting admin data to the API. The request submits JSON data, and the data is reflected in the response. A successful POST request typically returns a 200 OK or 201 Created response code.

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body:

{    "nom": "Ali",    "prenom": "Bourquouquou",    "email": "ali@example.com",    "password": "securePassword123"}
{
    "nom": "Ali",
    "prenom": "Bourquouquou",
    "email": "ali@example.com",
    "password": "securePassword123"
}
Response: Returns the created admin object with status 200 OK or 201 Created.

2. Get Admin by ID
Retrieves a specific administrator by their ID.

Endpoint: GET http://localhost:8081/api/admins/{id}

Description: This is a GET request used to retrieve data from an endpoint. There is no request body for a GET request. A successful GET response will have a 200 OK status and should include the admin data in the response body.

Example: GET http://localhost:8081/api/admins/3

Headers: None

Request Body: None

Response: Returns the admin object with the specified ID.

3. Get Admin by Email
Retrieves an administrator by their email address.

Endpoint: GET http://localhost:8081/api/admins/email/{email}

Example: GET http://localhost:8081/api/admins/email/ali@example.com

Headers: None

Request Body: None

Response: Returns the admin object matching the email address.

4. Get All Admins
Retrieves a list of all administrators.

Endpoint: GET http://localhost:8081/api/admins

Headers: None

Request Body: None

Response: Returns an array of all admin objects.

5. Update Admin
Updates an existing administrator's information.

Endpoint: PUT http://localhost:8081/api/admins/{id}

Example: PUT http://localhost:8081/api/admins/7

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body:

{    "nom": "Ali",    "prenom": "Bourquouquou",    "email": "ali@example.com",    "password": "securePassword123"}
{
    "nom": "Ali",
    "prenom": "Bourquouquou",
    "email": "ali@example.com",
    "password": "securePassword123"
}
Response: Returns the updated admin object.

6. Delete Admin
Deletes an administrator by ID.

Endpoint: DELETE http://localhost:8080/api/admins/{id}

Example: DELETE http://localhost:8080/api/admins/4

Headers: None

Request Body: None

Response: Returns confirmation of deletion.

# Competition APIs
1. Create Competition
Creates a new competition.

Endpoint: POST http://localhost:8081/api/competitions

Headers: None (Content-Type is automatically set for JSON)

Request Body:

{    "description": "Competition on AI and     "annee": "2024-03-15",        IoT",    "admin": {        "id": 7    }}
{
    "description": "Competition on AI and IoT",
    "annee": "2024-03-15",
    "admin": {
        "id": 7
    }
}
Response: Returns the created competition object.

2. Get Competition by ID
Retrieves a specific competition by its ID.

Endpoint: GET http://localhost:8080/api/competitions/{id}

Example: GET http://localhost:8080/api/competitions/3

Headers: None

Request Body: None

Response: Returns the competition object with the specified ID.

3. Get All Competitions
Retrieves a list of all competitions.

Endpoint: GET http://localhost:8080/api/competitions

Headers: None

Request Body: None

Response: Returns an array of all competition objects.

4. Get Competition with Students
Retrieves a competition along with all registered students.

Endpoint: GET http://localhost:8080/api/competitions/{id}/with-etudiants

Example: GET http://localhost:8080/api/competitions/2/with-etudiants

Headers: None

Request Body: None

Response: Returns the competition object including an array of registered students.

5. Get Competitions by Year
Retrieves all competitions for a specific year.

Endpoint: GET http://localhost:8080/api/competitions/annee/{year}

Example: GET http://localhost:8080/api/competitions/annee/2025

Headers: None

Request Body: None

Response: Returns an array of competitions for the specified year.

6. Get Competitions by Admin ID
Retrieves all competitions managed by a specific admin.

Endpoint: GET http://localhost:8081/api/competitions/admin/{adminId}

Example: GET http://localhost:8081/api/competitions/admin/3

Headers: None

Request Body: None

Response: Returns an array of competitions managed by the specified admin.

7. Get Competitions by Admin ID with Details
Retrieves all competitions managed by a specific admin with detailed information.

Endpoint: GET http://localhost:8081/api/competitions/admin/{adminId}/with-details

Example: GET http://localhost:8081/api/competitions/admin/3/with-details

Headers: None

Request Body: None

Response: Returns an array of competitions with detailed information.

8. Get Competitions by Admin Email
Retrieves all competitions managed by an admin identified by email.

Endpoint: GET http://localhost:8080/api/competitions/admin-email/{email}

Example: GET http://localhost:8080/api/competitions/admin-email/mouadbourquouquou@gmail.com

Headers: None

Request Body: None

Response: Returns an array of competitions managed by the admin with the specified email.

9. Get Competitions Between Dates
Retrieves competitions within a specific date range.

Endpoint: GET http://localhost:8080/api/competitions/between

Query Parameters:

startDate (required): Start date in format YYYY-MM-DD
endDate (required): End date in format YYYY-MM-DD
Example: GET http://localhost:8080/api/competitions/between?startDate=2025-01-01&endDate=2025-12-31

Headers: None

Request Body: None

Response: Returns an array of competitions within the specified date range.

10. Update Competition
Updates an existing competition.

Endpoint: PUT http://localhost:8081/api/competitions/{id}

Example: PUT http://localhost:8081/api/competitions/3

Headers: None (Content-Type is automatically set for JSON)

Request Body:

{    "description": "Competition on AI and     "annee": "2024-03-15",        IoT",    "admin": {        "id": 7    }}
{
    "description": "Competition on AI and IoT",
    "annee": "2024-03-15",
    "admin": {
        "id": 7
    }
}
Response: Returns the updated competition object.

11. Delete Competition
Deletes a competition by ID.

Endpoint: DELETE http://localhost:8080/api/competitions/{id}

Example: DELETE http://localhost:8080/api/competitions/4

Headers: None

Request Body: None

Response: Returns confirmation of deletion.

# Student (Etudiant) APIs
1. Create Student
Creates a new student registration.

Endpoint: POST http://localhost:8080/api/etudiants

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body:

{    "nom": "Bourquouquou",    "prenom": "Mouad",    "etablissement": "ENSA",    "niveau": "BAC +4",    "cvPath": "/uploads/cv/john_doe_cv.pdf",    "status": "PENDING",    "competition": {        "id": 3    }}
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

Response: Returns the created student object.

2. Create Student with CV
Creates a new student registration with CV file upload (PDF and video).

Endpoint: POST http://localhost:8081/api/etudiants/with-cv

Headers:

Content-Type: multipart/form-data
Content-Type: multipart/form-data
Request Body (Form Data):

etudiant (text): JSON string containing student information
{    "nom": "Younes",    "prenom": "Bourquouquou",    "etablissement": "ENSA Marrakech",    "telephone": "0600000000",    "niveau": "Licence 3",    "competition": {        "id": 3    }}
{
    "nom": "Younes",
    "prenom": "Bourquouquou",
    "etablissement": "ENSA Marrakech",
    "telephone": "0600000000",
    "niveau": "Licence 3",
    "competition": {
        "id": 3
    }
}
cvPdf (file): PDF file of the student's CV
cvVideo (file): Video file of the student's CV presentation
Response: Returns the created student object with file paths.

3. Delete Student
Deletes a student by ID.

Endpoint: DELETE http://localhost:8080/api/etudiants/{id}

Example: DELETE http://localhost:8080/api/etudiants/3

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body: None

Response: Returns confirmation of deletion.

4. Get Student with Competition
Retrieves a student along with their competition information.

Endpoint: GET http://localhost:8080/api/etudiants/{id}/with-competition

Example: GET http://localhost:8080/api/etudiants/2/with-competition

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body: None

Response: Returns the student object including competition details.

5. Get All Students
Retrieves a list of all students.

Endpoint: GET http://localhost:8081/api/etudiants

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body: None

Response: Returns an array of all student objects.

6. Get Students by Competition ID
Retrieves all students registered for a specific competition.

Endpoint: GET http://localhost:8080/api/etudiants/competition/{competitionId}

Example: GET http://localhost:8080/api/etudiants/competition/3

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body: None

Response: Returns an array of students registered for the specified competition.

7. Get Students by Competition ID with Details
Retrieves all students registered for a specific competition with detailed information.

Endpoint: GET http://localhost:8080/api/etudiants/competition/{competitionId}/with-details

Example: GET http://localhost:8080/api/etudiants/competition/3/with-details

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body: None

Response: Returns an array of students with detailed information.

8. Search Students by Name
Searches for students by first name and last name.

Endpoint: GET http://localhost:8080/api/etudiants/search

Query Parameters:

nom (required): Last name
prenom (required): First name
Example: GET http://localhost:8080/api/etudiants/search?nom=Doe&prenom=John

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body: None

Response: Returns an array of students matching the search criteria.

9. Validate Student
Validates a student's application (changes status to ACCEPTED).

Endpoint: POST http://localhost:8081/api/etudiants/{id}/validate

Example: POST http://localhost:8081/api/etudiants/4/validate

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body: None

Response: Returns the updated student object with ACCEPTED status.

10. Refuse Student
Refuses a student's application (changes status to REFUSED).

Endpoint: POST http://localhost:8081/api/etudiants/{id}/refuse

Example: POST http://localhost:8081/api/etudiants/4/refuse

Headers: None

Request Body: None (body is optional for this endpoint)

Response: Returns the updated student object with REFUSED status.

11. Count Students by Competition
Returns the count of students registered for a specific competition.

Endpoint: GET http://localhost:8081/api/etudiants/competition/{competitionId}/count

Example: GET http://localhost:8081/api/etudiants/competition/3/count

Headers:

Content-Type: application/json
Content-Type: application/json
Request Body: None

Response: Returns a numeric count of students.

Additional Notes
Status Values
Students can have the following status values:

PENDING - Application is pending review
ACCEPTED - Application has been validated
REFUSED - Application has been refused
Port Configuration
The API uses two different ports:

Port 8080: Used for most read operations and some write operations
Port 8081: Used for create, update, and some specialized operations
Authentication
Currently, the API does not implement authentication headers in the documented requests. Ensure proper authentication mechanisms are in place before deploying to production.

Error Handling
All endpoints should return appropriate HTTP status codes:

200 OK - Successful GET, PUT, DELETE operations
201 Created - Successful POST operations
400 Bad Request - Invalid request data
404 Not Found - Resource not found
500 Internal Server Error - Server-side errors
Getting Started
Ensure both services are running on ports 8080 and 8081
Use the provided endpoints to interact with the API
All JSON requests should include the Content-Type: application/json header
File uploads should use Content-Type: multipart/form-data
For questions or issues, please contact the development team.
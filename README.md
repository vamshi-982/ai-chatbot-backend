AI Powered Chatbot Backend

Project Overview
This project is an AI-powered chatbot backend system developed using Spring Boot. 
The application allows authenticated users to communicate with an AI chatbot while maintaining chat sessions and conversation history.

JWT-based authentication is implemented to secure APIs so that only authorized users can access chatbot services.

--------------------------------------------------

Features
- User Registration
- User Login using JWT Authentication
- Secure REST APIs
- Chat Session Creation
- AI-powered Chat Responses
- Context-aware Conversations
- Chat History Management
- Global Exception Handling
- Swagger API Documentation
- Postman API Collection Support

--------------------------------------------------

Tech Stack
 Java 17
 Spring Boot
 Spring Security
 JWT Authentication
 Spring Data JPA (Hibernate)
 MySQL Database
 WebClient
 Groq AI API
 Maven
 Swagger OpenAPI

--------------------------------------------------

System Architecture

Controller Layer
    ↓
Service Layer
    ↓
Repository Layer
    ↓
MySQL Database
    ↓
AI Service Integration

--------------------------------------------------

Authentication Flow

User Registration
    ↓
User Login
    ↓
JWT Token Generation
    ↓
Access Protected APIs using Token

--------------------------------------------------

API Endpoints

User APIs
POST   /users/register      Register new user
POST   /auth/login          Login and receive JWT token

Chat Session APIs
POST   /sessions/create/{userId}     Create chat session

Chat APIs
POST   /chat/send/{sessionId}        Send message to AI
GET    /chat/history/{sessionId}     Retrieve chat history

--------------------------------------------------

API Testing
APIs can be tested using Postman or Swagger UI.

Swagger URL
http://localhost:8080/swagger-ui/index.html

--------------------------------------------------

How to Run Project Locally

1. Clone Repository
git clone <your-repository-link>

2. Configure Database
Create MySQL database:
chatbot_db

Update database configuration in application.properties.

3. Configure AI API Key
Add Groq API key in application.properties:
groq.api.key=YOUR_API_KEY

4. Run Application
mvn spring-boot:run

Application will start at:
http://localhost:8080

--------------------------------------------------

Security
- JWT-based Authentication implemented
- Protected REST endpoints
- Token-based authorization

--------------------------------------------------

Project Structure

com.grovyn.chatbot
    ai
    controller
    dto
    entity
    exception
    repository
    security
    service
    config

--------------------------------------------------

Author
Siddapuram Vamshi
MCA Student | Backend Developer

--------------------------------------------------

Project Type
AI Powered Secure Chatbot Backend System using Spring Boot, JWT Authentication and AI Integration.
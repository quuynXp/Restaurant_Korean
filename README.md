🍽️ Restaurant Management System - Backend Microservices
- A complete backend system for restaurant operations, built with Spring Boot microservices, containerized using Docker, and orchestrated via Eureka Service Discovery. Designed for scalability, performance, and real-time customer interaction.

🧩 Microservices Overview
- Service	Description
    - Eureka-Server	
        Acts as a Service Discovery for registering all microservices. Enables dynamic discovery and load balancing of services across the system.
    - Gateway-Service	
        The API Gateway (Zuul or Spring Cloud Gateway) handles routing, rate limiting, authentication (JWT), and forwards requests to appropriate backend services.
    - User-Service	
        Manages user registration, login, password reset, and role-based access control (admin/user). Implements JWT Auth (Access & Refresh Token), integrated with Spring Security + OAuth2.
    - Menu-Service	
        Provides CRUD APIs for managing menu items such as dishes, drinks. Supports full-text search using Elasticsearch.
    - Order-Service	
        Handles the entire order lifecycle. Supports placing, updating, tracking, and cancelling orders. Communicates with Kitchen, Payment, and Notification services.
    - Kitchen-Service	
        Receives and processes orders from Order-Service. Manages kitchen workflow and updates status in real time. Communicates status back to Order-Service or via Kafka.
    - Payment-Service	
        Integrates VNPAY QR and PayPal (Sandbox) for secure online payments. Validates payment status and triggers notifications or invoice generation.
    - Chat-Service	
        Real-time chat system built with Socket.IO, allowing communication between customers and restaurant staff for support or order-related queries.
    - Notification-Service	
        Sends real-time notifications via Kafka events, email (SMTP), or system messages when orders are created, updated, or payment is successful.
    - Invoice-Service	
        Generates downloadable invoices based on completed orders. Works closely with Payment-Service and sends invoices via email.
    - Report-Service	
        Generates daily, weekly, and monthly reports on revenue, order count, dish popularity, etc. Aggregates data from Order and Menu services.
    - Kitchen-Service	
        Manages backend kitchen operations, order queues, and preparation status.
    - Chatbot (part of User or standalone)	
        AI-based chatbot (OpenAI or similar) that assists users in booking tables, suggesting dishes, and handling basic inquiries.
    - docker-compose.yml	
        Container orchestration for all microservices, including MySQL, Redis, Kafka, Elasticsearch, and each Spring Boot service.

🛠️ Core Tech Stack
- Programming Language:	            Java 21
- Framework:	                    Spring Boot, Spring Cloud, Spring Security, Spring Batch
- Authentication:	                OAuth2, JWT (Access + Refresh Tokens), Nimbus JOSE + JWT
- Communication:	                gRPC (Internal services), REST, WebSocket (Chat)
- Database:	                        MySQL (Dockerized), Redis (Caching & Session)
- Messaging:	                    Apache Kafka (Order & Notification Events)
- Search:	                        Elasticsearch
- Real-time:	                    Socket.IO (Chat), Kafka Notifications
- Payment Gateways:	                VNPAY QR, PayPal (Sandbox)
- Email Service:	                SMTP (Mailtrap, Gmail, or other SMTP relay)
- Containerization:	                Docker, docker-compose
- Monitoring:	                    Swagger UI for API documentation

🚀 Deployment
- Local Setup with Docker Compose
1. Ensure Docker & Docker Compose are installed.
2. Run the following command:
   - docker-compose up --build
3. Access services via:
    - Eureka Dashboard: http://localhost:8761
    - API Gateway: http://localhost:8080
    - Swagger UI (e.g., via /swagger-ui.html depending on service)

📬 Contact or Contributions
- Feel free to contribute, open issues, or suggest improvements. This system is built to be scalable, testable, and production-ready.

✨ Features
✅ gRPC-based Microservices Communication
- Designed gRPC APIs for efficient, low-latency, high-throughput communication between internal services.

🔐 Secure JWT-based Authentication
- Implemented JWT Authentication (Access + Refresh Token) based on OAuth2 and Nimbus JOSE + JWT.

- Secured and documented APIs using Swagger/OpenAPI.

- Role-based access control (admin, user) managed via Spring Security filters.

🗃️ Database & ORM
- Utilized Spring Data JPA for ORM mapping.

- MySQL database containerized using Docker.

⚡ Performance Optimization with Redis
- Integrated Redis for high-performance caching.

- Cached frequently accessed data and managed token/session storage.

💬 Real-time Communication
- Built a real-time chat system using Socket.IO, enabling instant communication between customers and restaurant staff.

📁 Bulk Data Import
- Implemented bulk data import functionality using Spring Batch for CSV/Excel files.

🔍 Full-text Search Engine
- Integrated Elasticsearch for powerful full-text search across menu items and restaurant data.

🤖 AI-powered Chatbot
- Developed an AI Chatbot to:

- Suggest dishes based on customer preferences.

- Support table bookings.

- Assist staff with restaurant operations.

📦 Event-driven Architecture
- Leveraged Apache Kafka for:

- Event-driven order tracking.

- Real-time notifications.

🚪 API Gateway
- Deployed an API Gateway to:

- Centralize routing.

- Enforce authentication.

- Optimize load balancing.




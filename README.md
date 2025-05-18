Restaurant Management System - Backend Services
This backend project is designed to serve a high-performance restaurant management system, built with a microservices architecture and modern DevOps practices. It supports robust communication, real-time interactions, secure authentication, and intelligent automation.

✨ Features
✅ gRPC-based Microservices Communication
Designed gRPC APIs for efficient, low-latency, high-throughput communication between internal services.

🔐 Secure JWT-based Authentication
Implemented JWT Authentication (Access + Refresh Token) based on OAuth2 and Nimbus JOSE + JWT.

Secured and documented APIs using Swagger/OpenAPI.

Role-based access control (admin, user) managed via Spring Security filters.

🗃️ Database & ORM
Utilized Spring Data JPA for ORM mapping.

MySQL database containerized using Docker.

⚡ Performance Optimization with Redis
Integrated Redis for high-performance caching.

Cached frequently accessed data and managed token/session storage.

💬 Real-time Communication
Built a real-time chat system using Socket.IO, enabling instant communication between customers and restaurant staff.

📁 Bulk Data Import
Implemented bulk data import functionality using Spring Batch for CSV/Excel files.

🔍 Full-text Search Engine
Integrated Elasticsearch for powerful full-text search across menu items and restaurant data.

🤖 AI-powered Chatbot
Developed an AI Chatbot to:

Suggest dishes based on customer preferences.

Support table bookings.

Assist staff with restaurant operations.

💳 Payment Integration
Integrated VNPAY QR and PayPal (Sandbox) as payment gateways.

Implemented automated email notifications using SMTP for invoices, order updates, etc.

📦 Event-driven Architecture
Leveraged Apache Kafka for:

Event-driven order tracking.

Real-time notifications.

🚪 API Gateway
Deployed an API Gateway to:

Centralize routing.

Enforce authentication.

Optimize load balancing.

🚀 Tech Stack
Java 21, Spring Boot

gRPC, OAuth2, Nimbus JOSE + JWT

Spring Security, Spring JPA, Spring Batch

MySQL, Redis, Elasticsearch, Kafka

Socket.IO, Docker

Swagger, PayPal API, VNPAY API, SMTP Mail



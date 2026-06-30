Building your "EduTrack" upskilling platform has moved through a full enterprise development lifecycle, transforming a basic concept into a production-ready Full-Stack application.
Here is the complete architectural summary of our progress across all perspectives:
🗄️ 1. Database Perspective (The Source of Truth)
We transitioned from local storage to a hosted, relational PostgreSQL instance on Supabase, using real-world data schemas.
Schema Design: Implemented a relational structure with three primary tables: Users, Courses, and Learning_Tasks.
Relationships: Established a One-to-Many link between Users and Learning_Tasks (one user has many tasks).
Seeding: Injected 25+ realistic entries (e.g., Ananya, Vikram) to simulate an enterprise environment with varied task types and categories.
GitHub
GitHub
+4
⚙️ 2. Backend Perspective (The Business Logic)
The backend was built using Spring Boot 3 (Java 21), following the "Layered Architecture" standard in corporate MNCs.
GitHub
GitHub
+1
Controller Layer: Developed 10 RESTful endpoints under versioned routes (/api/v1/...) for full CRUD on users, tasks, and courses.
Service & Repository Layer: Decoupled business logic from controllers, utilizing Spring Data JPA for seamless communication with Supabase.
DTOs (Data Transfer Objects): Implemented Java Records to flatten entities for the frontend, ensuring security by not exposing internal DB fields directly.
Production Hardening: Integrated Global Exception Handling, SLF4J Logging, and Jakarta Bean Validation for robust error management.
GeeksforGeeks
GeeksforGeeks
+4
⚛️ 3. Frontend Perspective (The User Experience)
The UI was built with React, styled with Tailwind CSS, and optimized for production-grade performance.
Facebook
Facebook
+2
Component Architecture: Replaced a single-file app with reusable components: UserSwitcher, Stats, TaskBoard, and CourseCatalog.
State Management: Implemented a global activeUserId state, allowing the dashboard to dynamically fetch and display data specific to the selected user.
Network Layer: Used Axios to handle asynchronous API calls with proper loading states (Spinners) and error feedback.
UI/UX: Applied a modern "Enterprise" palette (Indigo/Slate) with responsive grid layouts and interactive task toggles.
Amazon Web Services (AWS)
Amazon Web Services (AWS)
+5
🛠️ 4. Deployment & Infrastructure
Dockerized: Successfully containerized the Spring Boot application using Multi-stage builds to minimize image size.
Microservices Ready: Mapped out the future split into a User-Task Service and a Course-Catalog Service for horizontal scalability.

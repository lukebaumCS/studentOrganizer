# Student Organizer

**Student Organizer** is a full-stack **Java Spring Boot** application designed as an organizational tool for students to efficiently manage their courses and schedules. The application uses a **PostgreSQL database** running in **Docker**, with **Hibernate** for ORM and **JPA** for persistence. The web application is served via **Apache Tomcat**.



## Tech Stack

* **Backend:** Java 17, Spring Boot 3
* **Frontend:** Thymeleaf, HTML5, CSS3
* **Database:** PostgreSQL (Dockerized)
* **ORM & Persistence:** Hibernate + JPA
* **Server:** Apache Tomcat
* **Build Tool:** Maven



## Setup & Run

1. Clone the repository:

```bash
git clone https://github.com/lukebaumCS/studentOrganizer
cd studentOrganizer
```

2. Start the PostgreSQL database using Docker:

```bash
docker-compose up -d
```

3. Build and run the Spring Boot application:

```bash
mvn clean install
mvn spring-boot:run
```

4. Access the application via:

```
http://localhost:8000
```

# AI E-Commerce Backend

Java 17 + Spring Boot 4.0.5 + Spring AI 2.0.1 + PostgreSQL/MySQL + JWT + JPA/Hibernate + Swagger/OpenAPI.

## Run
1. Create the database using `sql/mysql-schema.sql` OR `sql/postgresql-schema.sql`.
2. Edit `application-mysql.properties` or `application-postgres.properties`.
3. Set `OPENAI_API_KEY` and `JWT_SECRET` as environment variables.
4. Run with:
   `mvn clean spring-boot:run -Dspring-boot.run.profiles=mysql`
   or
   `mvn clean spring-boot:run -Dspring-boot.run.profiles=postgres`
5. Swagger: http://localhost:8080/swagger-ui.html

## Important
This starter includes the AI chat integration and chat_history persistence. It does NOT yet implement document ingestion/PGVector RAG or Kafka publishing; those are the next modules to add after the CRUD/auth flow is working.

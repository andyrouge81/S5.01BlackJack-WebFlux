
# 🃏 Reactive Blackjack API

A **reactive REST API** that simulates a Blackjack game, built with **Spring Boot WebFlux**, using a **polyglot persistence architecture**:

- 🟢 **MongoDB** → Stores game sessions
- 🔵 **MySQL (R2DBC)** → Stores players and statistics

The application is fully **Dockerized** and documented with **OpenAPI / Swagger**.

---

## 🚀 Features

- Create and manage Blackjack games
- Play turns using **HIT** and **STAND**
- Automatic dealer logic and game result calculation
- Player statistics tracking (games played / games won)
- Ranking endpoint
- Reactive, non-blocking architecture
- RESTful API with Swagger documentation

---

## 🏗 Architecture Overview

The project follows a **layered architecture**.

### 1️⃣ Entry Point
**`Application`**  
Bootstraps Spring Boot using `SpringApplication.run()`.

---

### 2️⃣ Web Layer (Controllers)

| Controller | Responsibility |
|-----------|----------------|
| **GameController** | Create games, play turns, fetch games, delete games |
| **PlayerController** | Create players, update names, get ranking, list player games |

---

### 3️⃣ Service Layer (Business Logic)

| Service | Responsibility |
|---------|----------------|
| **GameService** | Controls Blackjack game flow and result logic |
| **PlayerService** | Manages players, ranking, and game associations |

---

### 4️⃣ Domain Layer (Game Logic)

Core Blackjack logic is encapsulated in:

- **Game** → Game state and flow
- **Hand** → Cards held by player/dealer
- **Deck** → Card shuffling and drawing
- **Card** → Suit and rank
- **Enums** → `GameStatus`, `GameResult`, `Suit`, `Rank`

---

### 5️⃣ Persistence Layer

| Entity | Database | Technology |
|-------|----------|------------|
| **Game** | MongoDB | ReactiveMongoRepository |
| **Player** | MySQL | R2DBC Repository |

---

### 6️⃣ DTOs & Mappers

DTOs separate internal models from API responses:

- `GameResponse`
- `PlayerResponse`
- `HandResponse`
- `PlayerGameResponse`

Mapping handled by:

- `GameMapper`
- `PlayerMapper`

---

### 7️⃣ Global Error Handling

`GlobalExceptionHandler` provides consistent API error responses using:

- `GameNotFoundException`
- `PlayerNotFoundException`
- `ApiError`

---
### 📌 Project Structure
```pgsql
    controller/   → REST endpoints  
    service/      → Business logic  
    model/        → Domain objects  
    repository/   → Database access  
    dto/          → API data transfer objects  
    mapper/       → Entity ↔ DTO conversion  
    config/       → OpenAPI + configuration  
    exception/    → Global error handling
   ```
---

## 📦 Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring WebFlux**
- **MongoDB Reactive**
- **R2DBC MySQL**
- **Lombok**
- **SpringDoc OpenAPI**
- **Docker**

---

## 🧪 API Documentation

Swagger UI available at:

[Swagger UI](http://localhost:8081/swagger-ui.html)

---

## 🐳 Running with Docker

### 1️⃣ Create a network
    ```bash
    docker network create blackjack-net
    ```

### 2️⃣ Start MongoDB
    ```bash
    docker run -d \
  --name mongo \
  --network blackjack-net \
    mongo:7
    ```
### 3️⃣ Start MySQL
```bash
    docker run -d \
    --name mysql \
    --network blackjack-net \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=blackjack \
    -e MYSQL_USER=blackjack \
    -e MYSQL_PASSWORD=blackjack123 \
    mysql:8.4
   ```

### 4️⃣ Run the API
```bash

docker run -d \
--name blackjack-api \
--network blackjack-net \
-p 8081:8080 \
-e SPRING_PROFILES_ACTIVE=docker \
andyrouge/blackjack-api:latest
```


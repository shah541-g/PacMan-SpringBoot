# PacMaze Adventures

A Java-based Pacman-inspired desktop game with a client-server architecture. The system provides single-player gameplay with user authentication and score tracking capabilities.

---

## Overview

**PacMaze Adventures** is a graphical Pacman game featuring:
- **Architecture**: Client-Server using REST API
- **Frontend**: Java Swing-based GUI desktop application
- **Backend**: Spring Boot REST API server
- **Database**: MySQL for persistent data storage
- **Design Pattern**: Model-View-Controller (MVC)

The system currently supports **single-player gameplay only**. Multiplayer functionality is planned for future development and requires WebSocket/Socket implementation for real-time synchronization.

---

## Current Status

### ✅ Implemented Features

#### **Single-Player Mode**
- Play Pacman-inspired game with AI-controlled ghosts
- Save game progress to database
- Track and view game history
- Display high scores and game statistics

#### **User Management**
- User registration (Sign Up)
- Secure user login (Sign In)
- Persistent user sessions
- User profile information retrieval
- Input validation for username, email, and password

#### **Frontend Features**
- Clean Java Swing GUI with multiple pages:
  - **ApplicationLauncher**: Splash screen with progress bar
  - **SignUpPage**: User registration interface
  - **SignInPage**: User login interface
  - **MainPage**: Game menu with single-player and multiplayer options (UI only)
  - **PersonalDetailsPage**: View user profile information
  - **SinglePlayerGameDetailsPage**: View single-player game history
  - **GameOutcomePage**: Display game results and scores

#### **Backend Features**
- REST API endpoints for user management
- Single-player game data persistence
- Game history tracking by player email
- Spring Security configuration
- JPA/Hibernate for ORM

### ⚠️ Incomplete Features

#### **Multiplayer Mode**
- UI buttons and pages are present but **NOT FUNCTIONAL**
- Backend endpoints exist but are **NOT FULLY IMPLEMENTED**
- **WebSocket/Socket support is NOT implemented** - required for real-time player synchronization
- Room management, player role assignment, and move broadcasting are placeholders
- See [Remaining.txt](Remaining.txt) for implementation roadmap

---

## System Architecture

### Frontend (Java Swing)

```
Frontend/src/
├── App.java                          # Application entry point
├── pages/                            # UI Pages (MVC View)
│   ├── ApplicationLauncher.java      # Splash screen with progress bar
│   ├── SignUpPage.java               # User registration
│   ├── SignInPage.java               # User login
│   ├── MainPage.java                 # Game menu (single-player functional, multiplayer UI only)
│   ├── PersonalDetailsPage.java      # User profile
│   ├── SinglePlayerGameDetailsPage.java  # Game history
│   └── GameOutcomePage.java          # Game result display
├── game/                             # Game Logic (MVC Model & Controller)
│   ├── gameRunner/
│   │   ├── SinglePlayerRunner/       # Single-player game execution
│   │   └── MultiPlayerRunner/        # Multiplayer (NOT IMPLEMENTED)
│   ├── pacmanGames/                  # Game entity models
│   │   └── imagesLoader/             # Image asset loading
│   └── sprites/                      # Game sprite definitions
├── player/
│   └── Player.java                   # Static player session data
├── behaviors/                        # Game entity behaviors
├── customExceptions/
│   └── gameExceptions/               # Game-specific exceptions
├── utils/
│   └── requestUtilities/             # HTTP utilities for REST calls
├── validations/                      # Input validation (Email, Password, Username)
├── widgetFactories/                  # UI component factories
└── logs/                             # Application logs
```

### Backend (Spring Boot)

```
Backend/src/main/java/com/example/pacmazeAdventures/
├── controller/                       # REST Endpoints
│   ├── SignUpController.java         # POST /api/users/signup
│   ├── SignInController.java         # POST /api/users/signin
│   ├── UserDetailsController.java    # GET /api/users/my-details
│   ├── SinglePlayerGameController.java   # Single-player game endpoints
│   └── MultiplayerGameController.java    # Multiplayer endpoints (stub)
├── entity/                           # JPA Entities / Models
│   ├── User.java                     # User entity with authentication
│   ├── SinglePlayerGame.java         # Single-player game records
│   ├── MultiplayerGame.java          # Multiplayer game records
│   ├── Room.java                     # Multiplayer room entity
│   └── DesiredRoom.java              # Room request entity
├── service/                          # Business Logic Layer
│   ├── UserService.java
│   ├── SinglePlayerGameService.java
│   └── MultiplayerGameService.java
├── repository/                       # Data Access Layer (Spring Data JPA)
│   ├── UserRepository.java
│   ├── SinglePlayerGameRepository.java
│   ├── MultiplayerGameRepository.java
│   ├── RoomRepository.java
│   └── DesiredRoomRepository.java
├── DTO/                              # Data Transfer Objects
│   └── Request/Response DTOs
├── config/                           # Configuration Classes
│   └── Security configuration
└── Backend.java                      # Spring Boot main application class
```

---

## Technology Stack

### Frontend
- **Language**: Java 17+
- **GUI Framework**: Swing
- **HTTP Client**: Java HttpURLConnection (via HttpUtil wrapper)
- **JSON Processing**: org.json library

### Backend
- **Language**: Java 17-22
- **Framework**: Spring Boot 3.4.1
- **ORM**: Spring Data JPA with Hibernate
- **Security**: Spring Security with BCryptPasswordEncoder
- **Database**: MySQL
- **Build Tool**: Maven 3.8+
- **Testing**: JUnit, Mockito

### Database
- **MySQL 5.7+**
- **Database Name**: `pacmaze_adventures`
- **Tables**: users, single_player_games, multiplayer_games, rooms, desired_rooms

---

## System Requirements

### Frontend
- Java 17 or higher
- 100MB free disk space
- 512MB RAM minimum

### Backend
- Java 17 or higher (tested with 22)
- Maven 3.8 or higher
- MySQL 5.7 or higher
- 500MB free disk space
- 1GB RAM minimum

### Network
- TCP port 8080 (backend server)
- HTTP connectivity between frontend and backend

---

## Installation & Setup

### Prerequisites
1. Install Java 17+
2. Install Maven 3.8+
3. Install MySQL 5.7+
4. Create MySQL database: `pacmaze_adventures`

### Backend Setup

1. Navigate to Backend directory:
   ```bash
   cd Backend
   ```

2. Update database credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/pacmaze_adventures?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=YOUR_PASSWORD
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

   Or:
   ```bash
   java -jar target/Backend-0.0.1-SNAPSHOT.jar
   ```

   The server will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to Frontend directory:
   ```bash
   cd Frontend
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   java -cp target/Frontend-1.0.jar App
   ```

   Or compile and run directly:
   ```bash
   javac -cp "lib/*" src/*.java
   java -cp "lib/*:bin" App
   ```

---

## REST API Endpoints

### Authentication & User Management

#### Sign Up
- **Endpoint**: `POST /api/users/signup`
- **Request Body**:
  ```json
  {
    "username": "string",
    "email": "string@example.com",
    "password": "string"
  }
  ```
- **Response**: User creation confirmation

#### Sign In
- **Endpoint**: `POST /api/users/signin`
- **Request Body**:
  ```json
  {
    "email": "string@example.com",
    "password": "string"
  }
  ```
- **Response**: Login confirmation

#### Get User Details
- **Endpoint**: `GET /api/users/my-details?email={email}`
- **Response**:
  ```json
  {
    "username": "string",
    "email": "string@example.com",
    "createdAt": "timestamp"
  }
  ```

### Single-Player Game

#### Save Game Progress
- **Endpoint**: `POST /api/single-player-games/save-game`
- **Request Body**:
  ```json
  {
    "playerEmail": "string@example.com",
    "score": "integer",
    "level": "integer",
    "gameTime": "long"
  }
  ```

#### Fetch Game History
- **Endpoint**: `GET /api/single-player-games/player/{email}`
- **Response**: List of all saved games for the player

### Multiplayer Game (NOT IMPLEMENTED)

#### Create Room
- **Endpoint**: `POST /api/multiplayer/create-room`
- **Status**: Endpoint exists but functionality incomplete

#### Join Room
- **Endpoint**: `POST /api/multiplayer/join-room`
- **Status**: Endpoint exists but functionality incomplete

#### Send Move
- **Endpoint**: `POST /api/multiplayer/send-move`
- **Status**: Endpoint exists but functionality incomplete

#### Receive Move
- **Endpoint**: `GET /api/multiplayer/receive-move`
- **Status**: Endpoint exists but functionality incomplete

---

## MVC Architecture Details

### Model Layer
- **Backend**: JPA Entities (User, SinglePlayerGame, MultiplayerGame, etc.)
- **Frontend**: Player singleton (session data), Game entities

### View Layer
- **Frontend**: Java Swing components and pages
- All UI rendering through Swing frameworks

### Controller Layer
- **Backend**: Spring REST Controllers handling HTTP requests
- **Frontend**: Page classes managing user interactions and navigation

### Data Flow
```
Frontend GUI (View) 
    → User Action 
    → Page Controller 
    → HTTP Request 
    → Spring Controller (Backend) 
    → Service Layer 
    → JPA Repository 
    → MySQL Database 
    → Response → Frontend Display
```

---

## Input Validation

### Username Validation
- Length: 3-20 characters
- Characters: Alphanumeric and underscores only
- No spaces or special characters

### Email Validation
- Must be valid email format
- RFC 5322 standard compliance

### Password Validation
- Minimum length: 8 characters
- Must contain uppercase, lowercase, numbers, and special characters
- Encrypted using BCryptPasswordEncoder before storage

---

## Known Limitations & TODO

### Not Implemented (See Remaining.txt)
1. **Multiplayer Gameplay**
   - WebSocket/Socket communication for real-time synchronization
   - Room management system
   - Player role and color assignment
   - Real-time move broadcasting

2. **Game Features**
   - Map variation
   - Movement controls refinement
   - Ghost AI improvements
   - Leaderboard system

3. **Security**
   - JWT token-based authentication (currently uses session-based)
   - HTTPS support
   - Input sanitization enhancements

4. **Testing**
   - Comprehensive unit tests
   - Integration tests for API endpoints
   - Frontend GUI testing

---

## Database Schema Overview

### Users Table
```sql
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL (BCrypt encrypted),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Single Player Games Table
```sql
CREATE TABLE single_player_games (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  score INT,
  level INT,
  game_time LONG,
  played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### Multiplayer Games Table
```sql
CREATE TABLE multiplayer_games (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  room_id BIGINT,
  score INT,
  winner_id BIGINT,
  played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (winner_id) REFERENCES users(id)
);
```

---

## Configuration

### Backend Configuration (application.properties)

```properties
# Application name
spring.application.name=user-signup

# MySQL Database
spring.datasource.url=jdbc:mysql://localhost:3306/pacmaze_adventures?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=lLfD))1EO2znaL!

# Hibernate Configuration
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true

# Server Configuration
server.port=8080
server.address=0.0.0.0
```

---

## Troubleshooting

### Backend Connection Error
- **Issue**: Frontend cannot connect to backend
- **Solution**: 
  - Ensure backend is running on `http://localhost:8080`
  - Check firewall settings
  - Verify MySQL database is running and credentials are correct

### Database Connection Failed
- **Issue**: Backend cannot connect to MySQL
- **Solution**:
  - Verify MySQL service is running
  - Check database credentials in application.properties
  - Ensure database `pacmaze_adventures` exists
  - Check MySQL user permissions

### Authentication Failed
- **Issue**: Login fails with valid credentials
- **Solution**:
  - Ensure user account exists in database
  - Check that password hashing is correct
  - Verify email format matches registration

### GUI Not Displaying
- **Issue**: Frontend window doesn't appear
- **Solution**:
  - Ensure Java 17+ is installed
  - Check for console errors
  - Verify Swing libraries are available

---

## Future Enhancements

1. **Multiplayer Support**
   - Implement WebSocket for real-time communication
   - Add room-based game sessions
   - Implement player role assignment
   - Add real-time move synchronization

2. **Game Features**
   - Multiple game maps
   - Power-up system
   - Difficulty levels
   - Global leaderboard

3. **Security**
   - JWT authentication
   - HTTPS/SSL support
   - Rate limiting
   - Input sanitization

4. **UI/UX**
   - Enhanced graphics
   - Sound effects
   - Animations
   - Mobile version (if applicable)

5. **Performance**
   - Game state optimization
   - Database query optimization
   - Caching layer implementation

---

## File Descriptions

- **[Remaining.txt](Remaining.txt)**: Outstanding tasks and implementation roadmap
- **[Frontend/README.md](Frontend/README.md)**: Frontend-specific setup instructions
- **[Frontend/Steps.txt](Frontend/Steps.txt)**: Immediate game adjustments needed

---

## Development Notes

### MVC Pattern Implementation
- **Model**: Database entities and business logic
- **View**: Java Swing UI components
- **Controller**: REST controllers and page controllers

### Frontend-Backend Communication
- HTTP-based REST API using JSON
- All requests include player email for user identification
- Synchronous request-response pattern

### Code Organization
- Clear separation of concerns
- Factory pattern for UI component creation
- Utility classes for common operations (HTTP requests, validation)
- Custom exceptions for error handling

---

## Contributors

- **Repository**: shah541-g/PacMan-SpringBoot
- **License**: Not specified
- **Status**: Active Development

---

## Getting Help

For issues, questions, or contributions:
1. Check [Remaining.txt](Remaining.txt) for known tasks
2. Review error messages in console/logs
3. Verify all prerequisites are installed
4. Check database connection settings

---

## License

Currently no license specified. Please add appropriate license file if needed.

---

**Last Updated**: 2025
**Project Status**: Single-player fully functional. Multiplayer in planning phase.

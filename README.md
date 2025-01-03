

# **PacMaze Adventures**

## **Overview**
PacMaze Adventures is a multiplayer and single-player Pacman-inspired game that allows players to engage in exciting gameplay either alone or with others. The system is composed of a **frontend desktop client** and a **backend server**. It leverages Spring Boot for the backend and Java Swing for the client, creating a seamless and dynamic gaming experience.

---

## **Features**
### **Multiplayer Mode**
- **Room Management**: Players can create and join virtual rooms for multiplayer games.
- **Role Assignment**: Assigns roles and Pacman colors to players.
- **Real-time Synchronization**: Broadcasts player movements in real-time within the room.

### **Single-Player Mode**
- Save and resume game progress.
- Record and track high scores.

### **User Management**
- Secure user registration and authentication.
- Persistent user sessions with sign-in and sign-out functionalities.

### **System Architecture**
The system uses a client-server architecture:
1. **Frontend**: Java Swing-based desktop application for the client interface.
2. **Backend**: Spring Boot-powered REST API for managing game logic and user data.

---

## **System Requirements**
### **Frontend**
- Java 17+
- Swing Framework

### **Backend**
- Java 17+
- Maven 3.8+
- MySQL 

---

## **Directory Structure**
```plaintext
PacMazeAdventures/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── com/     
│   │   │   │   │   ├── example/     
│   │   │   │   │   │   ├── pacmazeAdventures/     
│   │   │   │   │   │   │   ├── controller/      # REST Controllers
│   │   │   │   │   │   │   ├── service/         # Business Logic
│   │   │   │   │   │   │   ├── repository/      # Data Access Logic
│   │   │   │   │   │   │   ├── entity/          # Data entities
│   │   │   │   │   │   │   └── config/          # Security and App Config
│   │   │   └── resources/
│   │   │       ├── application.properties       # App Configuration
│   │   │       └── static/                      # Static Assets
│   ├── pom.xml                                  # Maven Config
│   └── README.md                                # Backend-specific Documentation
├── frontend/
│   ├── src/
│   │   ├── behaviors/
|   |   ├── customExceptions/     
│   │   │   ├── gameExceptions/                  # REST Controllers
│   │   ├── game/                         # Swing GUI Classes
|   │   │   ├── gameRunner/                    # Game Logic
|   │   │   └── pacmanGames/                      # Data Models
│   │   └── resources/
│   │   │       └── images/          # Game Assets
│   ├── pom.xml                      # Maven Config
│   └── README.md                    # Frontend-specific Documentation
└── README.md                        # System Overview
```

---

## **Getting Started**

### **Backend**
1. Navigate to the `backend/` directory.
2. Build and run the server:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### **Frontend**
1. Navigate to the `frontend/` directory.
2. Build and run the desktop client:
   ```bash
   mvn clean install
   java -jar target/frontend-1.0.jar
   ```

---

## **Endpoints**
### **Backend API**
#### **Multiplayer**
- `POST /multiplayer/create-room`: Create a new game room.
- `POST /multiplayer/join-room`: Join an existing game room.
- `POST /multiplayer/send-move`: Send player movement.
- `GET /multiplayer/receive-move`: Receive synchronized movements.

#### **Single Player**
- `POST /singleplayer/save-game`: Save game progress.
- `GET /singleplayer/get-games`: Fetch games by player email.

#### **Authentication**
- `POST /auth/signup`: Register a new user.
- `POST /auth/signin`: Log in.
- `POST /auth/signout`: Log out.

#### **User Management**
- `GET /user/{email}`: Fetch user details.

---

## **UML Class Diagram**
```plaintext
(see full class diagram in `docs/plantuml/class-diagram.puml`)
```

---

## **Technologies Used**
- **Backend**: Spring Boot, JPA, BCryptPasswordEncoder
- **Frontend**: Java Swing
- **Database**: PostgreSQL (optional)
- **Testing**: JUnit, Mockito

---

## **Testing**
Run backend tests:
```bash
mvn test
```

Run frontend tests (if applicable):
```bash
mvn test
```

---

## **Future Improvements**
- Implement a global leaderboard for multiplayer mode.
- Add AI opponents for single-player games.
- Enhance security using JWT for session management.
- Mobile version for enhanced accessibility.

---

Enjoy the adventures in PacMaze! For contributions or support, refer to the CONTRIBUTING.md file or open an issue in the repository. 🎮

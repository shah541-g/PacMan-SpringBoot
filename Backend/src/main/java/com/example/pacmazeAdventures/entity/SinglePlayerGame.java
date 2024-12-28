package com.example.pacmazeAdventures.entity;

import jakarta.persistence.*;


import java.sql.Timestamp;


@Entity
@Table(name = "single_player_games")
public class SinglePlayerGame {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "game_id")
    private int gameId;

    @Column(name = "player_email", nullable = false,length = 25)
    private String playerEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    public enum Status {
        WIN,
        LOSE
    }

    public int getGameId() {
        return gameId;
    }

    public String getPlayerEmail() {
        return playerEmail;
    }

    public Status getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }


    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "SinglePlayerGame{" +
                "gameId=" + gameId +
                ", playerEmail='" + playerEmail + '\'' +
                ", status=" + status +
                ", score=" + score +
                ", timestamp=" + timestamp +
                '}';
    }

}

package com.example.pacmazeAdventures.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "multiplayer_games")
public class MultiplayerGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameID;

    @Column(nullable = false, name = "room_key")
    private String roomKey;

    @Column(nullable = false, name = "starting_time")
    private LocalDateTime startingTime;

    @Column(nullable = false, name = "player1_email")
    private String player1Email;

    @Column(nullable = false, name = "player2_email")
    private String player2Email;

    @Column(nullable = false, name = "player1_score")
    private int player1Score;

    @Column(nullable = false, name = "player2_score")
    private int player2Score;

    @Column(length = 255, nullable = true, name = "winner")
    private String winner;
    @Column(length = 255, nullable = true, name = "loser")
    private String loser;

    // Getters and Setters
    public Long getGameID() {
        return gameID;
    }

    public void setGameID(Long gameID) {
        this.gameID = gameID;
    }

    public String getRoomKey() {
        return roomKey;
    }

    public void setRoomKey(String roomKey) {
        this.roomKey = roomKey;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public String getPlayer1Email() {
        return player1Email;
    }

    public void setPlayer1Email(String player1Email) {
        this.player1Email = player1Email;
    }

    public String getPlayer2Email() {
        return player2Email;
    }

    public void setPlayer2Email(String player2Email) {
        this.player2Email = player2Email;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }
}

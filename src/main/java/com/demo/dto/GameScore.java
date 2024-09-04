package com.demo.dto;

import lombok.Data;

@Data
public class GameScore implements Comparable<GameScore> {

    private String playerName;

    private int score;

    @Override
    public int compareTo(GameScore other) {
        return Integer.compare(other.score, this.score);
    }

    public GameScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

}
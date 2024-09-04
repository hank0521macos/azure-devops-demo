package com.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.dto.GameScore;

@Service
public class SnakeGameService {

    private List<GameScore> highScores = new ArrayList<>();

    public List<GameScore> saveScore(GameScore score) {
        highScores.add(score);
        Collections.sort(highScores);
        if (highScores.size() > 10) {
            highScores = highScores.subList(0, 10);
        }
        return highScores;
    }

    public List<GameScore> getHighScores() {
        return highScores;
    }

    public void resetHighScores() {
        highScores.clear();
    }

}

package com.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.demo.dto.GameScore;

class SnakeGameServiceTest {

    private SnakeGameService snakeGameService;

    @BeforeEach
    void setUp() {
        snakeGameService = new SnakeGameService();
    }

    @Test
    void testSaveScore() {
        GameScore score1 = new GameScore("Player1", 100);
        GameScore score2 = new GameScore("Player2", 200);

        List<GameScore> result1 = snakeGameService.saveScore(score1);
        assertEquals(1, result1.size());
        assertEquals(score1, result1.get(0));

        List<GameScore> result2 = snakeGameService.saveScore(score2);
        assertEquals(2, result2.size());
        assertEquals(score2, result2.get(0)); // 高分應該在前面
        assertEquals(score1, result2.get(1));
    }

    @Test
    void testSaveScoreLimit() {
        for (int i = 0; i < 15; i++) {
            snakeGameService.saveScore(new GameScore("Player" + i, i * 10));
        }

        List<GameScore> highScores = snakeGameService.getHighScores();
        assertEquals(10, highScores.size());
        assertEquals(140, highScores.get(0).getScore());
        assertEquals(50, highScores.get(9).getScore());
    }

    @Test
    void testGetHighScores() {
        GameScore score1 = new GameScore("Player1", 100);
        GameScore score2 = new GameScore("Player2", 200);
        snakeGameService.saveScore(score1);
        snakeGameService.saveScore(score2);

        List<GameScore> highScores = snakeGameService.getHighScores();
        assertEquals(2, highScores.size());
        assertEquals(score2, highScores.get(0));
        assertEquals(score1, highScores.get(1));
    }

    @Test
    void testResetHighScores() {
        snakeGameService.saveScore(new GameScore("Player1", 100));
        snakeGameService.saveScore(new GameScore("Player2", 200));

        snakeGameService.resetHighScores();

        List<GameScore> highScores = snakeGameService.getHighScores();
        assertTrue(highScores.isEmpty());
    }

}

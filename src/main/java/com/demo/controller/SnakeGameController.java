package com.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.dto.GameScore;
import com.demo.service.SnakeGameService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SnakeGameController {

    @Autowired
    private SnakeGameService snakeGameService;

    @GetMapping("/")
    public String index(Model model) {
        List<GameScore> highScores = snakeGameService.getHighScores();
        model.addAttribute("highScores", highScores);
        return "index";
    }

    @GetMapping("/game")
    public String game(@RequestParam String playerName, Model model) {
        model.addAttribute("playerName", playerName);
        return "game";
    }

    @PostMapping("/saveScore")
    @ResponseBody
    public List<GameScore> saveScore(@RequestBody GameScore score) {
        log.info("score = {}", score);
        return snakeGameService.saveScore(score);
    }

    @GetMapping("/highScores")
    @ResponseBody
    public List<GameScore> getHighScores() {
        return snakeGameService.getHighScores();
    }

    @PostMapping("/resetHighScores")
    @ResponseBody
    public Map<String, Boolean> resetHighScores() {
        snakeGameService.resetHighScores();
        return Collections.singletonMap("success", true);
    }

    @GetMapping("/api/test")
    @ResponseBody
    public Map<String, String> testApi() {
        return Collections.singletonMap("message", "API is working correctly");
    }

}
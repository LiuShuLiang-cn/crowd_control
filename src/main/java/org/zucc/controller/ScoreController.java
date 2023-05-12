package org.zucc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zucc.entity.Score;
import org.zucc.service.ScoreService;


@Controller
@RequestMapping(("/score"))
@ResponseBody
public class ScoreController {
    ScoreService scoreService;

    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/{systemName}/{roleTopic}/{statue}")
    public Score getScore(@PathVariable("systemName") String systemName,
                          @PathVariable("roleTopic") String roleTopic,
                          @PathVariable("statue") int statue) {
        return scoreService.Composition(systemName, roleTopic, statue);
    }
    @GetMapping("/current/{systemName}/{roleTopic}")
    public Score UserScore(@PathVariable("systemName")String systemName,
                           @PathVariable("roleTopic")String roleTopic){
        return scoreService.getCurrentScore(systemName,roleTopic);
    }

    @GetMapping("/update")
    public Score UserScore(@RequestParam("systemName") String systemName,
                           @RequestParam("roleTopic") String roleTopic,
                           @RequestParam("score") int score) {
        return scoreService.setCurrentScore(systemName, roleTopic, score);
    }
}

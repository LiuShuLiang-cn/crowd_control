package org.zucc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zucc.entity.Score;

public interface ScoreService extends IService<Score> {
    void initScore(String systemName);

    void setLoginRole(String systemName, String role, String userName);

    Score Composition(String systemName, String roleTopic, int statue);

    Score getCurrentScore(String systemName, String roleTopic);

    Score setCurrentScore(String systemName, String roleTopic, int fraction);
}

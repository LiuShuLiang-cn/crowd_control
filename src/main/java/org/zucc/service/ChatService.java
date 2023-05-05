package org.zucc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zucc.entity.Chat;

import java.util.List;

public interface ChatService extends IService<Chat> {
    void HandleMsg(Chat chat, String toRoleTopic, String systemName);

    List<Chat> getAllTODO(String systemName,String roleTopic);

    String finishTODO(int id);
}

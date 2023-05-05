package org.zucc.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.zucc.dao.ChatDao;
import org.zucc.entity.Chat;
import org.zucc.service.ChatService;

import java.util.List;

@Service
@Slf4j
public class ChatServiceImpl extends ServiceImpl<ChatDao, Chat> implements ChatService {

    private ChatDao chatDao;

    private SimpMessagingTemplate template;
    @Autowired
    public void setChatDao(ChatDao chatDao) {
        this.chatDao = chatDao;
    }
    @Autowired
    public void setTemplate(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void HandleMsg(Chat chat, String toRoleTopic, String systemName) {
        /*
        处理信息的类型并进行转发
         */
        chatDao.insert(chat);
        template.convertAndSend("/topic/" + systemName + toRoleTopic, chat);
    }

    @Override
    public List<Chat> getAllTODO(String systemName, String roleTopic) {
        List<Chat> chats = chatDao.getNoTODO(systemName, roleTopic);
        log.info("系统<"+systemName +">中角色<" + roleTopic + ">有" + chats.size() + "条记录未完成");
        return chats;
    }

    @Override
    public String finishTODO(int id) {
        /*
        完成该项任务
         */
        try {
            Chat chat = chatDao.selectById(id);
            chat.setStatue(1);
            if (chatDao.updateById(chat) == 1) {
                log.info("完成任务: id=" + chat.getId());
                return "恭喜你！完成了一条任务";
            }else {
                log.error("update command error!");
            }
        } catch (NullPointerException e) {
            log.error("找不到id为"+id+"的命令");
        }
        return "对不起！完成任务失败";
    }
}

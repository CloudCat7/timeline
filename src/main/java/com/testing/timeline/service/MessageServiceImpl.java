package com.testing.timeline.service;

import com.testing.timeline.domain.Message;
import com.testing.timeline.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    /**
     * 加载消息
     * @param i 最新消息需要加载到第几条
     * @param j 最旧消息需要加载到第几条
     * */
    @Override
    public List<Message> getMessages(int i, int j){

        List<Message> allMessages = messageRepository.findAll();
        Collections.reverse(allMessages);
        List<Message> moreMessages = new ArrayList<>();
        moreMessages.addAll(allMessages.subList(i,Math.min(j,allMessages.size())));
        return moreMessages;
    }

    /**
     * 用于发送消息（暂时只是测试用）
     * */
    @Override
    public void postMessage(String userName, String content, String imgNum){

        Date date = new Date();
        Message message = new Message(userName,content,date,imgNum);
        messageRepository.save(message);

    }

}

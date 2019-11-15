package com.testing.timeline.service;

import com.testing.timeline.domain.Message;

import java.util.List;

public interface MessageService {

    List<Message> getMessages(int i, int j);

    void postMessage(String userName, String content, String imgNum);

}

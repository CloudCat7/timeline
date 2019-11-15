package com.testing.timeline.service;

import com.testing.timeline.domain.Message;
import com.testing.timeline.repository.MessageRepository;
import org.junit.Before;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private MessageService messageService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMessages() {

        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();

        List<Message> messageList = new ArrayList<>();
        Message m1 = new Message("user_1","sample_1",d1,"1");
        Message m2 = new Message("user_2","sample_2",d2,"2");
        Message m3 = new Message("user_3","sample_3",d3,"3");
        messageList.add(m1);
        messageList.add(m2);
        messageList.add(m3);
        when(messageRepository.findAll()).thenReturn(messageList);

        List<Message> resultList1 = messageService.getMessages(0, 2);
        List<Message> resultList2 = messageService.getMessages(0, 5);

        verify(messageRepository,times(1)).findAll();
        verifyNoMoreInteractions(messageRepository);

        assertAll(
                ()->assertEquals(2,resultList1.size(),"参数为2，应该只返回两个Message"),
                ()->assertEquals("user_3",resultList1.get(0).getUserName(),"List反转失败"),
                ()->assertEquals("sample_3",resultList1.get(0).getContent()),
                ()->assertEquals(d3,resultList1.get(0).getSendTime()),
                ()->assertEquals("user_2",resultList1.get(1).getUserName()),
                ()->assertEquals("sample_2",resultList1.get(1).getContent()),
                ()->assertEquals(d2,resultList1.get(1).getSendTime()),
                ()->assertEquals(3,resultList2.size(),"参数为5，应该返回list的实际长度")
        );
    }
}
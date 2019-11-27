package com.testing.timeline.repository;

import com.testing.timeline.domain.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@AutoConfigureTestDatabase(replace = NONE)
class MessageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private MessageRepository repo;

    private Message message_1;
    private Date d1 = new Date();

    @BeforeEach
    public void init() {
        message_1 = new Message.MessageBuilder().username("user_1")
                .content("test_text_1")
                .sendTime(d1)
                .image("1")
                .build();
    }

    @AfterEach
    public void cleanup() { entityManager.clear(); }

    @Test
    public void testFindAllMessage() {

        entityManager.persist(message_1);
        Iterable<Message>messages = repo.findAll();

        int count = 0;
        for(Message message : messages) {
            assertEquals("user_1", message.getUserName());
            assertEquals("test_text_1", message.getContent());
            assertEquals(d1,message.getSendTime());
            assertEquals("1",message.getImage());
            count++;
        }
        assertEquals(1, count);
    }

}
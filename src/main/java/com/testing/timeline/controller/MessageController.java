package com.testing.timeline.controller;

import com.testing.timeline.domain.Message;
import com.testing.timeline.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@EnableAutoConfiguration
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    private int i = 0;
    private int j = 5;

    /**
     * 更新页面（获取最新动态）
     * */
//    public List<Message> update(){
//    @GetMapping("/update")
    @GetMapping(value={"","/update","index.html"})
    public ModelAndView update(Model model){
        List<Message> messageList;
//        messageService.postMessage();
        i = 0;
//        j = 5;
        messageList = messageService.getMessages(i,j);
        model.addAttribute("messageList",messageList);
        return new ModelAndView("index.html","messageModel",model);
    }
    /*public ModelAndView update(Model model){
        List<Message> messageList;
//        messageService.postMessage();
        i = 0;
//        j = 5;
        messageList = messageService.getMessages(i,j);
        model.addAttribute("messageList",messageList);
        return new ModelAndView("/messages","messageModel",model);
    }*/

/*    @GetMapping("/test")
    public ModelAndView justTest(Model model){
        String testing;
        testing = "testing1";
        model.addAttribute("testing",testing);
        return new ModelAndView("/test","testModel",model);
    }*/

    /**
     * 显示更多更早消息（一次加载5条）
     */
    //    public List<Message> load(Model model){
    @GetMapping("/load")
    public ModelAndView load(Model model){
        List<Message> messageList;
        j += 5;
        messageList = messageService.getMessages(i,j);
        model.addAttribute("messageList",messageList);
        return new ModelAndView("index.html","messageModel",model);
    }

    /**
     * 插入一条消息
     * */
    @GetMapping("/post")
    public ModelAndView post(Model model){
/*        ArrayList<String> imgNumList = new ArrayList<>();
        imgNumList.add("2");*/
        Random r = new Random();
        int imgNum = r.nextInt(3)+1;
        messageService.postMessage("test","==== TESTING ====",String.valueOf(imgNum));
        i++;
        j++;
        List<Message> messageList;
        messageList = messageService.getMessages(i,j);
        model.addAttribute("messageList",messageList);
        return new ModelAndView("index.html","messageModel",model);
    }

    /*public void post(){
*//*        ArrayList<String> imgNumList = new ArrayList<>();
        imgNumList.add("2");*//*
        messageService.postMessage("test","==== TESTING ====","2");
        i++;
        j++;
    }*/

}

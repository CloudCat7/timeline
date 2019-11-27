package com.testing.timeline.domain;

import javax.persistence.*;
import java.util.Date;


@Table(name = "timeline")
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mid;
    private String userName;
    private String content;
    private Date sendTime;
//    private List<String> images;
    private String image;

    public Message(){

    }

    public Message(String userName, String content, Date sendTime) {
        this.userName = userName;
        this.content = content;
        this.sendTime = sendTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /*public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> imgNumList) {
        ArrayList<String> pathList = new ArrayList<>();
        for(String imgNum : imgNumList){
            pathList.add("images/" + imgNum + ".jpg");
        }
        this.images = pathList;
    }*/

    public String getImage() {
        return image;
    }

    public void setImage(String imgNum) {
        this.image = "images/" + imgNum +".jpg";
    }

    //新增
    public static class MessageBuilder{
        private String username;
        private String content;
        private  Date sendTime;

        public MessageBuilder username(String name) {
            this.username = name;
            return this;
        }

        public MessageBuilder content(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder sendTime(Date sendTime) {
            this.sendTime = sendTime;
            return this;
        }
        public Message build() { return new Message(username,content,sendTime);}
    }
}

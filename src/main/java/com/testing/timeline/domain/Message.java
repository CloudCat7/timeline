package com.testing.timeline.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
}

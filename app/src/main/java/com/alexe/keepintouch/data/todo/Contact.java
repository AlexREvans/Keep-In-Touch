package com.alexe.keepintouch.data.todo;

import android.content.Intent;

import java.util.Date;

public class Contact {

    private String name;
    private String source;
    private Date lastContacted;
    private String picture;
    private String lastMessage;
    private String id;

    public static abstract class Respond {
        public abstract Intent getIntent(String message);
    }

    public Respond getTalkToMe() {
        return talkToMe;
    }

    public void setTalkToMe(Respond talkToMe) {
        this.talkToMe = talkToMe;
    }

    private Respond talkToMe;

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getLastContacted() {
        return lastContacted;
    }

    public void setLastContacted(Date lastContacted) {
        this.lastContacted = lastContacted;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

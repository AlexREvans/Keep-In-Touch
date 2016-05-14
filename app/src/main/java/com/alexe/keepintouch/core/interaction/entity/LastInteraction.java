package com.alexe.keepintouch.core.interaction.entity;

import com.alexe.keepintouch.core.interaction.source.InteractionSource;

import java.util.Date;

public class LastInteraction {

    private Contact contact;
    private Date date;
    private String sourceName;
    private InteractionSource.Responder responder;

    public LastInteraction(Contact contact, Date date, InteractionSource source) {
        this.contact = contact;
        this.date = date;
        this.sourceName = source.getName();
        this.responder = source.getResponder();
    }

    public Contact getContact() {
        return contact;
    }

    public Date getDate() {
        return date;
    }

    public String getSourceName() {
        return sourceName;
    }

    public InteractionSource.Responder getResponder() {
        return responder;
    }

}

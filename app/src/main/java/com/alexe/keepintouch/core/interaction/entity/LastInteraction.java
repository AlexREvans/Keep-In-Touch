package com.alexe.keepintouch.core.interaction.entity;

import com.alexe.keepintouch.core.interaction.source.InteractionSource;

import java.util.Date;

public class LastInteraction {

    private Contact contact;
    private Date date;
    private SourceDetails interactionSource;

    public LastInteraction(Contact contact, Date date, SourceDetails source) {
        this.contact = contact;
        this.date = date;
    }

    public Contact getContact() {
        return contact;
    }

    public Date getDate() {
        return date;
    }

}

package com.alexe.keepintouch.core.interaction.entity;

import com.alexe.keepintouch.core.contact.Contact;

import java.util.Date;

public class LastInteraction {

    private Contact contact;
    private Date date;
    private SourceDetails interactionSourceDetails;

    public LastInteraction(Contact contact, Date date, SourceDetails source) {
        this.contact = contact;
        this.date = date;
        this.interactionSourceDetails = source;
    }

    public Contact getContact() {
        return contact;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SourceDetails getInteractionSourceDetails() {
        return interactionSourceDetails;
    }

    public void setInteractionSourceDetails(SourceDetails interactionSourceDetails) {
        this.interactionSourceDetails = interactionSourceDetails;
    }
}

package com.alexe.keepintouch.data.interaction.source.sms;

import android.content.Intent;
import android.net.Uri;

import com.alexe.keepintouch.core.interaction.entity.SourceDetails;

public class SmsSourceDetails implements SourceDetails {

    private String tel;
    private String lastMessage;

    public SmsSourceDetails(String tel) {
        this.tel = tel;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public String getName() {
        return "SMS";
    }

    @Override
    public Responder getResponder(String message) {
        return new Responder() {
            @Override
            public Intent getIntent(String message) {
                Intent respond = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", tel, null));
                respond.putExtra("sms_body", message);
                return respond;
            }
        };
    }
}

package com.alexe.keepintouch.core.interaction.source.sms;

import com.alexe.keepintouch.core.interaction.entity.LastInteraction;
import com.alexe.keepintouch.core.interaction.source.InteractionSource;

import java.util.Date;
import java.util.Map;

public class SmsInteractionSource implements InteractionSource {
    @Override
    public Map<String, LastInteraction> getLastInteractions(Date sinceTime) {
        return null;
    }

    @Override
    public String getName() {
        return "Text Message";
    }

    @Override
    public Responder getResponder() {
        return null;
    }
}

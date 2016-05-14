package com.alexe.keepintouch.core.interaction.source;

import com.alexe.keepintouch.core.LastInteraction;

import java.util.Date;
import java.util.Map;

public class SmsInteractionSource implements InteractionSource {
    @Override
    public Map<String, LastInteraction> getLastInteractions(Date sinceTime) {
        return null;
    }
}

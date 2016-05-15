package com.alexe.keepintouch.data.interaction.source.call;

import com.alexe.keepintouch.core.interaction.entity.LastInteraction;
import com.alexe.keepintouch.core.interaction.source.InteractionSource;

import java.util.Date;
import java.util.Map;

public class CallInteractionSource implements InteractionSource {
    @Override
    public Map<String, LastInteraction> getLastInteractions(Date sinceTime) {
        return null;
    }
}

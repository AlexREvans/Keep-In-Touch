package com.alexe.keepintouch.core.interaction.source;

import com.alexe.keepintouch.core.LastInteraction;

import java.util.Date;
import java.util.Map;

public interface InteractionSource {
    Map<String, LastInteraction> getLastInteractions(Date sinceTime);
}

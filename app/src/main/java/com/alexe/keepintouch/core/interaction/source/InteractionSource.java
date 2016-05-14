package com.alexe.keepintouch.core.interaction.source;

import android.content.Intent;

import com.alexe.keepintouch.core.interaction.entity.LastInteraction;

import java.util.Date;
import java.util.Map;

public interface InteractionSource {

    Map<String, LastInteraction> getLastInteractions(Date sinceTime);
    String getName();
    Responder getResponder();

    abstract class Responder {
        public abstract Intent getIntent(String message);
    }

}

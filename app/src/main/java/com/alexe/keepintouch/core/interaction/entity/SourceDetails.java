package com.alexe.keepintouch.core.interaction.entity;

import android.content.Intent;

import com.alexe.keepintouch.core.interaction.source.InteractionSource;

public interface SourceDetails {
    String getName();
    Responder getResponder();

    abstract class Responder {
        public abstract Intent getIntent(String message);
    }
}

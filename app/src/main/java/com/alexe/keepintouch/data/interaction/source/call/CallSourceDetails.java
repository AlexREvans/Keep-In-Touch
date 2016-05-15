package com.alexe.keepintouch.data.interaction.source.call;

import com.alexe.keepintouch.core.interaction.entity.SourceDetails;

public class CallSourceDetails implements SourceDetails {
    @Override
    public String getName() {
        return "Call";
    }

    @Override
    public Responder getResponder() {
        return null;
    }
}

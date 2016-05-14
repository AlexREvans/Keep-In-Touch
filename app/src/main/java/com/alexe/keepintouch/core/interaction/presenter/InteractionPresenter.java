package com.alexe.keepintouch.core.interaction.presenter;

import com.alexe.keepintouch.core.interaction.entity.LastInteraction;

import java.util.List;

public interface InteractionPresenter {
    List<LastInteraction> getLastInteractions();
    void setLastInteractions(List<LastInteraction> lastInteractions);
}

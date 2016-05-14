package com.alexe.keepintouch.core.interaction.presenter;

import com.alexe.keepintouch.core.interaction.entity.LastInteraction;

import java.util.List;

public class MockInteractionPresenter implements InteractionPresenter {

    public List<LastInteraction> interactions;

    public List<LastInteraction> getLastInteractions() {
        return interactions;
    }

    @Override
    public void setLastInteractions(List<LastInteraction> lastInteractions) {
        interactions = lastInteractions;
    }
}

package com.alexe.keepintouch.core;

import java.util.List;

public class MockInteractionPresenter implements InteractionPresenter {

    public List<LastInteraction> interactions;

    @Override
    public List<LastInteraction> getLastInteractions() {
        return interactions;
    }

    @Override
    public void setLastInteractions(List<LastInteraction> lastInteractions) {
        interactions = lastInteractions;
    }
}

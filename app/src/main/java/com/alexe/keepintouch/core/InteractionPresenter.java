package com.alexe.keepintouch.core;

import java.util.List;

public interface InteractionPresenter {
    List<LastInteraction> getLastInteractions();
    void setLastInteractions(List<LastInteraction> lastInteractions);
}

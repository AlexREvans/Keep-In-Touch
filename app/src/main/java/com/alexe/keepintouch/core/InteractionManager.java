package com.alexe.keepintouch.core;

import com.alexe.keepintouch.core.interaction.entity.LastInteraction;
import com.alexe.keepintouch.core.interaction.presenter.InteractionPresenter;
import com.alexe.keepintouch.core.interaction.source.InteractionSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractionManager {

    private InteractionPresenter presenter;
    private Map<String, InteractionSource> sources = new HashMap<>();

    public InteractionManager(InteractionPresenter presenter) {
        this.presenter = presenter;
    }

    public void populateLastInteractions(Date sinceTime) {

        Map<String, LastInteraction> interactions = new HashMap<>();

        for (InteractionSource interactionSource : sources.values()) {
            Map<String, LastInteraction> lastInteractionsFromSource = interactionSource.getLastInteractions(sinceTime);

            for (Map.Entry<String, LastInteraction> lastInteraction : lastInteractionsFromSource.entrySet()) {
                LastInteraction lastFromOtherSource = interactions.get(lastInteraction.getKey());

                if (lastFromOtherSource == null || (lastInteraction.getValue().getDate().after(lastFromOtherSource.getDate()))) {
                    interactions.put(lastInteraction.getKey(), lastInteraction.getValue());
                }
            }
        }

        List<LastInteraction> lastInteractionResults = new ArrayList(interactions.values());
        Collections.sort(lastInteractionResults, new Comparator<LastInteraction>() {
            @Override
            public int compare(LastInteraction lhs, LastInteraction rhs) {
                return lhs.getDate().compareTo(rhs.getDate());
            }
        });

        presenter.setLastInteractions(lastInteractionResults);
    }

    public void addInteractionSource(InteractionSource source) {
        sources.put(source.getClass().getCanonicalName(), source);
    }
}

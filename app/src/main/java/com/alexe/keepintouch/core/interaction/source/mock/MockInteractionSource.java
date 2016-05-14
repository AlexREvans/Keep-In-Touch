package com.alexe.keepintouch.core.interaction.source.mock;

import android.content.Intent;

import com.alexe.keepintouch.core.interaction.entity.Contact;
import com.alexe.keepintouch.core.interaction.entity.LastInteraction;
import com.alexe.keepintouch.core.interaction.source.InteractionSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MockInteractionSource implements InteractionSource {

    private Map<String, LastInteraction> lastInteractions = new HashMap<>();

    public MockInteractionSource() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        List<Date> dates = new ArrayList<>();

        cal.add(Calendar.DATE, -1);
        dates.add(cal.getTime());
        cal.add(Calendar.MONTH, -1);
        dates.add(cal.getTime());
        cal.add(Calendar.MONTH, -4);
        dates.add(cal.getTime());
        cal.add(Calendar.MONTH, -2);
        dates.add(cal.getTime());

        String[] names = new String[] {
                "Bob", "Joe", "Helen", "Chris"
        };

        for (int i = 0; i < dates.size(); ++i) {
            String name = names[i % names.length];
            lastInteractions.put(name, new LastInteraction(new Contact(), dates.get(i), this));
        }

    }

    @Override
    public Map<String, LastInteraction> getLastInteractions(Date sinceTime) {

        Map<String, LastInteraction> recent = new HashMap<>();
        for (Map.Entry<String, LastInteraction> lastInteraction : lastInteractions.entrySet()) {
            if (lastInteraction.getValue().getDate().after(sinceTime)) {
                recent.put(lastInteraction.getKey(), lastInteraction.getValue());
            }
        }

        return recent;
    }

    @Override
    public String getName() {
        return "Imaginary";
    }

    @Override
    public Responder getResponder() {
        return null;
    }
}

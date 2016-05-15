package com.alexe.keepintouch.core.interaction.source;

import com.alexe.keepintouch.core.contact.Contact;
import com.alexe.keepintouch.core.interaction.entity.LastInteraction;
import com.alexe.keepintouch.core.interaction.entity.SourceDetails;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockInteractionSource implements InteractionSource {

    private Map<String, LastInteraction> lastInteractions = new HashMap<>();

    private static final String KITTEN_URL = "http://placekitten.com/";

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

        String[] names = new String[]{
                "Al", "Bill", "Charlie", "Dom"
        };

        SourceDetails mockDetails = new SourceDetails() {
            @Override
            public String getName() {
                return "Mock";
            }

            @Override
            public Responder getResponder() {
                return null;
            }
        };

        for (int i = 0; i < dates.size(); ++i) {
            String name = names[i % names.length];
            int dim = 500 + i;

            lastInteractions.put(name, new LastInteraction(new Contact(name, name, KITTEN_URL + 500 + "/" + dim), dates.get(i), mockDetails));
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

}

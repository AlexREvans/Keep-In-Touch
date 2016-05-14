package com.alexe.keepintouch.core.interaction.source;

import com.alexe.keepintouch.core.LastInteraction;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MockInteractionSource implements InteractionSource {

    private Map<String, LastInteraction> lastInteractions = new HashMap<>();

    public MockInteractionSource() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        LastInteraction a = new LastInteraction();
        cal.add(Calendar.DATE, -1);
        a.setDate(cal.getTime());

        LastInteraction b = new LastInteraction();
        cal.add(Calendar.MONTH, -1);
        b.setDate(cal.getTime());

        LastInteraction c = new LastInteraction();
        cal.add(Calendar.MONTH, -4);
        c.setDate(cal.getTime());

        LastInteraction d = new LastInteraction();
        cal.add(Calendar.MONTH, -2);
        d.setDate(cal.getTime());

        lastInteractions.put("A", a);
        lastInteractions.put("B", b);
        lastInteractions.put("C", c);
        lastInteractions.put("D", d);
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

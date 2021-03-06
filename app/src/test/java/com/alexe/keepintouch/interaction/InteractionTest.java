package com.alexe.keepintouch.interaction;

import com.alexe.keepintouch.core.interaction.InteractionManager;
import com.alexe.keepintouch.core.interaction.entity.LastInteraction;
import com.alexe.keepintouch.core.interaction.presenter.MockInteractionPresenter;
import com.alexe.keepintouch.core.interaction.source.MockInteractionSource;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InteractionTest {

    private InteractionManager im;
    private MockInteractionPresenter ip;

    @Before
    public void setup() {
        ip = new MockInteractionPresenter();
        im = new InteractionManager(ip);
    }

    @Test
    public void getInteractionsBasic() {
        im.populateLastInteractions(sixMonthsAgo());
        List<LastInteraction> interactions = ip.getLastInteractions();
        assertThat(interactions.size(), is(0));
    }

    @Test
    public void getInteractionsMock() {
        im.addInteractionSource(new MockInteractionSource());
        im.populateLastInteractions(sixMonthsAgo());
        List<LastInteraction> interactions = ip.getLastInteractions();
        assertThat(interactions.size(), is(3));
    }

    @Test
    public void getInteractionsDuplicate() {
        im.addInteractionSource(new MockInteractionSource());
        im.addInteractionSource(new MockInteractionSource());
        im.populateLastInteractions(sixMonthsAgo());
        List<LastInteraction> interactions = ip.getLastInteractions();
        assertThat(interactions.size(), is(3));
    }

    public Date sixMonthsAgo() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -6);
        return cal.getTime();
    }

}

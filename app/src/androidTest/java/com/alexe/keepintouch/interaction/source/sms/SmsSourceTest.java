package com.alexe.keepintouch.interaction.source.sms;


import android.app.Application;
import android.test.ApplicationTestCase;

import com.alexe.keepintouch.core.interaction.InteractionManager;
import com.alexe.keepintouch.core.interaction.entity.LastInteraction;
import com.alexe.keepintouch.core.interaction.presenter.MockInteractionPresenter;
import com.alexe.keepintouch.data.interaction.source.sms.SmsInteractionSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class SmsSourceTest extends ApplicationTestCase<Application> {
    public SmsSourceTest() {
        super(Application.class);
    }

    private InteractionManager im;
    private MockInteractionPresenter ip;

    @Before
    public void setup() {
        ip = new MockInteractionPresenter();
        im = new InteractionManager(ip);
    }

    @Test
    public void getSmsInteractions() {
        im.addInteractionSource(new SmsInteractionSource(getContext()));
        im.populateLastInteractions(sixMonthsAgo());
        List<LastInteraction> interactions = ip.getLastInteractions();
        assertThat(interactions.size(), is(not(0)));

        for (LastInteraction interaction : interactions) {
            assertThat(interaction.getInteractionSourceDetails(), is(not(null)));
        }
    }

    public Date sixMonthsAgo() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -6);
        return cal.getTime();
    }
}
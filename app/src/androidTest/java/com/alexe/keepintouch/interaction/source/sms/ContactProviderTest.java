package com.alexe.keepintouch.interaction.source.sms;


import android.app.Application;
import android.test.ApplicationTestCase;

import com.alexe.keepintouch.core.contact.Contact;
import com.alexe.keepintouch.data.contact.ContactProvider;

import org.junit.Test;

public class ContactProviderTest extends ApplicationTestCase<Application> {
    public ContactProviderTest() {
        super(Application.class);
    }

    @Test
    public void getByName() {
        ContactProvider cp = new ContactProvider(getContext());
        Contact c = cp.getContact("A");
        assertNotNull(c);
    }

    @Test
    public void getByTel() {
        ContactProvider cp = new ContactProvider(getContext());
        Contact c = cp.getContact("0");
        assertNotNull(c);
    }

    @Test
    public void getByEmail() {
        ContactProvider cp = new ContactProvider(getContext());
        Contact c = cp.getContact("gmail.com");
        assertNotNull(c);
    }

}

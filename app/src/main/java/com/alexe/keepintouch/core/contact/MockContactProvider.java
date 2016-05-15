package com.alexe.keepintouch.core.contact;

import java.util.ArrayList;
import java.util.List;

public class MockContactProvider implements ContactProvider {

    @Override
    public Contact getContact(String search) {
        return new Contact("DUMMY", "Test Contact", null);
    }
}

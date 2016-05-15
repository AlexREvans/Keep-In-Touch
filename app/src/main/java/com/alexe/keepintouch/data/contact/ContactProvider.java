package com.alexe.keepintouch.data.contact;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.alexe.keepintouch.core.contact.Contact;

import java.util.HashMap;
import java.util.Map;

public class ContactProvider implements com.alexe.keepintouch.core.contact.ContactProvider {

    private Map<String, Contact> cache = new HashMap<>();
    private Context context;

    public ContactProvider(Context context) {
        this.context = context;
    }

    private static final String[] CONTACT_PROJECTION = new String[]{
            ContactsContract.PhoneLookup._ID,
            ContactsContract.PhoneLookup.DISPLAY_NAME,
            ContactsContract.PhoneLookup.PHOTO_URI
    };

    @Override
    public Contact getContact(String search) {

        if (cache.containsKey(search)) {
            return cache.get(search);
        }

        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(search));
        Cursor c = context.getContentResolver().query(uri, CONTACT_PROJECTION, null, null, null);

        if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        Contact contact = new Contact(c.getString(0), c.getString(1), c.getString(2));
        c.close();

        cache.put(search, contact);
        return contact;
    }
}

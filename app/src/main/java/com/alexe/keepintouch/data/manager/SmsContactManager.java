package com.alexe.keepintouch.data.manager;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.util.Log;

import com.alexe.keepintouch.data.Contact;
import com.alexe.keepintouch.data.ContactManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmsContactManager implements ContactManager {

    private Context context;

    public SmsContactManager(Context ctx) {
        context = ctx;
    }

    private final String[] COLS = new String[]{
            Telephony.Sms.DATE,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY
    };

    @Override
    public List<Contact> getContacts() {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);


        Cursor c = context.getContentResolver().query(
                Telephony.Sms.CONTENT_URI,
                COLS,
                Telephony.Sms.DATE + " > ?",
                new String[]{
                        Long.toString(cal.getTimeInMillis())
                },
                null);

        Map<String, Contact> lastInteractions = mapResults(c);

        c.close();

        return new ArrayList<>(lastInteractions.values());
    }

    private Map<String, Contact> mapResults(Cursor c) {

        Map<String, Contact> lastInteraction = new HashMap<>();

        if (!c.moveToFirst()) {
            return lastInteraction;
        }

        for (int i = 0; i < c.getCount(); ++i) {

            Date lastContact = new Date(c.getLong(0));
            String phoneNumber = c.getString(1);
            String body = c.getString(2);
            ContactInfo contactInfo = getContactInfoFromPhoneNumber(phoneNumber);


            if(contactInfo == null) {
                c.moveToNext();
                continue;
            }

            Contact contact;

            if (!lastInteraction.containsKey(contactInfo.id)) {
                contact = new Contact();
                contact.setId(contactInfo.id);
                contact.setName(contactInfo.name);
                contact.setPicture(contactInfo.photo);
                contact.setLastContacted(lastContact);
                contact.setSource("SMS");
                contact.setLastMessage(body);
                lastInteraction.put(contactInfo.id, contact);
            } else {
                contact = lastInteraction.get(contactInfo.id);

                if (lastContact.after(contact.getLastContacted())) {
                    contact.setLastContacted(lastContact);
                    contact.setLastMessage(body);
                }
            }

            c.moveToNext();
        }

        return lastInteraction;
    }

    private class ContactInfo {
        public String id;
        public String name;
        public String type;
        public String photo;

    }

    private ContactInfo getContactInfoFromPhoneNumber(String phone) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone));
        Cursor c = context.getContentResolver().query(
                uri,
                new String[]{
                        ContactsContract.PhoneLookup.DISPLAY_NAME,
                        ContactsContract.PhoneLookup._ID,
                        ContactsContract.PhoneLookup.TYPE,
                        ContactsContract.PhoneLookup.PHOTO_URI
                },
                null,null,null
        );

        if(!c.moveToFirst()) {
            c.close();
            return null;
        }

        ContactInfo x = new ContactInfo();
        x.name = c.getString(0);
        x.id = c.getString(1);
        x.type = c.getString(2);
        x.photo = c.getString(3);

        c.close();

        return x;
    }
}

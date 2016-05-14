package com.alexe.keepintouch.data.interaction.source.sms;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;

import com.alexe.keepintouch.core.interaction.entity.Contact;
import com.alexe.keepintouch.core.interaction.entity.LastInteraction;
import com.alexe.keepintouch.core.interaction.source.InteractionSource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SmsInteractionSource implements InteractionSource {

    private final String[] COLS = new String[]{
            Telephony.Sms.DATE,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY
    };

    private Context context;

    public SmsInteractionSource(Context ctx) {
        context = ctx;
    }

    @Override
    public Map<String, LastInteraction> getLastInteractions(Date sinceTime) {

        Cursor c = context.getContentResolver().query(
                Telephony.Sms.CONTENT_URI,
                COLS,
                Telephony.Sms.DATE + " > ?",
                new String[]{
                        Long.toString(sinceTime.getTime())
                },
                null);

        Map<String, LastInteraction> lastInteractions = mapResults(c);

        c.close();

        return lastInteractions;
    }

    private Map<String, LastInteraction> mapResults(Cursor c) {

        Map<String, LastInteraction> lastInteraction = new HashMap<>();

        if (!c.moveToFirst()) {
            return lastInteraction;
        }

        for (int i = 0; i < c.getCount(); ++i) {

            Date lastContact = new Date(c.getLong(0));
            final String phoneNumber = c.getString(1);
            String body = c.getString(2);
            final ContactInfo contactInfo = getContactInfoFromPhoneNumber(phoneNumber);

            if (contactInfo == null) {
                c.moveToNext();
                continue;
            }

            final LastInteraction lastInter;

            if (!lastInteraction.containsKey(contactInfo.id)) {
                SmsSourceDetails details = new SmsSourceDetails(phoneNumber);
                details.setLastMessage(body);
                lastInter = new LastInteraction(
                        new Contact(contactInfo.id, contactInfo.name, contactInfo.photo),
                        lastContact,
                        details);
                lastInter.setInteractionSourceDetails(details);

                lastInteraction.put(contactInfo.id, lastInter);
            } else {
                lastInter = lastInteraction.get(contactInfo.id);

                if (lastContact.after(lastInter.getDate())) {
                    lastInter.setDate(lastContact);
                    ((SmsSourceDetails) lastInter.getInteractionSourceDetails()).setLastMessage(body);
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
                null, null, null
        );

        if (!c.moveToFirst()) {
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

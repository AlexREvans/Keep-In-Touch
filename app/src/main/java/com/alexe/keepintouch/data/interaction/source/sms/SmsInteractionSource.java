package com.alexe.keepintouch.data.interaction.source.sms;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;

import com.alexe.keepintouch.core.contact.Contact;
import com.alexe.keepintouch.core.contact.ContactProvider;
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
    private ContactProvider contactProvider;

    public SmsInteractionSource(Context ctx, ContactProvider contactProvider) {
        context = ctx;
        this.contactProvider = contactProvider;
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
            final Contact contactInfo = contactProvider.getContact(phoneNumber);

            if (contactInfo == null) {
                c.moveToNext();
                continue;
            }

            final LastInteraction lastInter;

            if (!lastInteraction.containsKey(contactInfo.getId())) {
                SmsSourceDetails details = new SmsSourceDetails(phoneNumber);
                details.setLastMessage(body);
                lastInter = new LastInteraction(
                        contactInfo,
                        lastContact,
                        details);
                lastInter.setInteractionSourceDetails(details);

                lastInteraction.put(contactInfo.getId(), lastInter);
            } else {
                lastInter = lastInteraction.get(contactInfo.getId());

                if (lastContact.after(lastInter.getDate())) {
                    lastInter.setDate(lastContact);
                    ((SmsSourceDetails) lastInter.getInteractionSourceDetails()).setLastMessage(body);
                }
            }

            c.moveToNext();
        }

        return lastInteraction;
    }
}

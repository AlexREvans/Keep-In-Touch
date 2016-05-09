package com.alexe.keepintouch.control;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexe.keepintouch.R;
import com.alexe.keepintouch.data.Contact;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact lhs, Contact rhs) {
                return lhs.getLastContacted().compareTo(rhs.getLastContacted());
            }
        });

        this.contacts = contacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_last_contact, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...
        ContactViewHolder vh = new ContactViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact c = contacts.get(position);
        holder.load(c);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}

package com.alexe.keepintouch.control;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexe.keepintouch.R;
import com.alexe.keepintouch.data.Contact;

import org.w3c.dom.Text;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...
        ContactViewHolder vh = new ContactViewHolder((TextView)v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact c = contacts.get(position);
        String s = "You last spoke to " + c.getName() + " on " + c.getLastContacted() + ": " + c.getLastMessage();
        holder.text.setText(s);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}

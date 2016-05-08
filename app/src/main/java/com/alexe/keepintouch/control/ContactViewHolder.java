package com.alexe.keepintouch.control;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alexe.keepintouch.R;
import com.alexe.keepintouch.data.Contact;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private TextView contactName;
    private TextView contactedOn;
    private TextView contactMessage;

    public ContactViewHolder(View itemView) {
        super(itemView);
        contactName = (TextView) itemView.findViewById(R.id.contactName);
        contactedOn = (TextView) itemView.findViewById(R.id.contactOn);
        contactMessage = (TextView) itemView.findViewById(R.id.contactMessage);
    }

    public void load(Contact c) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(c.getLastContacted());

        contactName.setText(c.getName());
        contactedOn.setText(new SimpleDateFormat("dd MMM").format(cal.getTime()));
        contactMessage.setText(c.getLastMessage());
    }
}

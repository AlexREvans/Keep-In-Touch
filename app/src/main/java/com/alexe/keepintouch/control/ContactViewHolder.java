package com.alexe.keepintouch.control;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexe.keepintouch.R;
import com.alexe.keepintouch.data.Contact;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private TextView contactName;
    private TextView contactedOn;
    private TextView contactMessage;
    private ImageView contactPic;

    private Contact contact;

    public ContactViewHolder(View itemView) {
        super(itemView);
        contactName = (TextView) itemView.findViewById(R.id.contactName);
        contactedOn = (TextView) itemView.findViewById(R.id.contactOn);
        contactMessage = (TextView) itemView.findViewById(R.id.contactMessage);
        contactPic = (ImageView) itemView.findViewById(R.id.contactPic);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(contact.getTalkToMe().getIntent("Hey " + contact.getName() + "! I'm sending you a super-cool message to keep in touch! Ha!"));
            }
        });
    }

    public void load(Contact c) {
        contact = c;

        Calendar cal = Calendar.getInstance();
        cal.setTime(c.getLastContacted());

        contactName.setText(c.getName());
        contactedOn.setText(new SimpleDateFormat("dd MMM").format(cal.getTime()));
        contactMessage.setText(c.getLastMessage());
        if (c.getPicture() != null) {
            contactPic.setImageURI(Uri.parse(c.getPicture()));
        } else {
            contactPic.getLayoutParams().height = 0;
        }
    }

}

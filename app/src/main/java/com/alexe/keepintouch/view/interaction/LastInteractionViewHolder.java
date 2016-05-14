package com.alexe.keepintouch.view.interaction;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexe.keepintouch.R;
import com.alexe.keepintouch.core.interaction.entity.LastInteraction;
import com.alexe.keepintouch.data.interaction.source.sms.SmsSourceDetails;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LastInteractionViewHolder extends RecyclerView.ViewHolder {

    private TextView contactName;
    private TextView contactedOn;
    private TextView contactMessage;
    private ImageView contactPic;

    private LastInteraction lastInteraction;

    public LastInteractionViewHolder(View itemView) {
        super(itemView);
        contactName = (TextView) itemView.findViewById(R.id.contactName);
        contactedOn = (TextView) itemView.findViewById(R.id.contactOn);
        contactMessage = (TextView) itemView.findViewById(R.id.contactMessage);
        contactPic = (ImageView) itemView.findViewById(R.id.contactPic);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(lastInteraction.getInteractionSourceDetails().getResponder().getIntent("Hey " + lastInteraction.getContact().getName() + "! I'm sending you a super-cool message to keep in touch! Ha!"));
            }
        });
    }

    public void load(LastInteraction lastInteraction) {
        this.lastInteraction = lastInteraction;

        Calendar cal = Calendar.getInstance();
        cal.setTime(lastInteraction.getDate());

        contactName.setText(lastInteraction.getContact().getName());
        contactedOn.setText(new SimpleDateFormat("dd MMM").format(cal.getTime()));

        if (lastInteraction.getInteractionSourceDetails() instanceof SmsSourceDetails) {
            contactMessage.setText(((SmsSourceDetails) lastInteraction.getInteractionSourceDetails()).getLastMessage());
        }

        if (lastInteraction.getContact().getPictureUri() != null) {
            contactPic.setImageURI(Uri.parse(lastInteraction.getContact().getPictureUri()));
        } else {
            contactPic.getLayoutParams().height = 0;
        }
    }

}

package com.alexe.keepintouch.view.interaction;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexe.keepintouch.R;
import com.alexe.keepintouch.core.interaction.entity.LastInteraction;
import com.alexe.keepintouch.core.interaction.entity.SourceDetails;
import com.alexe.keepintouch.data.interaction.source.sms.SmsSourceDetails;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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
                SourceDetails.Responder respond = lastInteraction.getInteractionSourceDetails().getResponder();
                if (respond != null) {
                    v.getContext().startActivity(respond.getIntent("Hey " + lastInteraction.getContact().getName() + "! I'm sending you a super-cool message to keep in touch! Ha!"));
                } else {
                    Toast.makeText(v.getContext(), "No app installed to respond to " + lastInteraction.getContact().getName(), Toast.LENGTH_SHORT).show();
                }
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
            Glide
                    .with(contactPic.getContext())
                    .load(lastInteraction.getContact().getPictureUri())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            hidePicture();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .fitCenter()
                    .centerCrop()
                    .into(contactPic);

        } else {
            hidePicture();
        }
    }

    private void hidePicture() {
        contactPic.getLayoutParams().height = 0;
        contactPic.getLayoutParams().width = 0;
    }

}

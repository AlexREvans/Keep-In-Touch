package com.alexe.keepintouch.control;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    public TextView text;

    public ContactViewHolder(TextView itemView) {
        super(itemView);
        text = itemView;
    }
}

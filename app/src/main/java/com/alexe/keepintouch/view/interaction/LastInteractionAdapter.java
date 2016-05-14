package com.alexe.keepintouch.view.interaction;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexe.keepintouch.R;
import com.alexe.keepintouch.core.interaction.entity.LastInteraction;

import java.util.List;

public class LastInteractionAdapter extends RecyclerView.Adapter<LastInteractionViewHolder> {

    private List<LastInteraction> lastInteractions;

    public LastInteractionAdapter(List<LastInteraction> lastInteractions) {
        this.lastInteractions = lastInteractions;
    }

    @Override
    public LastInteractionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_last_contact, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...
        LastInteractionViewHolder vh = new LastInteractionViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(LastInteractionViewHolder holder, int position) {
        LastInteraction li = lastInteractions.get(position);
        holder.load(li);
    }

    @Override
    public int getItemCount() {
        return lastInteractions.size();
    }
}

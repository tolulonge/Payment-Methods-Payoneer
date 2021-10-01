package com.example.payoneer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.payoneer.R;
import com.example.payoneer.model.PayoneerItems;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class PayoneerListAdapter extends RecyclerView.Adapter<PayoneerListAdapter.ViewHolder>{
    private final List<PayoneerItems> payoneerItems;
    private final Context context;

    public PayoneerListAdapter(Context context, List<PayoneerItems> payoneerItems) {
        this.payoneerItems = payoneerItems;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.payoneer_items, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PayoneerItems payoneerItem = payoneerItems.get(position);
        holder.paymentMethodLabelTxt.setText(payoneerItem.getLabel());
        holder.paymentMethodTxt.setText(payoneerItem.getMethod());
        Glide.with(context)
                .load(payoneerItem.getLinks().get("logo"))
                .placeholder(R.drawable.loading_status_animation)
                .error(R.drawable.error)
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"You clicked on "+payoneerItem.getLabel(),Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(context.getResources().getColor(R.color.green_700))
                        .show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return payoneerItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView paymentMethodLabelTxt;
        public TextView paymentMethodTxt;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.paymentMethodLabelTxt = itemView.findViewById(R.id.paymentLabel);
            this.paymentMethodTxt = itemView.findViewById(R.id.paymentMethodTxt);
            this.cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

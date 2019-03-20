package com.example.shopinus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter {
    private static ClickListener clickListener;
    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener){
        ItemAdapter.clickListener = clickListener;
    }

    private ArrayList<Item> items;
    public ItemAdapter(ArrayList<Item> items){
        this.items = items;
        Log.e("items",""+items.size());
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView title;
        public final ImageView image;
        public ViewHolder(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.imageView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Item item = items.get(i);
        ((ViewHolder) viewHolder).title.setText(item.getName());
        Log.e("URL",item.getURL());
        Picasso.get().load(item.getURL()).into(((ViewHolder)viewHolder).image);

    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        else {
            return 0;
        }
    }
}

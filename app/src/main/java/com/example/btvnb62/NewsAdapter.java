package com.example.btvnb62;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    private List<TinTuc> newsList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position, String url);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public NewsAdapter(List<TinTuc> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        TinTuc tinTuc = newsList.get(position);
        Picasso.get().load(tinTuc.getUrlanh()).into(holder.urlanh);
        holder.txttitle.setText(tinTuc.getTitle());

        ////////////////
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position, tinTuc.getUrl());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView urlanh;
        TextView txttitle;

        public NewsViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            urlanh = itemView.findViewById(R.id.urlanh);
            txttitle = itemView.findViewById(R.id.txttitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        TinTuc tinTuc = newsList.get(position);
                        listener.onItemClick(position, tinTuc.getUrl());
                    }
                }
            });
        }
    }
}

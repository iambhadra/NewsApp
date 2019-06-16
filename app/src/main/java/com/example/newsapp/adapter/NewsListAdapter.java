package com.example.newsapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.R;
import com.example.newsapp.models.Articles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsHolder> {


    final private newsItemClickListener mOnNewsClicked;
    private List<Articles> lNewsList = new ArrayList<Articles>();

    public NewsListAdapter(newsItemClickListener myListener, List<Articles> newsList) {
        this.mOnNewsClicked = myListener;
        this.lNewsList = newsList;
    }

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView newsImage_iv;
        TextView headline_tv;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            newsImage_iv = (ImageView) itemView.findViewById(R.id.news_poster_iv);
            headline_tv = (TextView) itemView.findViewById(R.id.headline_tv);

           itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnNewsClicked.onNewsItemClicked(getAdapterPosition());

        }
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdMovieitem = R.layout.news_list_adapter;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachParentImmediatley = false;
        View view = inflater.inflate(layoutIdMovieitem, viewGroup, shouldAttachParentImmediatley);
        NewsHolder viewHolder = new NewsHolder(view);

         return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder viewHolder, int i) {
        Picasso.get()
                .load( lNewsList.get(i).getUrlToImage())
                .into(viewHolder.newsImage_iv);
        viewHolder.headline_tv.setText(lNewsList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return lNewsList.size();
    }

    public interface newsItemClickListener {
        void onNewsItemClicked(int iPosition);
    }

}

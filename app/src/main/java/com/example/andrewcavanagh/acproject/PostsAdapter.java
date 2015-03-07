package com.example.andrewcavanagh.acproject;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by andrewcavanagh on 3/6/15.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    //our data source -- an array of strings -- should probably be a model
    private String[] dataSet;

    //constructor for our adapter (used to set our data source).
    public PostsAdapter(String[] dataSource) {
        dataSet = dataSource;
    }

    //inner view holder class -- exposes item layout
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.posts_list_view_item);
        }
    }

    //create new row using viewHolder
    //index (i) here is the type of view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rowView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHolder(rowView);
    }

    //configure the row's contents
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String rowData = dataSet[i];
        viewHolder.mTextView.setText(rowData);
    }

    //length of the table
    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    //returns the type of view that should be displayed.
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}

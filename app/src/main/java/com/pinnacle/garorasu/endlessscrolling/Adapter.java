package com.pinnacle.garorasu.endlessscrolling;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinnacle.garorasu.endlessscrolling.Interface.AdapterView;
import com.pinnacle.garorasu.endlessscrolling.Interface.MainView;
import com.pinnacle.garorasu.endlessscrolling.Interface.PresenterView;
import com.pinnacle.garorasu.endlessscrolling.Model.Post;

import java.util.ArrayList;

/**
 * Created by garorasu on 19/2/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements AdapterView{
    private final MainView mainView;
    private final PresenterView presenterView;
    private final ArrayList<Post> allPosts;

    public Adapter(MainView mainView){
        this.mainView = mainView;
        this.presenterView = new Presenter(mainView,this);
        this.allPosts = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        Post post = allPosts.get(position);
        Log.d("Adapter","Bind data "+post.getId());
        holder.mId.setText(post.getId());
        holder.mUserId.setText(post.getUserId());
        holder.mTitle.setText(post.getTitle());
        holder.mBody.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return allPosts.size();
    }

    @Override
    public void addItem(Post post) {
        Log.d("Adapter","Data item added "+post.getId());
        allPosts.add(post);
    }

    @Override
    public void requestData() {
        Log.d("Adapter","Request Data");
        presenterView.requestData();
    }

    @Override
    public void loadMore(int page) {
        Log.d("Adapter","Load more "+page);
        presenterView.loadMore(page);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mUserId,mId,mTitle,mBody;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.text_title);
            mBody = (TextView) itemView.findViewById(R.id.text_body);
            mUserId = (TextView) itemView.findViewById(R.id.text_userid);
            mId = (TextView) itemView.findViewById(R.id.text_id);
        }
    }
}

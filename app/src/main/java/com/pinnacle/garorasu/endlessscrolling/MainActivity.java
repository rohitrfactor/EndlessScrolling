package com.pinnacle.garorasu.endlessscrolling;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.pinnacle.garorasu.endlessscrolling.Interface.MainView;


public class MainActivity extends Activity implements MainView {
    final Adapter adapter = new Adapter(this);
    private RecyclerView recyclerView;
    private LinearLayout mNetworkError,mNetworkErrorLoadMore;
    private ProgressBar mProgressBar,mProgressBarLoadMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUIElements();
    }

    public void setUIElements(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_post);
        mNetworkError = (LinearLayout) findViewById(R.id.network_error);
        mNetworkErrorLoadMore = (LinearLayout) findViewById(R.id.network_error_load_more);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBarLoadMore = (ProgressBar) findViewById(R.id.progressBar_load_more);
        adapter.requestData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                adapter.loadMore(page);
            }
        };

        recyclerView.setOnScrollListener(endlessRecyclerViewScrollListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        Log.d("Main","View Updated show progress");

        //Choose any UI element to update view thread from model background thread
        mProgressBar.post(new Runnable() {
            @Override
            public void run() {
                    mProgressBar.setVisibility(View.VISIBLE);

                    recyclerView.setVisibility(View.INVISIBLE);
                    mNetworkError.setVisibility(View.INVISIBLE);
                    mNetworkErrorLoadMore.setVisibility(View.INVISIBLE);
                    //mProgressBarLoadMore.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void hideProgress() {

        Log.d("Main","View Updated hide progress");

        mProgressBar.post(new Runnable() {
            @Override
            public void run() {

                recyclerView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.INVISIBLE);
                mNetworkError.setVisibility(View.INVISIBLE);
                mNetworkErrorLoadMore.setVisibility(View.INVISIBLE);
                //mProgressBarLoadMore.setVisibility(View.INVISIBLE);
            }
        });


    }

    @Override
    public void networkUnavailable() {
        Log.d("Main","View Updated network unavailable");

        mProgressBar.post(new Runnable() {
            @Override
            public void run() {
                mNetworkError.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                mNetworkErrorLoadMore.setVisibility(View.INVISIBLE);
                //mProgressBarLoadMore.setVisibility(View.INVISIBLE);
            }
        });



    }

    @Override
    public void showLoadMoreProgress() {
        Log.d("Main","View Updated show load more progress");

        mProgressBar.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setVisibility(View.VISIBLE);
                //mProgressBarLoadMore.setVisibility(View.VISIBLE);

                mNetworkError.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                mNetworkErrorLoadMore.setVisibility(View.INVISIBLE);
            }
        });


    }

    @Override
    public void loadMoreNetworkUnavailable() {
        Log.d("Main","View Updated load more network unavailable");

        mProgressBar.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setVisibility(View.VISIBLE);
                mNetworkError.setVisibility(View.VISIBLE);

                //mProgressBarLoadMore.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                mNetworkErrorLoadMore.setVisibility(View.INVISIBLE);
            }
        });



    }
}

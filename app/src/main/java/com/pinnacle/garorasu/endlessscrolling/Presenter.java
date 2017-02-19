package com.pinnacle.garorasu.endlessscrolling;

import android.util.Log;

import com.pinnacle.garorasu.endlessscrolling.Interface.AdapterView;
import com.pinnacle.garorasu.endlessscrolling.Interface.InteracterView;
import com.pinnacle.garorasu.endlessscrolling.Interface.MainView;
import com.pinnacle.garorasu.endlessscrolling.Interface.PresenterView;
import com.pinnacle.garorasu.endlessscrolling.Model.Post;

/**
 * Created by garorasu on 19/2/17.
 */

public class Presenter implements PresenterView{
    private final InteracterView interacterView;
    private final AdapterView adapterView;
    private final MainView mainView;
    private final int INIT_PAGE_NUMBER = 0;

    public Presenter(MainView mainView,AdapterView adapterView){
        this.mainView = mainView;
        this.adapterView = adapterView;
        this.interacterView = new Interacter(this);
    }

    @Override
    public void addItem(Post post) {
        Log.d("Presenter","Object successfully added"+post.getId());
        adapterView.addItem(post);
    }

    @Override
    public void onSuccess() {
        if(mainView != null){
            Log.d("Presenter","Object successfully fetched, hideProgress");
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(int page) {
        Log.d("Presenter","Failure on page number"+page);
        if(page == INIT_PAGE_NUMBER){
            if(mainView != null){
                mainView.networkUnavailable();
            }
        }
        else{
            if(mainView != null) {
                mainView.loadMoreNetworkUnavailable();
            }
        }
    }

    @Override
    public void networkUnavailable(int page) {
        Log.d("Presenter","Failure on page number"+page);
        if(page == INIT_PAGE_NUMBER){
            if(mainView != null){
                mainView.networkUnavailable();
            }
        }
        else{
            if(mainView != null) {
                mainView.loadMoreNetworkUnavailable();
            }
        }
    }

    @Override
    public void requestData() {
        Log.d("Presenter","Request Data");
        if(mainView !=null){
            Log.d("Presenter","Request Data showProgress");
            mainView.showProgress();
        }
        interacterView.request(INIT_PAGE_NUMBER);
    }

    @Override
    public void loadMore(int page) {
        Log.d("Presenter","load more page"+page);
        if(page<10){
            if(mainView!=null){
                Log.d("Presenter","load more page showProgress"+page);
                mainView.showLoadMoreProgress();
            }
            interacterView.request(page);
        }
    }
}

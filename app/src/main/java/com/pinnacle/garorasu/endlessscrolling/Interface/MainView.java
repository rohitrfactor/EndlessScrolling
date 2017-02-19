package com.pinnacle.garorasu.endlessscrolling.Interface;

/**
 * Created by garorasu on 17/2/17.
 */

public interface MainView {
    void showProgress();
    void hideProgress();
    void networkUnavailable();
    void showLoadMoreProgress();
    void loadMoreNetworkUnavailable();
}

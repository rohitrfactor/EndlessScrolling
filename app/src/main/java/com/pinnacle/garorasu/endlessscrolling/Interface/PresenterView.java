package com.pinnacle.garorasu.endlessscrolling.Interface;

import com.pinnacle.garorasu.endlessscrolling.Model.Post;

/**
 * Created by garorasu on 17/2/17.
 */

public interface PresenterView {
    void addItem(Post post);
    void onSuccess();
    void onFailure(int page);
    void networkUnavailable(int page);
    void requestData();
    void loadMore(int page);
}

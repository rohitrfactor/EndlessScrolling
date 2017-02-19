package com.pinnacle.garorasu.endlessscrolling.Interface;

import com.pinnacle.garorasu.endlessscrolling.Model.Post;

/**
 * Created by garorasu on 17/2/17.
 */

public interface AdapterView {
    void addItem(Post post);
    void requestData();
    void loadMore(int page);
}

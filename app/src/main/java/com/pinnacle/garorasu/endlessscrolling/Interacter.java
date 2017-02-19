package com.pinnacle.garorasu.endlessscrolling;


import android.util.Log;

import com.pinnacle.garorasu.endlessscrolling.Interface.InteracterView;
import com.pinnacle.garorasu.endlessscrolling.Interface.PresenterView;
import com.pinnacle.garorasu.endlessscrolling.Model.Post;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by garorasu on 18/2/17.
 */

public class Interacter implements InteracterView {
    private final PresenterView presenterView;

    public Interacter(PresenterView presenterView){
        this.presenterView = presenterView;
    }

    @Override
    public void request(int page) {
        final int pageNumber = page;
        final int startIndex = page*10+1;
        final int MaxIndex = startIndex+10;
        Log.d("Interacter","Start Index "+startIndex+" to "+MaxIndex);
        for(int i=startIndex;i<MaxIndex;i++){
            OkHttpClient client = new OkHttpClient();
            Log.d("Interacter","Entered in loop "+i);
            Request request = new Request.Builder()
                    .url("http://jsonplaceholder.typicode.com/posts/"+i)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("Interacter","Failure with exception"+e);
                    presenterView.networkUnavailable(pageNumber);
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {

                    try {
                        String responseData = response.body().string();
                        JSONObject json = new JSONObject(responseData);
                        Post post = Post.jsonToPost(json);
                        presenterView.addItem(post);
                        //Should call after fetching all the data; wrong updation
                        presenterView.onSuccess();
                        Log.d("Interacter","Object successfully fetched"+post.getId());
                    } catch (JSONException e) {
                        Log.e("Interacter","JSON Exception "+e);
                        presenterView.onFailure(pageNumber);
                    }
                }
            });

        }

    }
}

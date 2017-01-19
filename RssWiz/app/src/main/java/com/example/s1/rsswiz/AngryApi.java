package com.example.s1.rsswiz;

/**
 * Created by S1 on 1/18/2017.
 */
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AngryApi {
        @GET("bins/sv06b")
        Call<List<PostModel>> getData();
}

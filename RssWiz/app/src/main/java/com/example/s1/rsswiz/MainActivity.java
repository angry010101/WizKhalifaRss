package com.example.s1.rsswiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    List<PostModel> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostsAdapter adapter = new PostsAdapter(posts);
        recyclerView.setAdapter(adapter);

        RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();//смотрим сколько элементов на экране
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();//сколько всего элементов
                int firstVisibleItems = recyclerView.getLayoutManager().findFirstVisibleItemPosition();//какая позиция первого элемента

             //   if (!isLoading) {//проверяем, грузим мы что-то или нет, эта переменная должна быть вне класса  OnScrollListener
                    if ( (visibleItemCount+firstVisibleItems) >= totalItemCount) {
                        isLoading = true;//ставим флаг что мы попросили еще элемены
                        if(loadingListener != null){
                            loadingListener.loadMoreItems(totalItemCount);//тут я использовал калбэк который просто говорит наружу что нужно еще элементов и с какой позиции начинать загрузку
                        }
                    }
              //  }
            }
        };

        recyclerView.setOnScrollChangeListener(scrollListener);
     /*   try {
            Response response = App.getApi().getData().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        App.getApi().getData().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                posts.addAll(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });
    }
}
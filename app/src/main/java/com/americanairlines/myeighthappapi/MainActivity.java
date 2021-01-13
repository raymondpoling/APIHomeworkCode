package com.americanairlines.myeighthappapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.americanairlines.myeighthappapi.model.GitResponse;
import com.americanairlines.myeighthappapi.network.GitRetrofit;
import com.americanairlines.myeighthappapi.view.adapter.GitAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GitRetrofit gitRetrofit = new GitRetrofit();
    private RecyclerView gitRecyclerView;
    private GitAdapter gitAdapter = new GitAdapter(new ArrayList<>());
    private ImageView userImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        gitRecyclerView = findViewById(R.id.main_recyclerview);
        gitRecyclerView.setAdapter(gitAdapter);
        userImageView = findViewById(R.id.main_avatar_imageview);

        //use okhttp
        new Thread(){
            @Override
            public void run() {
                super.run();
                openConnection();
            }
        }.start();

        //use Retrofit
        new Thread() {

            @Override
            public void run() {
                super.run();
                gitRetrofit.getUserRepositories("Dalo-Chinkhwangwa-Prof")
                        .enqueue(new Callback<List<GitResponse>>() {
                            @Override
                            public void onResponse(Call<List<GitResponse>> call, Response<List<GitResponse>> response) {

                                if (response.isSuccessful() && response.body() != null) {
                                    Log.d("TAG_X", "" + response.body().size());
                                    gitAdapter.setResponseList(response.body());
                                    Glide.with(MainActivity.this)
                                            .setDefaultRequestOptions(RequestOptions.circleCropTransform())
                                            .load(response.body().get(0).getOwner().getAvatarUrl())
                                            .into(userImageView);
                                } else
                                    Log.d("TAG_X", "Error something did not workout...");
                            }

                            @Override
                            public void onFailure(Call<List<GitResponse>> call, Throwable t) {
                                Log.d("TAG_X", "oops " + t + " " + call.request().url());
                            }
                        });
            }
        }.start();
    }

    private void openConnection() {

        try {
            URL gitUrl = new URL("https://api.github.com/users/dalo-chinkhwangwa-prof/repos");
            HttpURLConnection urlConnection = (HttpURLConnection) gitUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String input;
            StringBuilder builder = new StringBuilder();
            while ((input = reader.readLine()) != null) {
                builder.append(input);
            }

            Log.d("TAG_X", builder.toString());
            urlConnection.disconnect();
            Gson gson = new Gson();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
















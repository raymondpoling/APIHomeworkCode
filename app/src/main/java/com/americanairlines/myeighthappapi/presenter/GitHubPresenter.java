package com.americanairlines.myeighthappapi.presenter;

import android.util.Log;

import com.americanairlines.myeighthappapi.model.GitResponse;
import com.americanairlines.myeighthappapi.network.GitRetrofit;
import com.americanairlines.myeighthappapi.view.adapter.GitAdapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubPresenter implements GitHubPresenterContract.GitHubPresenter {

    private GitRetrofit gitRetrofit = new GitRetrofit();
    private GitAdapter gitAdapter;
    private GitHubPresenterContract.GitHubView gitHubView;

    public GitHubPresenter(GitHubPresenterContract.GitHubView gitHubView) {
        this.gitHubView = gitHubView;
        gitAdapter = new GitAdapter(new ArrayList<>(0), gitHubView);
    }

    @Override
    public void getGitHub() {
        //use okhttp
        new Thread() {
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
                                    gitHubView.displayGitHub(response.body());
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

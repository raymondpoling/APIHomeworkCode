package com.americanairlines.myeighthappapi.network;

import com.americanairlines.myeighthappapi.model.GitResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.americanairlines.myeighthappapi.util.Constants.BASE_URL;

public class GitRetrofit {

    private GitApi gitApi;

    public GitRetrofit() {
        gitApi = createGitApi(createRetrofit());
    }

    private GitApi createGitApi(Retrofit retrofit) {
        return retrofit.create(GitApi.class);
    }

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<List<GitResponse>> getUserRepositories(String userName){
        return gitApi.getGitResponse(userName);
    }
}






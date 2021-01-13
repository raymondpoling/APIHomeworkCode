package com.americanairlines.myeighthappapi.network;

import com.americanairlines.myeighthappapi.model.GitResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.americanairlines.myeighthappapi.util.Constants.URL_PATH;
import static com.americanairlines.myeighthappapi.util.Constants.USER_NAME_PATH;

public interface GitApi {
    //BASE_URL = "https://api.github.com/ + users/{username}/repos?q=0";
    @GET(URL_PATH)
    Call<List<GitResponse>> getGitResponse(@Path(USER_NAME_PATH) String userName);

//    @GET("/users/{uname}/repos")
//    Call<List<GitResponse>> getUserRepos(@Path("uname") String userName);

}

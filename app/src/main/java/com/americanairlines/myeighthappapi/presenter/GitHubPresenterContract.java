package com.americanairlines.myeighthappapi.presenter;

import android.app.Activity;

import com.americanairlines.myeighthappapi.model.GitResponse;

import java.util.List;

public interface GitHubPresenterContract {
    interface GitHubPresenter {
        void getGitHub();
    }
    interface GitHubView {
        void displayGitHub(List<GitResponse> gitResponse);
        Activity getParentActivity();
    }
}

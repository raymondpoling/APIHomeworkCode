package com.americanairlines.myeighthappapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.americanairlines.myeighthappapi.R;
import com.americanairlines.myeighthappapi.model.GitResponse;
import com.americanairlines.myeighthappapi.presenter.GitHubPresenter;
import com.americanairlines.myeighthappapi.view.adapter.GitAdapter;

import static com.americanairlines.myeighthappapi.util.Constants.LOG_TAG;

public class MainActivity extends AppCompatActivity implements GitAdapter.GitDelegate {


    private GitHubFragment gitHubFragment = new GitHubFragment();
    private GitHubPresenter gitHubPresenter;

    private DetailsFragment detailsFragment = new DetailsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.github_frame, gitHubFragment)
                .addToBackStack(gitHubFragment.getTag())
                .commit();
        gitHubPresenter = new GitHubPresenter(gitHubFragment);

        gitHubPresenter.getGitHub();

    }

    @Override
    public void selectItem(GitResponse gitResponse) {

        Log.d(LOG_TAG, "Response: " + gitResponse.getCreatedAt());

        Bundle args = new Bundle();
        args.putParcelable("num1", gitResponse);
        detailsFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_frame, detailsFragment)
                .addToBackStack(detailsFragment.getTag())
                .commit();

        findViewById(R.id.details_frame).setOnClickListener(v -> {
            getSupportFragmentManager().popBackStack();
        });

    }
}
















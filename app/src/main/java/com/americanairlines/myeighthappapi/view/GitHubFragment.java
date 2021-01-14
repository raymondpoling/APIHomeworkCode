package com.americanairlines.myeighthappapi.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.americanairlines.myeighthappapi.R;
import com.americanairlines.myeighthappapi.model.GitResponse;
import com.americanairlines.myeighthappapi.presenter.GitHubPresenterContract;
import com.americanairlines.myeighthappapi.view.adapter.GitAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class GitHubFragment extends Fragment implements GitHubPresenterContract.GitHubView {
    private RecyclerView gitRecyclerView;
    private GitAdapter gitAdapter = new GitAdapter(new ArrayList<>(), this);
    private ImageView userImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.github_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gitRecyclerView = view.findViewById(R.id.main_recyclerview);

        gitRecyclerView.setAdapter(gitAdapter);
        userImageView = view.findViewById(R.id.main_avatar_imageview);

    }

    @Override
    public void displayGitHub(List<GitResponse> gitResponse) {
        gitAdapter.setResponseList(gitResponse);
        Glide.with(this)
                .setDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(gitResponse.get(0).getOwner().getAvatarUrl())
                .into(userImageView);
    }

    @Override
    public FragmentActivity getParentActivity() {
        return getActivity();
    }

}

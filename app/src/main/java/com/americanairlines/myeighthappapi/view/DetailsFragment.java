package com.americanairlines.myeighthappapi.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.americanairlines.myeighthappapi.R;
import com.americanairlines.myeighthappapi.model.GitResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import static com.americanairlines.myeighthappapi.util.Constants.LOG_TAG;

public class DetailsFragment extends Fragment {

    private TextView authorTextView;
    private TextView commitsTextView;
    private TextView repoTitleTextView;

    private ImageView authorAvatar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        authorAvatar = view.findViewById(R.id.avatar_imageview);
        authorTextView = view.findViewById(R.id.author_textview);
        commitsTextView = view.findViewById(R.id.commits_textview);
        repoTitleTextView = view.findViewById(R.id.repository_name_textview);

//        if (getArguments() != null) {
        //Assuming its not null - TODO: null check


        if(getArguments() != null && getArguments().getParcelable("num1") != null) {
            Log.d(LOG_TAG, "Got");
            GitResponse response = getArguments().getParcelable("num1");
                Glide.with(getContext())
                        .setDefaultRequestOptions(RequestOptions.circleCropTransform())
                        .load(response.getOwner().getAvatarUrl())
                        .placeholder(R.drawable.ic_placeholder)
                        .into(authorAvatar);

                authorTextView.setText(response.getOwner().getLogin());
                commitsTextView.setText(response.getCommitsUrl());
                repoTitleTextView.setText(response.getName());
//        }
            }
    }
}




















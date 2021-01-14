package com.americanairlines.myeighthappapi.view.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.americanairlines.myeighthappapi.R;
import com.americanairlines.myeighthappapi.model.GitResponse;
import com.americanairlines.myeighthappapi.presenter.GitHubPresenterContract;
import com.americanairlines.myeighthappapi.view.MainActivity;

import java.util.List;

import static com.americanairlines.myeighthappapi.util.Constants.LOG_TAG;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.GitViewHolder> {

    private List<GitResponse> responseList;
    private GitHubPresenterContract.GitHubView gitHubView;
    private GitDelegate gitDelegate;

    public interface GitDelegate{
        public void selectItem(GitResponse gitResponse);
    }

    public GitAdapter(List<GitResponse> responseList, GitHubPresenterContract.GitHubView gitHubView) {
        this.responseList = responseList;
        this.gitHubView = gitHubView;
//        this.gitDelegate = gitDelegate;
    }

    public void setResponseList(List<GitResponse> responseList) {
        Log.d(LOG_TAG, "HERE!!!!!, " + responseList.get(0).getCloneUrl());
        this.responseList = responseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.git_item, parent, false);
        return new GitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GitViewHolder holder, int position) {
        holder.repoNameTextView.setText(responseList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "ERR!!!" + gitHubView.getParentActivity().getClass());
                Activity t = gitHubView.getParentActivity();
                if(t.getClass() == MainActivity.class) {
                    ((MainActivity) t).selectItem(responseList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    class GitViewHolder extends RecyclerView.ViewHolder {
        TextView repoNameTextView;

        public GitViewHolder(@NonNull View itemView) {
            super(itemView);
            repoNameTextView = itemView.findViewById(R.id.repo_name);
        }
    }
}

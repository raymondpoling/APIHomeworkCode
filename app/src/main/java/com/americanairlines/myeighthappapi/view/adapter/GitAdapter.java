package com.americanairlines.myeighthappapi.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.americanairlines.myeighthappapi.R;
import com.americanairlines.myeighthappapi.model.GitResponse;

import java.util.List;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.GitViewHolder> {

    private List<GitResponse> responseList;
    private GitDelegate gitDelegate;

    public interface GitDelegate{
        public void selectItem(GitResponse gitResponse);
    }

    public GitAdapter(List<GitResponse> responseList, GitDelegate gitDelegate) {
        this.responseList = responseList;
        this.gitDelegate = gitDelegate;
    }

    public void setResponseList(List<GitResponse> responseList) {
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
                gitDelegate.selectItem(responseList.get(position));
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

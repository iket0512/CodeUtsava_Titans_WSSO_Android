package codeutsava.wsso.Posts.view;

import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import codeutsava.wsso.Posts.model.PostsData;
import codeutsava.wsso.Posts.model.Posts_Provider;
import codeutsava.wsso.Posts.presenter.Posts_Presenter;
import codeutsava.wsso.R;
import codeutsava.wsso.helper.Urls;
import codeutsava.wsso.map.Maps_View;

import static codeutsava.wsso.helper.MyApplication.getContext;

public class Posts extends AppCompatActivity implements PostsView{
    private Posts_Presenter posts_presenter;
    private PostsAdapter postsAdapter;
    private ProgressBar progressBar;
    private CardView card_default_events;

    private RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        progressBar=(ProgressBar)findViewById(R.id.posts_progressbar);
        recyclerView = (RecyclerView)findViewById(R.id.event_recycler_view);
        progressBar=(ProgressBar)findViewById(R.id.posts_progressbar);
        recyclerView.setHasFixedSize(true);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        linearLayoutManager = new LinearLayoutManager(getContext());
        postsAdapter = new PostsAdapter(this);
        posts_presenter=new Posts_Presenter(this,new Posts_Provider());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(postsAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Posts.this, Uri.parse(Urls.MAP));
            }
        });
        posts_presenter.getProductData("");
    }

    @Override
    public void showProgressBAr(Boolean bool) {
        if(bool)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setData(List<PostsData> data) {
        postsAdapter.setData(data);
        postsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Here We have some error", Toast.LENGTH_SHORT).show();
    }
}

package lkw1120.com.simpletracker;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import lkw1120.com.simpletracker.Adapter.BlockRecyclerViewAdapter;

public class BlockActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    BlockRecyclerViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);

        recyclerView = findViewById(R.id.blockactivity_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(BlockActivity.this));
        viewAdapter = new BlockRecyclerViewAdapter();
        viewAdapter.getBlockList();
        recyclerView.setAdapter(viewAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView reView, int newState) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewAdapter.getBlockList();
                    viewAdapter.notifyDataSetChanged();
                }
            }
        });

        swipeRefreshLayout = findViewById(R.id.blockactivity_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.removeAllViewsInLayout();
                viewAdapter = new BlockRecyclerViewAdapter();
                viewAdapter.getBlockList();
                recyclerView.setAdapter(viewAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}

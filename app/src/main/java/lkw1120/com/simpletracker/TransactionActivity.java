package lkw1120.com.simpletracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import lkw1120.com.simpletracker.Adapter.BlockRecyclerViewAdapter;
import lkw1120.com.simpletracker.Adapter.TransactionRecyclerViewAdapter;

public class TransactionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TransactionRecyclerViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.transactionactivity_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(TransactionActivity.this));
        viewAdapter = new TransactionRecyclerViewAdapter();
        viewAdapter.getTransactionList(intent.getStringArrayListExtra("txHash"));
        recyclerView.setAdapter(viewAdapter);

    }
}

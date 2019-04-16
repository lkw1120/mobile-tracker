package lkw1120.com.simpletracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

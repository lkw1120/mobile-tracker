package lkw1120.com.simpletracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import lkw1120.com.simpletracker.Model.ResultInfo;
import lkw1120.com.simpletracker.Task.GetResultTask;

public class ResultActivity extends AppCompatActivity {

    private TextView textView;

    private ResultInfo resultInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView = findViewById(R.id.resultactivity_textview_content);
        Intent intent = getIntent();
        try {
            GetResultTask getResultTask = new GetResultTask();
            resultInfo = getResultTask.execute(intent.getStringExtra("txHash")).get();
            textView.setText(resultInfo.getTxHash());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

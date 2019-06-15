package cn.edu.nuc.asynctaskdownloaddemo_94240_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnLoad = null;
    private TextView tvFinish = null;
    private ProgressBar bar = null;
    private DownloadTask downloadTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = findViewById(R.id.btnLoad);
        tvFinish = findViewById(R.id.tvFinish);
        bar = findViewById(R.id.bar);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadTask = new DownloadTask(tvFinish, bar);
                downloadTask.execute(200);
            }
        });

    }


}

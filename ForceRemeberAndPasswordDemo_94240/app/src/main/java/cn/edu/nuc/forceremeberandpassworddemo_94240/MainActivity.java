package cn.edu.nuc.forceremeberandpassworddemo_94240;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    public static final String  FORCE_OOFLIME = "cn.edu.nuc.MainActivity.force";

    private Button btnSendStop = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendStop = findViewById(R.id.btnSendStop);

        btnSendStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FORCE_OOFLIME);
                sendBroadcast(intent);
            }
        });

    }
}

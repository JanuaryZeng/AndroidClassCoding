package cn.edu.nuc.litepaldemo_94240;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {

    private Button createBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        createBtn.setOnClickListener(new MyLinstener());
    }

    public void init(){
        createBtn = (Button)findViewById(R.id.btnCreate);
    }

    public class MyLinstener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            LitePal.getDatabase();
        }
    }
}

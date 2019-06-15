package cn.edu.nuc.forceremeberandpassworddemo_94240;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText etUserName = null;
    private EditText etPassward = null;
    private Button btnLogin = null;
    private CheckBox remeberPass = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = findViewById(R.id.etUserName);
        etPassward = findViewById(R.id.etPassward);
        btnLogin = findViewById(R.id.btnLogin);
        remeberPass = findViewById(R.id.remeberPass);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);

        boolean isRemember = sharedPreferences.getBoolean("rememberPass",false);

        if(isRemember){
            String useNameStr = sharedPreferences.getString("useName",null);
            String passwardStr = sharedPreferences.getString("passwardStr",null);
            etUserName.setText(useNameStr);
            etPassward.setText(passwardStr);
            remeberPass.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnLogin:
                        String userNameStr = etUserName.getText().toString().trim();
                        String passwardStr = etPassward.getText().toString().trim();
                        if(userNameStr.equals("admin") && passwardStr.equals("123456")){
                            if(true == remeberPass.isChecked()) {
                                editor = sharedPreferences.edit();
                                editor.putString("useName", userNameStr);
                                editor.putString("rememberPass", String.valueOf(remeberPass.isChecked()));
                                editor.putString("passwardStr", passwardStr);
                            }
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"您输入的用户名和密码不正确",Toast.LENGTH_LONG).show();
                            etPassward.setText("");
                            etUserName.setText("");
                        }
                        break;
                }
            }
        });
    }
}

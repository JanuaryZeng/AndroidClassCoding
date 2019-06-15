package cn.edu.nuc.regieterdemo_94240;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private Button cityBtn = null, registerBtn = null;
    private EditText name = null, psd = null, psd2 = null, city = null;
    private RadioButton male = null, female = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityBtn = findViewById(R.id.cityBtn);
        registerBtn = findViewById(R.id.registerBtn);
        name = findViewById(R.id.name);
        psd = findViewById(R.id.psd);
        psd2 = findViewById(R.id.psd2);
        city = findViewById(R.id.city);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String checkResult=checkInfo();
                if(checkResult!=null){
                    AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("出错提示");
                    builder.setMessage(checkResult);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            psd.setText("");
                            psd2.setText("");
                        }
                    });
                    builder.create().show();
                }else{
                    Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("psd", psd.getText().toString());
                    String gender = male.isChecked()?"男":"女";
                    intent.putExtra("gender", gender);
                    intent.putExtra("city",city.getText().toString());
                    startActivity(intent);
                }
            }
        });

        cityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建需要对应于目标Activity的Intent
                Intent intent = new Intent(MainActivity.this, ChooseCityActivity.class);
                //启动指定Activity并等待返回的结果，其中0是请求码，用于标识该请求
                startActivityForResult(intent , 0);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode , int resultCode, Intent intent){
        //当requestCode、resultCode同时为0，也就是处理特定的结果
        if (requestCode == 0 && resultCode == 0){
            //取出Intent里的Extras数据
            Bundle data = intent.getExtras();
            //取出Bundle中的数据
            String resultCity = data.getString("city");
            //修改city文本框的内容
            city.setText(resultCity);
        }
    }

    public String checkInfo() {
        System.out.println(name);
        if (name.getText().toString() == null||name.getText().toString().equals("")) {
            System.out.println("***********");
            return "用户名不能为空";
        }
        if (psd.getText().toString().trim().length() < 6
                || psd.getText().toString().trim().length() > 15){
            return "密码位数应该6~15之间";
        }
        if(!psd.getText().toString().equals(psd2.getText().toString())){
            return "两次输入的密码不一致";
        }
        return null;
    }

}

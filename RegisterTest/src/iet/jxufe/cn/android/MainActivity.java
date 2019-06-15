package iet.jxufe.cn.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends Activity {
	private Button cityBtn, registerBtn;
	private EditText name, psd, psd2, city;
	private RadioButton male;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityBtn=(Button)findViewById(R.id.cityBtn);
        registerBtn=(Button)findViewById(R.id.registerBtn);
        name=(EditText)findViewById(R.id.name);
        psd=(EditText)findViewById(R.id.psd);
        psd2=(EditText)findViewById(R.id.psd2);
        city=(EditText)findViewById(R.id.city);
        male=(RadioButton)findViewById(R.id.male);
        registerBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String checkResult=checkInfo();
				if(checkResult!=null){
					Builder builder =new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("������ʾ");
					builder.setMessage(checkResult);
					builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
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
					String gender=male.isChecked()?"��":"Ů";
					intent.putExtra("gender", gender);
					intent.putExtra("city",city.getText().toString());
					startActivity(intent);
				}
			}
		});
        cityBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//������Ҫ��Ӧ��Ŀ��Activity��Intent
				Intent intent = new Intent(MainActivity.this
					, ChooseCityActivity.class);
				//����ָ��Activity���ȴ����صĽ��������0�������룬���ڱ�ʶ������
				startActivityForResult(intent , 0);					
			}
		});        
    }
	public void onActivityResult(int requestCode , int resultCode, Intent intent){
		//��requestCode��resultCodeͬʱΪ0��Ҳ���Ǵ����ض��Ľ��
		if (requestCode == 0&& resultCode == 0){
			//ȡ��Intent���Extras����
			Bundle data = intent.getExtras();
			//ȡ��Bundle�е�����
			String resultCity = data.getString("city");
			//�޸�city�ı��������
			city.setText(resultCity);
		}
	}
	public String checkInfo() {
		System.out.println(name);
		if (name.getText().toString() == null||name.getText().toString().equals("")) {
			System.out.println("***********");
			return "�û�������Ϊ��";
		}
		if (psd.getText().toString().trim().length() < 6
				|| psd.getText().toString().trim().length() > 15){
			return "����λ��Ӧ��6~15֮��";
		}
		if(!psd.getText().toString().equals(psd2.getText().toString())){
			return "������������벻һ��";
		}
			return null;
	}
}
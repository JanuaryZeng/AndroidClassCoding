package cn.edu.nuc.notepaddemo_94240;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText etTitle = null;
    private EditText etContext = null;
    private EditText etTime = null;

    private Button btnSelectTime = null;
    private Button btnAdd = null;
    private Button btnSelect = null;

    private LinearLayout llTitle = null;
    private ListView lvResult = null;

    private MyDateBase myDateBase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etContext = findViewById(R.id.etContext);
        etTime = findViewById(R.id.etTime);
        btnSelectTime = findViewById(R.id.btnSelectTime);
        btnAdd = findViewById(R.id.btnAdd);
        btnSelect = findViewById(R.id.btnSelect);
        llTitle = findViewById(R.id.llTitle);
        lvResult = findViewById(R.id.lvResult);

        MyListener myListener = new MyListener();
        btnSelectTime.setOnClickListener(myListener);
        btnSelect.setOnClickListener(myListener);
        btnAdd.setOnClickListener(myListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null == myDateBase){
            myDateBase.close();
        }
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.btnSelectTime){
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                etTime.setText(year+" "+(month+1)+" "+dayOfMonth);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }else{
                myDateBase = new MyDateBase(MainActivity.this, "notepad.db", null, 1);

                SQLiteDatabase database = myDateBase.getReadableDatabase();

                String title = etTitle.getText().toString().trim();
                String context = etContext.getText().toString().trim();
                String dateStr = etTime.getText().toString().trim();

                switch (v.getId()){

                    case R.id.btnAdd:
                        llTitle.setVisibility(View.INVISIBLE);
                        addData(database, title, context, dateStr);
                        Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                        lvResult.setAdapter(null);
                        break;

                    case R.id.btnSelect:
                        llTitle.setVisibility(View.VISIBLE);
                        Cursor cursor = queryData(database, title, context, dateStr);
                        SimpleCursorAdapter resultAdapter = new SimpleCursorAdapter(
                                MainActivity.this,
                                R.layout.listview_layout,
                                cursor,
                                new String[]{"_id", "title","context","date"},
                                new int[]{R.id.tvResultID, R.id.tvResultTheme, R.id.tvResultContext, R.id.tvResultDate}
                        );

                        lvResult.setAdapter(resultAdapter);
                        break;
                    default:
                        break;
                }
            }


        }
    }

    private Cursor queryData(SQLiteDatabase database, String title, String context, String dateStr) {
        Cursor cursor = database.rawQuery("select * from notepad_tb where title like ? and context like ? and date like ?",
                new String[]{"%"+title+"%","%"+context+"%","%"+dateStr+"%"});
        return cursor;
    }

    private void addData(SQLiteDatabase db, String theme, String context, String date) {
        db.execSQL("insert into notepad_tb values(null,?,?,?)", new String[]{theme, context, date});
        etTitle.setText("");
        etContext.setText("");
        etTime.setText("");
    }

}

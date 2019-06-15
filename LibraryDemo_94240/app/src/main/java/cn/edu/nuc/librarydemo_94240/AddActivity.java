package cn.edu.nuc.librarydemo_94240;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private Button btnBookAdd = null;
    private EditText tvBookName = null;
    private EditText tvBookAuthor = null;
    private EditText tvBookCount = null;
    private MyDataBase myDataBase = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();



        btnBookAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author = tvBookAuthor.getText().toString();
                String count = tvBookCount.getText().toString();
                String name = tvBookName.getText().toString();
                myDataBase = new MyDataBase(AddActivity.this, "library.db", null, 1);
                SQLiteDatabase database = myDataBase.getReadableDatabase();
                Cursor cursor = database.rawQuery("select * from library_tb where name = ?",new String[]{name});
                myDataBase = new MyDataBase(AddActivity.this, "library.db", null, 1);
                if(0 == cursor.getCount()) {
                    database.execSQL("insert into library_tb values(null,?,?,?)", new String[]{name, author, count});
                    Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();

                }else{
                    database.execSQL("update library_tb SET author = ? ,count = ? where name = ?", new String[]{author, count, name });
                    Toast.makeText(AddActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
                tvBookName.setText("");
                tvBookAuthor.setText("");
                tvBookCount.setText("");
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @SuppressLint("WrongViewCast")
    private void init(){
        btnBookAdd = findViewById(R.id.btnBookAdd);
        tvBookName = findViewById(R.id.tvBookName);
        tvBookAuthor = findViewById(R.id.tvBookAuthor);
        tvBookCount = findViewById(R.id.tvBookCount);
    }
}

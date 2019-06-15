package cn.edu.nuc.librarydemo_94240;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd = null;
    private Button btnDelete = null;
    private Button btnSelect = null;
    private ListView listView = null;
    private EditText etSelect = null;

    private MyDataBase myDataBase = null;
    private LinearLayout llTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        MyListener myListener = new MyListener();
        btnAdd.setOnClickListener(myListener);
        btnSelect.setOnClickListener(myListener);
        btnDelete.setOnClickListener(myListener);

        view();

    }

    private void init(){
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnSelect = findViewById(R.id.btnSelect);
        listView = findViewById(R.id.listView);
        llTitle = findViewById(R.id.llTitle);
        etSelect = findViewById(R.id.etSelect);
        myDataBase = new MyDataBase(MainActivity.this, "library.db", null, 1);

    }
    private void view(){
        llTitle.setVisibility(View.VISIBLE);
        SQLiteDatabase database = myDataBase.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from library_tb",null);
        SimpleCursorAdapter resultAdapter = new SimpleCursorAdapter(
                MainActivity.this,
                R.layout.listview_layout,
                cursor,
                new String[]{"_id", "name","author","count"},
                new int[]{R.id.tvId, R.id.tvName, R.id.tvAuthor, R.id.tvCount}
        );
        listView.setAdapter(resultAdapter);
    }


    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            SQLiteDatabase database = myDataBase.getReadableDatabase();
            String name = etSelect.getText().toString();
            switch (v.getId()){
                case R.id.btnAdd:
                    Intent intent = new Intent(MainActivity.this, AddActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnSelect:
                    Cursor cursor = database.rawQuery("select * from library_tb where name = ?",new String[]{name});
                    SimpleCursorAdapter resultAdapter = new SimpleCursorAdapter(
                            MainActivity.this,
                            R.layout.listview_layout,
                            cursor,
                            new String[]{"_id", "name","author","count"},
                            new int[]{R.id.tvId, R.id.tvName, R.id.tvAuthor, R.id.tvCount}
                    );
                    listView.setAdapter(resultAdapter);
                    break;
                case R.id.btnDelete:
                    database.execSQL("delete from library_tb where name = ?",new String[]{name});
                    view();
                    break;
                default:
                    break;
            }
        }
    }
}

package cn.edu.nuc.accesscontactsdemo_94240;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etName = null;
    private EditText etNumber = null;
    private Button btnAdd = null;
    private Button btnSelect = null;

    private LinearLayout titlell = null;
    private ListView contentLv = null;

    private ContentResolver resolver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        MyListener myListener = new MyListener();

        btnSelect.setOnClickListener(myListener);
        btnAdd.setOnClickListener(myListener);



    }

    private void init(){
        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);

        btnAdd = findViewById(R.id.btnAdd);
        btnSelect = findViewById(R.id.btnSelect);

        titlell = findViewById(R.id.titlell);
        contentLv = findViewById(R.id.contentLv);

        resolver = getContentResolver();
    }

    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnAdd:

                    if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CONTACTS)
                            != PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.WRITE_CONTACTS},  1);

                    }

                    addContacts();

                    break;

                case R.id.btnSelect:
                    if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CONTACTS)
                            != PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.WRITE_CONTACTS},  2);

                    }

                    ArrayList<Map<String,String>> persons = SelectContent();
                    titlell.setVisibility(View.VISIBLE);

                    SimpleAdapter simpleAdapter = new SimpleAdapter(
                            MainActivity.this,
                            persons,
                            R.layout.listview,
                            new String[]{"id", "name", "num"},
                            new int[]{R.id.tvId, R.id.tvName, R.id.tvNumber}
                    );
                    contentLv.setAdapter(simpleAdapter);

                    break;

                default:
                    break;
            }
        }

        private ArrayList<Map<String, String>> SelectContent() {

            ArrayList<Map<String, String>> details = new ArrayList<Map<String, String>>();

            Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);

            while(cursor.moveToNext()){
                Map<String, String> person = new HashMap<String, String>();

                String personId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String personName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                person.put("id",personId);
                person.put("name",personName);

                Cursor numberCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + personId, null, null);
                if (numberCursor.moveToNext()){
                    String personNumber = numberCursor.getString(numberCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    person.put("num",personNumber);
                    numberCursor.close();
                }

                details.add(person);
            }

            cursor.close();

            return details;
        }

        private void addContacts() {
            String nameStr = etName.getText().toString().trim();
            String numberStr = etNumber.getText().toString().trim();

            ContentValues values = new ContentValues();

            Uri rawContentUri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);

            long contactId = ContentUris.parseId(rawContentUri);

            values.clear();
            values.put(ContactsContract.Data.RAW_CONTACT_ID,contactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, nameStr);
            resolver.insert(ContactsContract.Data.CONTENT_URI, values);
            values.clear();

            values.put(ContactsContract.Data.RAW_CONTACT_ID, contactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,numberStr);
            values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            resolver.insert(ContactsContract.Data.CONTENT_URI,values);
            Toast.makeText(MainActivity.this, "添加联系人成功！！", Toast.LENGTH_SHORT).show();
        }
    }

}

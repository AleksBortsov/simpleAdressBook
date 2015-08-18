package com.example.aleksandr.simplesql;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btnCreate;
    EditText etSimple, etMail, etTelephone;
    TextView tvName, tvTele, tvMail;
    DBHelper dbHelper;


    private static final String LOG_TAG = "Logs";
    private static final String NAME = "name";
    private static final String MAIL = "mail";
    private static final String TABLE = "myTable";
    private static final String TELE = "telephone";
    private static final int NUMBER_DB = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        etSimple = (EditText) findViewById(R.id.etSimple);
        etMail = (EditText) findViewById(R.id.etMail);
        etTelephone = (EditText) findViewById(R.id.etTelephone);

        dbHelper = new DBHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        ContentValues contentValues = new ContentValues();

        String name = etSimple.getText().toString();
        String mail = etMail.getText().toString();
        String telephone = etTelephone.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.btnCreate:
                Log.d(LOG_TAG, "--Insert my DataBase---");
                contentValues.put(NAME, name);
                contentValues.put(MAIL, mail);
                contentValues.put(TELE, telephone);

                long rowID = db.insert(TABLE, null, contentValues);
                Log.d(LOG_TAG, "---row Insert Id--- " + rowID);

                Intent intent = new Intent(this, ActivitySave.class);
                intent.putExtra(MAIL, etMail.getText().toString());
                intent.putExtra(TELE, etTelephone.getText().toString());
                intent.putExtra(MAIL, etMail.getText().toString());
                startActivity(intent);
                break;
        }

        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, TABLE, null, NUMBER_DB);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "---create DataBase---");
            db.execSQL("create table " + TABLE + "  ("
                    + "id integer primary key autoincrement, "
                    + NAME + " text, "
                    + MAIL + " text, "
                    + TELE + " text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
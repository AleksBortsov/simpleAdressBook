package com.example.aleksandr.simplesql;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by aleksandr on 18.08.2015.
 */
public class ActivityEnterDataOfContact extends Activity implements View.OnClickListener {

    private static final String LOG_TAG = "Logs";
    private static final String NAME = "name";
    private static final String MAIL = "mail";
    private static final String TABLE = "Contacts";
    private static final String TELE = "telephone";
    private static final String LAST_NAME = "lastName";
    private static final int NUMBER_DB = 1;
    private static final String ID = "id";


    EditText etName, etMail, etTelephone, etLastName;
    Button btnOk;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityenterdataofcontact);

        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etMail);
        etTelephone = (EditText) findViewById(R.id.etTelephone);
        etLastName = (EditText) findViewById(R.id.etLastName);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {

        ContentValues contentValues = new ContentValues();

        // заполняем переменные с ввода текста
        String name = etName.getText().toString();
        String mail = etMail.getText().toString();
        String lastName = etLastName.getText().toString();
        String telephone = etTelephone.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d(LOG_TAG, "---Insert Contscts---");
        contentValues.put(NAME, name);
        contentValues.put(LAST_NAME, lastName);
        contentValues.put(TELE, telephone);
        contentValues.put(MAIL, mail);

        long rowId = db.insert(TABLE, null, contentValues);
        Log.d(LOG_TAG, "---row id--- " + rowId);

        Intent intent = new Intent();
        intent.putExtra(NAME, etName.getText().toString());
        intent.putExtra(MAIL, etMail.getText().toString());
        intent.putExtra(TELE, etTelephone.getText().toString());
        intent.putExtra(LAST_NAME, etLastName.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
        dbHelper.close();

    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, TABLE, null, NUMBER_DB);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE + " ("
                    + ID + " integer primary key autoincrement, "
                    + NAME + " text, "
                    + LAST_NAME + " text, "
                    + MAIL + " text, "
                    + TELE + " text );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

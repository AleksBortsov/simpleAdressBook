package com.example.aleksandr.simplesql;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

        //
        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {

        // создаем объект для
        ContentValues contentValues = new ContentValues();

        // заполняем переменные с ввода текста
        String name = etName.getText().toString();
        String mail = etMail.getText().toString();
        String lastName = etLastName.getText().toString();
        String telephone = etTelephone.getText().toString();

        // Подклчаемся к базе данных
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d(LOG_TAG, "---Insert Contscts---");
        //вкладываем значения в базу данных
        contentValues.put(NAME, name);
        contentValues.put(LAST_NAME, lastName);
        contentValues.put(TELE, telephone);
        contentValues.put(MAIL, mail);

        long rowId = db.insert(TABLE, null, contentValues);
        Log.d(LOG_TAG, "---row id--- " + rowId);

        // создаем переход на начальный экран с вводом имени и фамилии
        Intent intent = new Intent();
        intent.putExtra(NAME, etName.getText().toString());
        intent.putExtra(MAIL, etMail.getText().toString());
        intent.putExtra(TELE, etTelephone.getText().toString());
        intent.putExtra(LAST_NAME, etLastName.getText().toString());

        // результат ввода
        setResult(RESULT_OK, intent);

        Log.d(LOG_TAG, "--rows in my Table---");

        Cursor cursor = db.query(TABLE, null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int indexColimID = cursor.getColumnIndex(ID);
            int indexColumName = cursor.getColumnIndex(NAME);
            int indexColumLastName = cursor.getColumnIndex(LAST_NAME);
            do {

                Contact contact=new Contact(cursor.getString(indexColumName),getString(indexColumLastName));

                Log.d(LOG_TAG, "ID= " + cursor.getInt(indexColimID)
                        + "; name= " + cursor.getString(indexColumName)
                        + "; lastName= " + cursor.getString(indexColumLastName));

            } while (cursor.moveToNext());
        } else
            Log.d(LOG_TAG, "---NO ROWS---" + rowId);
        cursor.close();

        // закрываем сбыти intent
        finish();

        // закрываем бвзуданных
        dbHelper.close();

    }

    // вспомогательный класс для создания базы данных
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

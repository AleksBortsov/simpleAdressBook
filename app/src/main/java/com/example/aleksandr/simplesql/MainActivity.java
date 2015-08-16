package com.example.aleksandr.simplesql;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btnCreate;
    EditText etSimple, etMail;
    TextView tvView;

    DBHelper dbHelper;
    final String LOG_TAG = "Logs";

    private static final String NAME = "name";
    private static final String MAIL = "mail";
    private static final String TABLE = "myTable";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvView = (TextView) findViewById(R.id.tvView);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        etSimple = (EditText) findViewById(R.id.etSimple);
        etMail = (EditText) findViewById(R.id.etMail);

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

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.btnCreate:
                Log.d(LOG_TAG, "--Insert my DataBase---");
                contentValues.put(NAME, name);
                contentValues.put(MAIL, mail);

                long rowID = db.insert(TABLE, null, contentValues);
                Log.d(LOG_TAG, "---row Insert Id--- " + rowID);
                tvView.setText("You name: " + name + "  " + "You mail: " + mail);
                break;
        }

        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, TABLE, null, 1);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "---create DataBase---");
            db.execSQL("create table " + TABLE + "  ("
                    + "id integer primary key autoincrement, "
                    + NAME + " text"
                    + MAIL + " text" + " );");
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}

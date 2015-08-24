package com.example.aleksandr.simplesql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "Logs";
    private static final String NAME = "name";
    private static final String MAIL = "mail";
    private static final String TABLE = "Contacts";
    private static final String TELE = "telephone";
    private static final String LAST_NAME = "lastName";
    private static final int NUMBER_DB = 1;

    Button btnCreate;
    TextView tvMainName, tvMainLastName;
    Layout ll5;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        tvMainName = (TextView) findViewById(R.id.tvMainName);
        tvMainName.setOnClickListener(this);

        tvMainLastName = (TextView) findViewById(R.id.tvMainLastName);

        //-------------------NEW PART OF CLASS--------------------

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewXml);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);





        //mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreate:
                Intent intent = new Intent(this, ActivityEnterDataOfContact.class);
                startActivityForResult(intent, NUMBER_DB);
                break;
            case R.id.ll5:
                Intent intent1 = new Intent(this, ActivityVisuallyWhatSave.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String name = data.getStringExtra(NAME);
        String lastName = data.getStringExtra(LAST_NAME);
        tvMainName.setText("LastName=" + lastName + "; ");
        tvMainLastName.setText("Name " + name);

        //Log.d(LOG_TAG, "---rows in my table: ");
        // Cursor cursor=;
    }
}
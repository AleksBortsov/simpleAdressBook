package com.example.aleksandr.simplesql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aleksandr on 17.08.2015.
 */
public class ActivityVisuallyWhatSave extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "Logs";
    private static final String NAME = "name";
    private static final String MAIL = "mail";
    private static final String TELE = "telephone";
    TextView tvName, tvTele, tvMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityvisualitywhatsave);

        tvName = (TextView) findViewById(R.id.tvName);
        tvTele = (TextView) findViewById(R.id.tvTele);
        tvMail = (TextView) findViewById(R.id.tvMail);

        Intent intent = getIntent();

        tvName.setText("Name: " + intent.getStringExtra(NAME));
        tvMail.setText("Mail: " + intent.getStringExtra(MAIL));
        tvTele.setText("Telephone: " + intent.getStringExtra(TELE));
    }

    @Override
    public void onClick(View v) {
    }
}
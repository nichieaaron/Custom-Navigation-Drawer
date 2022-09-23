package com.shrikanthravi.customnavigationdrawer.swipeviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.shrikanthravi.customnavigationdrawer.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        TextView textView = findViewById(R.id.detailTV);
        textView.setText(getIntent().getStringExtra("param"));
    }
}
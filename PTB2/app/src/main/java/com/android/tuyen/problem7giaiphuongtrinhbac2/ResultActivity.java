package com.android.tuyen.problem7giaiphuongtrinhbac2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myBundle");

        //Take 2 values a and b from the bundle
        float a = bundle.getFloat("numberA", 0);
        float b = bundle.getFloat("numberB", 0);

        giaiPTBN(a, b);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void initView(){
        btnBack = findViewById(R.id.btnBack);
        tvResult = findViewById(R.id.tvResult);
    }

    void giaiPTBN(float a, float b) {
        if (a == 0) {
            if (b == 0) {
                tvResult.setText("Phương trình vô số nghiệm");
            } else {
                tvResult.setText("Phương trình vô nghiệm");
            }
        } else {
            tvResult.setText("" + (float) -b / a);
        }
    }
}

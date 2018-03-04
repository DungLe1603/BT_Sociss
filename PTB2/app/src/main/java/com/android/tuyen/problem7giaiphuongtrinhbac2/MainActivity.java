package com.android.tuyen.problem7giaiphuongtrinhbac2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtInputA;
    private EditText edtInputB;
    private Button btnResult;
    SharedPreferences pre;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a, b;
                try {
                    a = Float.parseFloat(edtInputA.getText().toString());
                    b = Float.parseFloat(edtInputB.getText().toString());
                    //Put the data you want to save in the variable edit
                    edit.putFloat("numberA", a);
                    edit.putFloat("numberB", b);

                    //set status == true when move screen
                    edit.putBoolean("status", true);
                    //save data
                    edit.commit();

                    //Creates the intent to pass the ResultActivity screen
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);

                    //Create bundles to encapsulate data
                    Bundle bundle = new Bundle();

                    bundle.putFloat("numberA", a);
                    bundle.putFloat("numberB", b);

                    intent.putExtra("myBundle", bundle);

                    startActivity(intent);      //start ResultActivity

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Có lỗi trong quá trình xử lý!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (pre.getBoolean("status", false)) {
            edtInputA.setText("0");
            edtInputB.setText("0");
            Toast.makeText(this, "Wellcome back to MainActivity ! Your last edit text : a = " + pre.getFloat("numberA", 0) + ", b = " + pre.getFloat("numberB", 0), Toast.LENGTH_SHORT).show();
        }
    }
    public void initView() {
        edtInputA = (EditText) findViewById(R.id.edtInputA);
        edtInputB = (EditText) findViewById(R.id.edtInputB);
        btnResult = (Button) findViewById(R.id.btnResult);

        pre = getSharedPreferences("dataResult", MODE_PRIVATE);
        edit = pre.edit();

        //set status when run app = false
        edit.putBoolean("status", false);
        edit.commit();
    }
}

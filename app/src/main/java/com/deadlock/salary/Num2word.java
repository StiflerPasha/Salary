package com.deadlock.salary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Num2word extends AppCompatActivity {

    TextView tv_trans, tv_gotIt;
    EditText et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num2word);

        tv_trans = (TextView) findViewById(R.id.tv_trans);
        tv_gotIt = (TextView) findViewById(R.id.tv_gotIt);
        et_number = (EditText) findViewById(R.id.et_number);



        tv_gotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_number.getText().toString().equals("")) {
                    int num = Integer.parseInt(et_number.getText().toString());
                    tv_trans.setText(NumberToString.WordsRus(num));
                    tv_trans.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}

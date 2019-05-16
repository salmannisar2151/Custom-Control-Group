package com.example.salman.customsegmentcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.salman.segmentedradiogroup.CustomRadioGroup;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CustomRadioGroup customRadioGroup;
    EditText edtTitle, edtWidth;
    Button btnAdd, btnRemove;
    ArrayList<Integer> colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colors = new ArrayList<>();
        setColors();
        customRadioGroup = findViewById(R.id.rg_custom_group);
        customRadioGroup.updateAllRadioButtonsBackground();
        btnAdd = findViewById(R.id.btn_add);
        btnRemove = findViewById(R.id.btn_remove);
        edtTitle = findViewById(R.id.edt_title);
        edtWidth = findViewById(R.id.edt_width);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadioGroup.getChildCount() > 0) {
                    customRadioGroup.removeTab();
                } else
                    Toast.makeText(getApplicationContext(), "List id empty", Toast.LENGTH_SHORT).show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edtTitle.getText())) {
                    customRadioGroup.addRadioButton(edtTitle.getText().toString());
                    edtTitle.setText("");
                }
                else
                    Toast.makeText(getApplicationContext(), "Please enter title", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setColors() {
        colors.add(R.color._0);
        colors.add(R.color._1);
        colors.add(R.color._2);
        colors.add(R.color._3);
        colors.add(R.color._4);
        colors.add(R.color._5);
        colors.add(R.color._6);
        colors.add(R.color._7);
        colors.add(R.color._8);
        colors.add(R.color._9);
        colors.add(R.color._10);
        colors.add(R.color._11);
        colors.add(R.color._12);
        colors.add(R.color._13);
        colors.add(R.color._14);
        colors.add(R.color._15);
        colors.add(R.color._16);
        colors.add(R.color._17);
        colors.add(R.color._18);
        colors.add(R.color._19);
        colors.add(R.color._20);
        colors.add(R.color._21);
        colors.add(R.color._22);
        colors.add(R.color._23);
    }

    int getRandomNumber() {
        Random rand = new Random();

// Obtain a number between [0 - 49].
        int n = rand.nextInt(colors.size());

// Add 1 to the result to get a number from the required range
// (i.e., [1 - 50]).
        return n;
    }

    public void strokeWidth(View view) {
        customRadioGroup.setStrokeWidth(Integer.parseInt(edtWidth.getText().toString()));
    }


    public void textUnSelected(View view) {
        customRadioGroup.setuTextColor(colors.get(getRandomNumber()));
    }

    public void textSelected(View view) {
        customRadioGroup.setsTextColor(colors.get(getRandomNumber()));
    }

    public void border(View view) {
        customRadioGroup.setuBorderColor(getResources().getColor(colors.get(getRandomNumber())));
    }

    public void background(View view) {
        customRadioGroup.setsBackgroundColor(getResources().getColor(colors.get(getRandomNumber())));
    }
}

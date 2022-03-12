package com.example.compoundinterestcalculator;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {

    private EditText user_field;
    private EditText user_field2;
    private EditText user_field3;
    private EditText user_field4;

    private Button main_btn;
    private LineChartView chart;

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        initView();
        initListeners();
        return super.onCreateView(parent, name, context, attrs);
    }

    public void initView(){
        user_field = findViewById(R.id.InitialAmount);
        user_field2 = findViewById(R.id.Contribution);
        user_field3 = findViewById(R.id.InterestRate);
        user_field4 = findViewById(R.id.Years);
        main_btn = findViewById(R.id.main_btn);
        chart = findViewById(R.id.chart);
    }

    public void initListeners(){
        main_btn.setOnClickListener(view -> {
            String InitialAmountText = user_field.getText().toString();
            String ContributionText = user_field2.getText().toString();
            String InterestRateText = user_field3.getText().toString();
            String YearsText = user_field4.getText().toString();
            calculatePercent(InitialAmountText, ContributionText, InterestRateText, YearsText);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculatePercent(String InitialAmountText, String  ContributionText, String InterestRateText, String YearsText) {
        Integer InitialAmount = Integer.valueOf(InitialAmountText);
        Integer Contribution = Integer.valueOf(ContributionText);
        Integer InterestRate = Integer.valueOf(InterestRateText);
        Integer Years = Integer.valueOf(YearsText);

        Integer result = InitialAmount * (InterestRate / 10);
        Integer result2 = result * (InterestRate / 10);
        Integer result3 = result2 * (InterestRate / 10);
        Integer result4 = result3 * (InterestRate / 10);
        Integer result5 = result4 * (InterestRate / 10);
    }



}
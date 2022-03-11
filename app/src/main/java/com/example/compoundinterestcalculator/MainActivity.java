package com.example.compoundinterestcalculator;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Spinner spinner1 = findViewById(R.id.spinner1);
        //Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner1.setAdapter(adapter);
        //spinner2.setAdapter(adapter);

        user_field = findViewById(R.id.InitialAmount);
        user_field2 = findViewById(R.id.Contribution);
        user_field3 = findViewById(R.id.InterestRate);
        user_field4 = findViewById(R.id.Years);
        main_btn = findViewById(R.id.main_btn);
        chart = findViewById(R.id.chart);


        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String spinnerText1 = spinner1.getSelectedItem().toString();
                //String spinnerText2 = spinner2.getSelectedItem().toString();
                String InitialAmountText = user_field.getText().toString();
                String ContributionText = user_field2.getText().toString();
                String InterestRateText = user_field3.getText().toString();
                String YearsText = user_field4.getText().toString();
                Integer InitialAmount = Integer.valueOf(InitialAmountText);
                Integer Contribution = Integer.valueOf(ContributionText);
                Integer InterestRate = Integer.valueOf(InterestRateText);
                Integer Years = Integer.valueOf(YearsText);


                // передать их в метод
                calculatePercent(InitialAmount, Contribution, InterestRate, Years);
            }
        });

    }

    public void calculatePercent(int InitialAmount, int Contribution, int InterestRate, int Years) {
        //вывести значение для второго периода в лог
        // потом для 3х периодов

        Integer result = InitialAmount * (InterestRate / 10);
        Integer result2 = result * (InterestRate / 10);
        Integer result3 = result2 * (InterestRate / 10);
        Integer result4 = result3 * (InterestRate / 10);
        Integer result5 = result4 * (InterestRate / 10);
        Log.i(TAG, "calculatePercent: fdsfsddsffs" + result + " " + result2 + " " + result3 + " " + result4 + " " + result5);


        Axis x = new Axis();
        ChartData a = chart.getChartData();
        a.setAxisXBottom(x);
        a.setAxisYLeft(x);
        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(1, result));
        values.add(new PointValue(2, result2));
        values.add(new PointValue(3, result3));
        values.add(new PointValue(4, result4));
        values.add(new PointValue(5, result5));
        ChartData chartData = chart.getChartData();
        LineChartData lineChartData = chart.getLineChartData();


        Line line = new Line(values).setColor(Color.GREEN).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        lineChartData.setLines(lines);
    }
}
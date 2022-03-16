package com.example.compoundinterestcalculator;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static lecho.lib.hellocharts.gesture.ZoomType.HORIZONTAL_AND_VERTICAL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        initView();
        initListeners();
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

    public void calculatePercent(String InitialAmountText, String  ContributionText, String InterestRateText, String YearsText) {

        Integer initialAmount = stringToInt(InitialAmountText);
        Integer contribution = stringToInt(ContributionText);
        Integer interestRate = stringToInt(InterestRateText);
        Integer years = stringToInt(YearsText);

        List<PointValue> points = calculatePointValues(initialAmount, contribution, interestRate, years);
        drawChart(points);
    }


    public List<PointValue> calculatePointValues(Integer initialAmount, Integer contributionAmount, Integer interestRate, Integer years) {
        List<PointValue> points = new ArrayList<>();

        float decimalInterestRate = interestRate.floatValue()/100;

        float previousSum = initialAmount;

        for(float i=0; i<years; i++){
            float resultSum = (previousSum + contributionAmount) * decimalInterestRate + (previousSum + contributionAmount);
            previousSum = resultSum;
            points.add(new PointValue(i, resultSum));
        }
        return points;
    }

    public static int stringToInt(String param) {
        try {
            return Integer.valueOf(param);
        } catch(NumberFormatException e) {
            return 0;
        }
    }

    public void drawChart(List<PointValue> values){
        chart.setInteractive(false);

        Axis axisX = new Axis();
        axisX.setMaxLabelChars(6);
        axisX.setTextColor(Color.BLACK);
        axisX.setLineColor(Color.BLACK);

        Axis axisY = new Axis();
        axisY.setMaxLabelChars(6);
        axisY.setTextColor(Color.BLACK);
        axisY.setLineColor(Color.BLACK);

        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        chart.setLineChartData(data);


        //вызывается всегда в конце
        ChartData chartData = chart.getChartData();
        chartData.setAxisXBottom(axisX);
        chartData.setAxisYLeft(axisY);
        chartData.setValueLabelBackgroundColor(Color.BLACK);
        chartData.setValueLabelsTextColor(Color.BLACK);
        chartData.finish();

        chart.setVisibility(View.VISIBLE);

    }
}
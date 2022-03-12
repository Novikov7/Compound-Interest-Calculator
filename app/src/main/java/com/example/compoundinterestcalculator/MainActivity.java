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

        Integer InitialAmount = stringToInt(InitialAmountText);
        Integer Contribution = stringToInt(ContributionText);
        Integer InterestRate = stringToInt(InterestRateText);
        Integer Years = stringToInt(YearsText);


        /*Integer result = InitialAmount * (InterestRate / 10);
        Integer result2 = result * (InterestRate / 10);
        Integer result3 = result2 * (InterestRate / 10);
        Integer result4 = result3 * (InterestRate / 10);
        Integer result5 = result4 * (InterestRate / 10);*/
         drawChart();


    }

    public static int stringToInt(String param) {
        try {
            return Integer.valueOf(param);
        } catch(NumberFormatException e) {
            return 0;
        }
    }

    public void drawChart(){
        chart.setInteractive(false);

        Axis axisX = Axis.generateAxisFromRange(0,20,1);
        Axis axisY = Axis.generateAxisFromRange(0,20,1);

        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 1));
        values.add(new PointValue(0.5F, 4));
        values.add(new PointValue(1.3F, 5));
        values.add(new PointValue(1.7F, 6));
        values.add(new PointValue(2, 7));
        values.add(new PointValue(2.5F, 8));
        values.add(new PointValue(2.8F, 9));
        values.add(new PointValue(3, 10));
        values.add(new PointValue(3.5F, 11));
        values.add(new PointValue(4, 15));

        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        chart.setLineChartData(data);

        ChartData chartData = chart.getChartData();
        chartData.setAxisXBottom(axisX);
        chartData.setAxisYLeft(axisY);
        chartData.setValueLabelBackgroundColor(Color.BLACK);
        chartData.setValueLabelsTextColor(Color.BLACK);
        chartData.finish();

        chart.setVisibility(View.VISIBLE);

    }
}
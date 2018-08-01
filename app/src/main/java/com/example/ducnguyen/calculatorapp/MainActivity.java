package com.example.ducnguyen.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements StandardModeFragment.OnViewClickedListener {
    private TextView mTextViewExpression;
    private StringBuilder mExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewExpression = findViewById(R.id.text_expression);
        mExpression = new StringBuilder();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_calculator, new StandardModeFragment()).commit();
    }

    @Override
    public void addToExpression(String textToExpression) {
        mExpression.append(textToExpression);
        mTextViewExpression.setText(mExpression.toString());
    }
}

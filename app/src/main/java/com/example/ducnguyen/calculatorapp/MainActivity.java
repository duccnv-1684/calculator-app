package com.example.ducnguyen.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements StandardModeFragment.OnViewClickedListener {
    private TextView mTextViewExpression, mTextViewResult;
    private StringBuilder mExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewExpression = findViewById(R.id.text_expression);
        mTextViewResult = findViewById(R.id.text_result);
        mExpression = new StringBuilder();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_calculator, new StandardModeFragment()).commit();
    }

    @Override
    public void addToExpression(char textToExpression) {
        mExpression.append(textToExpression);
        mTextViewExpression.setText(mExpression.toString());
    }

    @Override
    public void calculate() {
        if (!Calculator.checkSyntaxExpression(mExpression.toString())){
            mTextViewResult.setText(R.string.syntax_error);
        }else {
            double result = Calculator.calculate(mExpression.toString());
            mTextViewResult.setText(String.valueOf(result));
            mExpression = new StringBuilder();
            mTextViewExpression.setText(mExpression);
        }
    }

    @Override
    public void allClear() {
        mTextViewResult.setText(R.string.number_zero);
        mExpression = new StringBuilder();
        mTextViewExpression.setText(mExpression);
    }
}

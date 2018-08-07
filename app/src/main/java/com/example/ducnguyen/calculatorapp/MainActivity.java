package com.example.ducnguyen.calculatorapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements StandardModeFragment.OnViewClickedListener, PopupMenu.OnMenuItemClickListener {
    private static final String PREF_NAME = "SHARE_PREFS";
    private static final String PREF_RESULT = "SHARE_RESULT";
    private SharedPreferences mSharedPreferences;
    private TextView mTextViewExpression, mTextViewResult;
    private StringBuilder mExpression;
    private float mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        mResult = mSharedPreferences.getFloat(PREF_RESULT, 0);
        mTextViewExpression = findViewById(R.id.text_expression);
        mTextViewResult = findViewById(R.id.text_result);
        mTextViewResult.setText(String.valueOf(mResult));
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
        if (!Calculator.checkSyntaxExpression(mExpression.toString())) {
            mTextViewResult.setText(R.string.syntax_error);
        } else {
            mResult = Calculator.calculate(mExpression.toString());
            mTextViewResult.setText(String.valueOf(mResult));
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

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_save_result:
                mSharedPreferences.edit().putFloat(PREF_RESULT, mResult).apply();
                Toast.makeText(this, R.string.msg_saved, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_clean_result:
                mSharedPreferences.edit().clear().apply();
                Toast.makeText(this, R.string.msg_cleaned, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    public void showPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.activity_main, popupMenu.getMenu());
        popupMenu.show();
    }
}

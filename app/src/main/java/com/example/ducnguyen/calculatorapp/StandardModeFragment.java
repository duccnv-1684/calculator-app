package com.example.ducnguyen.calculatorapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StandardModeFragment extends Fragment implements View.OnClickListener {
    private static final String ZERO = "0";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";
    private static final String FIVE = "5";
    private static final String SIX = "6";
    private static final String SEVEN = "7";
    private static final String EIGHT = "8";
    private static final String NINE = "9";
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MUL = "*";
    private static final String DIV = "/";
    private static final String PERCENT = "%";
    private static final String NEGATIVE_POSITIVE = "NP";
    private static final String ALL_CLEAR = "AC";
    private static final String DOT = ".";
    private static final String EQUAL = "=";
    private OnViewClickedListener mOnViewClickedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnViewClickedListener = (OnViewClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_standard_mode, container
                , false);
        setupViewInFragment(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        String textToExpression;
        switch (view.getId()) {
            case R.id.tv_number_zero:
                textToExpression = ZERO;
                break;
            case R.id.tv_number_one:
                textToExpression = ONE;
                break;
            case R.id.tv_number_two:
                textToExpression = TWO;
                break;
            case R.id.tv_number_three:
                textToExpression = THREE;
                break;
            case R.id.tv_number_four:
                textToExpression = FOUR;
                break;
            case R.id.tv_number_five:
                textToExpression = FIVE;
                break;
            case R.id.tv_number_six:
                textToExpression = SIX;
                break;
            case R.id.tv_number_seven:
                textToExpression = SEVEN;
                break;
            case R.id.tv_number_eight:
                textToExpression = EIGHT;
                break;
            case R.id.tv_number_nine:
                textToExpression = NINE;
                break;
            case R.id.tv_dot:
                textToExpression = DOT;
                break;
            case R.id.tv_operator_add:
                textToExpression = ADD;
                break;
            case R.id.tv_operator_sub:
                textToExpression = SUB;
                break;
            case R.id.tv_operator_mul:
                textToExpression = MUL;
                break;
            case R.id.tv_operator_div:
                textToExpression = DIV;
                break;
            case R.id.tv_operator_percent:
                textToExpression = PERCENT;
                break;
            case R.id.tv_operator_negative_positive:
                textToExpression = NEGATIVE_POSITIVE;
                break;
            case R.id.tv_all_clear:
                textToExpression = ALL_CLEAR;
                break;
            case R.id.tv_operator_equal:
                textToExpression = EQUAL;
                break;
            default:
                textToExpression = null;
                break;
        }
        mOnViewClickedListener.addToExpression(textToExpression);
    }

    private void setupViewInFragment(View view) {
        int[] viewIds = {R.id.tv_number_zero, R.id.tv_number_one, R.id.tv_number_two
                , R.id.tv_number_three, R.id.tv_number_four, R.id.tv_number_five
                , R.id.tv_number_six, R.id.tv_number_seven, R.id.tv_number_eight
                , R.id.tv_number_nine, R.id.tv_operator_add, R.id.tv_operator_sub
                , R.id.tv_operator_mul, R.id.tv_operator_div, R.id.tv_operator_negative_positive
                , R.id.tv_dot, R.id.tv_operator_percent, R.id.tv_operator_equal, R.id.tv_all_clear};
        for (int viewId : viewIds) {
            view.findViewById(viewId).setOnClickListener(this);
        }
    }

    public interface OnViewClickedListener {
        void addToExpression(String textToExpression);
    }
}

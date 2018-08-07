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
        switch (view.getId()) {
            case R.id.tv_number_zero:
                mOnViewClickedListener.addToExpression(Constant.ZERO);
                break;
            case R.id.tv_number_one:
                mOnViewClickedListener.addToExpression(Constant.ONE);
                break;
            case R.id.tv_number_two:
                mOnViewClickedListener.addToExpression(Constant.TWO);
                break;
            case R.id.tv_number_three:
                mOnViewClickedListener.addToExpression(Constant.THREE);
                break;
            case R.id.tv_number_four:
                mOnViewClickedListener.addToExpression(Constant.FOUR);
                break;
            case R.id.tv_number_five:
                mOnViewClickedListener.addToExpression(Constant.FIVE);
                break;
            case R.id.tv_number_six:
                mOnViewClickedListener.addToExpression(Constant.SIX);
                break;
            case R.id.tv_number_seven:
                mOnViewClickedListener.addToExpression(Constant.SEVEN);
                break;
            case R.id.tv_number_eight:
                mOnViewClickedListener.addToExpression(Constant.EIGHT);
                break;
            case R.id.tv_number_nine:
                mOnViewClickedListener.addToExpression(Constant.NINE);
                break;
            case R.id.tv_dot:
                mOnViewClickedListener.addToExpression(Constant.DOT);
                break;
            case R.id.tv_operator_add:
                mOnViewClickedListener.addToExpression(Constant.ADD);
                break;
            case R.id.tv_operator_sub:
                mOnViewClickedListener.addToExpression(Constant.SUB);
                break;
            case R.id.tv_operator_mul:
                mOnViewClickedListener.addToExpression(Constant.MUL);
                break;
            case R.id.tv_operator_div:
                mOnViewClickedListener.addToExpression(Constant.DIV);
                break;
            case R.id.tv_operator_percent:
                mOnViewClickedListener.addToExpression(Constant.PERCENT);
                break;
            case R.id.tv_operator_negative_positive:
                mOnViewClickedListener.addToExpression(Constant.NEGATIVE_POSITIVE);
                break;
            case R.id.tv_all_clear:
                mOnViewClickedListener.allClear();
                break;
            case R.id.tv_operator_equal:
                mOnViewClickedListener.calculate();
                break;
            default:
                break;
        }
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
        void addToExpression(char textToExpression);
        void calculate();
        void allClear();
    }
}

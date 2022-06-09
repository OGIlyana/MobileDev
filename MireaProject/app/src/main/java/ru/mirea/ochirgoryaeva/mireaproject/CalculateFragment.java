package ru.mirea.ochirgoryaeva.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculateFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText etNum1;
    private EditText etNum2;

    private Button btnSum;

    private Button btnSub;
    private Button btnMult;
    private Button btnDiv;

    private TextView tvRes;
    String oper = "";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculateFragment newInstance(String param1, String param2) {
        CalculateFragment fragment = new CalculateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calculate, container, false);



        etNum1 = v.findViewById(R.id.editText1);
        etNum2 = v.findViewById(R.id.editText2);

        btnSum = v.findViewById(R.id.btnPlus);

        btnSub = v.findViewById(R.id.btnMinus);
        btnMult = v.findViewById(R.id.btnMulti);
        btnDiv = v.findViewById(R.id.btnSplit);

        tvRes = v.findViewById(R.id.textViewItog);
       btnSum.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);


        // Inflate the layout for this fragment
        return v;
    }
    @Override
    public void onClick(View view) {

        float num1 = 0;
        float num2 = 0;
        float result = 0;

        if (TextUtils.isEmpty(etNum1.getText().toString())
                || TextUtils.isEmpty(etNum2.getText().toString())) {
            return;
        }

        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());


        switch (view.getId()) {
            case R.id.btnPlus:
                oper = "+";
                result = num1 + num2;
                break;
            case R.id.btnMinus:
                oper = "-";
                result = num1 - num2;
                break;
            case R.id.btnMulti:
                oper = "*";
                result = num1 * num2;
                break;
            case R.id.btnSplit:
                oper = "/";
                result = num1 / num2;
                break;
            default:
                break;
        }
        tvRes.setText(num1 + " " + oper + " " + num2 + " = " + result);
    }





}
package com.zty.healthy.healthyplus.ui.activity.service;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;

import java.util.Calendar;

/**
 * 预定医生
 * Created by zty on 2016/10/11.
 */

public class OrderDoctorActivity extends BaseActivity implements View.OnClickListener {
    private CircularImageView imgOrderDoctor;
    private TextView textOrderDoctorName;
    private TextView textOrderDoctorTitle;
    private TextView textOrderDoctorNote;
    private Button btnOrderVideo;

    private TextView textPraise, textMid, textBad;//评价率
    private Button btnOrderEvaluate;

    private GridView gridViewOldMan;
    private RadioGroup radioGroupOrderDoctor1, radioGroupOrderDoctor2;
    private RadioButton radioButtonOrder1, radioButtonOrder2, radioButtonOrder3, radioButtonOrder4;
    private EditText editOrderDoctor;
    private TextView textOrderPrise;

    private int currentBtnID;

    @Override
    protected int getContentView() {
        return R.layout.activity_order_doctor;
    }

    @Override
    protected void initView() {
        title.setText("医生预定");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setText("");

    }

    @Override
    protected void initData() {
        imgOrderDoctor = (CircularImageView) findViewById(R.id.imgOrderDoctor);
        textOrderDoctorName = (TextView) findViewById(R.id.textOrderDoctorName);
        textOrderDoctorTitle = (TextView) findViewById(R.id.textOrderDoctorTitle);
        textOrderDoctorNote = (TextView) findViewById(R.id.textOrderDoctorNote);
        btnOrderVideo = (Button) findViewById(R.id.btnOrderVideo);
        btnOrderVideo.setOnClickListener(this);

        textPraise = (TextView) findViewById(R.id.textPraise);
        textMid = (TextView) findViewById(R.id.textMid);
        textBad = (TextView) findViewById(R.id.textBad);
        btnOrderEvaluate = (Button) findViewById(R.id.btnOrderEvaluate);
        btnOrderEvaluate.setOnClickListener(this);

        gridViewOldMan = (GridView) findViewById(R.id.gridViewOldMan);
        radioGroupOrderDoctor1 = (RadioGroup) findViewById(R.id.radioGroupOrderDoctor1);
        radioGroupOrderDoctor2 = (RadioGroup) findViewById(R.id.radioGroupOrderDoctor2);
        radioButtonOrder1 = (RadioButton) findViewById(R.id.radioButtonOrder1);
        radioButtonOrder2 = (RadioButton) findViewById(R.id.radioButtonOrder2);
        radioButtonOrder3 = (RadioButton) findViewById(R.id.radioButtonOrder3);
        radioButtonOrder4 = (RadioButton) findViewById(R.id.radioButtonOrder4);
        radioButtonOrder1.setOnClickListener(new BtnSelected(1));
        radioButtonOrder2.setOnClickListener(new BtnSelected(2));
        radioButtonOrder3.setOnClickListener(new BtnSelected(3));
        radioButtonOrder4.setOnClickListener(new BtnSelected(4));
        editOrderDoctor = (EditText) findViewById(R.id.editOrderDoctor);
        editOrderDoctor.setOnClickListener(this);
        textOrderPrise = (TextView) findViewById(R.id.textOrderPrise);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.btnOrderVideo:
                break;
            case R.id.btnOrderEvaluate:
                break;
            case R.id.editOrderDoctor:
                showDatePicker();
                break;
        }

    }

    public class BtnSelected implements View.OnClickListener {
        public BtnSelected(int id) {
            btnID = id;
        }

        final public int btnID;

        @Override
        public void onClick(View arg0) {
            currentBtnID = btnID;

            if (btnID == 1 || btnID == 2) {
                radioGroupOrderDoctor2.clearCheck();
            } else if (btnID == 3 || btnID == 4) {
                radioGroupOrderDoctor1.clearCheck();
            }
        }
    }

    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                StringBuilder strForwardDate = new StringBuilder()
                        .append(year).append("-")
                        .append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1))
                        .append("-").append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);

                editOrderDoctor.setText(strForwardDate.toString());
            }
        }, currentYear, currentMonth, currentDay).show();
    }

}

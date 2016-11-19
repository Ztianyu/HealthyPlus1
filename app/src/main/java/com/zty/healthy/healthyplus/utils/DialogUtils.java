package com.zty.healthy.healthyplus.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.zty.healthy.healthyplus.R;

/**
 * Created by zty on 2016/10/19.
 */

public class DialogUtils {

    /**
     * 提交血压数据
     */
    public static void pressureDialog(Context context, final SavePressure listener) {

        // 为了能在下面的OnClickListener中获取布局上组件的数据，必须定义为final类型.
        final View customLayout = LayoutInflater.from(context).inflate(
                R.layout.dialog_pressure, null);

        final Dialog dialog = new AlertDialog.Builder(context).create();

        dialog.show();
        dialog.getWindow().setContentView(customLayout);

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        final EditText editDialogHighPressure = (EditText) customLayout.findViewById(R.id.editDialogHighPressure);
        final EditText editDialogLowPressure = (EditText) customLayout.findViewById(R.id.editDialogLowPressure);
        final EditText editDialogHeartBeat = (EditText) customLayout.findViewById(R.id.editDialogHeartBeat);
        Button btnSavePressure = (Button) customLayout.findViewById(R.id.btnSavePressure);

        btnSavePressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.saveListener(editDialogHighPressure.getText().toString(), editDialogLowPressure.getText().toString(), editDialogHeartBeat.getText().toString());
                dialog.dismiss();
            }
        });
    }

    public interface SavePressure {
        void saveListener(String hbp, String lbp, String heart);
    }

    /**
     * 提交体重数据
     */
    public static void weightDialog(Context context, final SaveWeight listener) {

        // 为了能在下面的OnClickListener中获取布局上组件的数据，必须定义为final类型.
        final View customLayout = LayoutInflater.from(context).inflate(
                R.layout.dialog_weight, null);

        final Dialog dialog = new AlertDialog.Builder(context).create();

        dialog.show();
        dialog.getWindow().setContentView(customLayout);

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        final EditText editDialogWeight = (EditText) customLayout.findViewById(R.id.editDialogWeight);
        Button btnSavePressure = (Button) customLayout.findViewById(R.id.btnSaveWeight);

        btnSavePressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.saveListener(editDialogWeight.getText().toString());
                dialog.dismiss();
            }
        });
    }

    public interface SaveWeight {
        void saveListener(String weight);
    }

    private static boolean isBefore = true;

    /**
     * 提交体重数据
     */
    public static void sugarDialog(Context context, final SaveSugar listener) {

        // 为了能在下面的OnClickListener中获取布局上组件的数据，必须定义为final类型.
        final View customLayout = LayoutInflater.from(context).inflate(
                R.layout.dialog_sugar, null);

        final Dialog dialog = new AlertDialog.Builder(context).create();

        dialog.show();
        dialog.getWindow().setContentView(customLayout);

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        final RadioGroup radioGroup = (RadioGroup) customLayout.findViewById(R.id.radioGroupDialogSugar);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioDialogSugarBefore:
                        isBefore = true;
                        break;
                    case R.id.radioDialogSugarAfter:
                        isBefore = false;
                        break;
                }

            }
        });


        final EditText editDialogSugar = (EditText) customLayout.findViewById(R.id.editDialogSugar);
        Button btnSavePressure = (Button) customLayout.findViewById(R.id.btnSaveSugar);

        btnSavePressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.saveListener(isBefore, editDialogSugar.getText().toString());
                dialog.dismiss();
            }
        });
    }

    public interface SaveSugar {
        void saveListener(boolean isBefore, String sugar);
    }
}

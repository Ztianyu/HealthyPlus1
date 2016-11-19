package com.zty.healthy.healthyplus.ui.activity;

import android.app.Activity;

import java.io.File;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.zty.healthy.healthyplus.utils.SelectPicUtils;

public class UseCameraActivity extends Activity {
    
    private String mImageFilePath;

    public static File temp;
    public static final String IMAGEFILEPATH = "ImageFilePath";
    public final static String IMAGE_PATH = "image_path";
    static Activity mContext;
    public final static int GET_IMAGE_REQ = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        temp = null;

        // 判断 activity被销毁后 有没有数据被保存下来
        if (savedInstanceState != null) {

            mImageFilePath = savedInstanceState.getString(IMAGEFILEPATH);

            Log.i("savedInstanceState", mImageFilePath);

            File mFile = new File(IMAGEFILEPATH);
            if (mFile.exists()) {
                Intent rsl = new Intent();
                rsl.putExtra(IMAGE_PATH, mImageFilePath);
                setResult(SelectPicUtils.PHOTO_REQUEST_TAKE_PHOTO, rsl);
                Log.i("savedInstanceState", "图片拍摄成功");
                finish();
            } else {
                finish();
                Log.i("savedInstanceState", "图片拍摄失败");
            }
        }

        mContext = this;
        if (savedInstanceState == null) {
            initialUI();
        }

    }

    public void initialUI() {
        // 根据时间生成 后缀为 .jpg 的图片
        temp = new File(SelectPicUtils.PHOTO_DIR, SelectPicUtils.getPhotoFileName());
        mImageFilePath = temp.toString();

        Log.i("mImageFilePath", mImageFilePath);
        showCamera(temp);

    }

    private void showCamera(File out) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out)); // set
        startActivityForResult(intent, GET_IMAGE_REQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode == GET_IMAGE_REQ) {

            Intent rsl = new Intent();
            rsl.putExtra(IMAGE_PATH, mImageFilePath);
            mContext.setResult(SelectPicUtils.PHOTO_REQUEST_TAKE_PHOTO, rsl);
            mContext.finish();

        } else {
            mContext.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("ImageFilePath", mImageFilePath + "");

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

}

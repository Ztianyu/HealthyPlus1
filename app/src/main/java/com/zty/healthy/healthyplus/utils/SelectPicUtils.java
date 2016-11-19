package com.zty.healthy.healthyplus.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 照片选择
 * Created by zty on 2016/9/2.
 */
public class SelectPicUtils {

    public static final int PHOTO_REQUEST_TAKE_PHOTO = 100;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 200;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 300;// 结果

    public static final File PHOTO_DIR = new File(
            Environment.getExternalStorageDirectory() + "/DCIM/Camera");// 存储图片地址

    public static File tempFile = new File(PHOTO_DIR, getPhotoFileName());

    public static void TakePhoto(Context context) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        // 指定调用相机拍照后照片的储存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        ((Activity) context).startActivityForResult(intent, PHOTO_REQUEST_TAKE_PHOTO);

    }

    /**
     * 选择照片
     *
     * @param context
     */
    public static void PickPicture(Context context) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity) context).startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    // 使用系统当前日期加以调整作为照片的名称
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'yyyy_MMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    private static int size = 200;

    /**
     * 对图片进行裁剪
     */
    public static void startPhotoZoom(Context context, Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);

        ((Activity) context).startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

}

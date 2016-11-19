package com.zty.healthy.healthyplus.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by zty on 2016/10/19.
 */

public class ImageUtils {

    public static String imageToBase64(Drawable bit) {

        String imageString = "";

        if (bit != null) {
            Bitmap bbb = ((BitmapDrawable) bit).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bbb.compress(CompressFormat.JPEG, 100, stream);
            int options = 100;
            while (stream.toByteArray().length / 1024 * 7 > 1000 && options > 0) { // 循环判断如果压缩后图片是否大于1000kb,大于继续压缩
                stream.reset();// 重置baos即清空baos
                bbb.compress(CompressFormat.JPEG, options, stream);// 这里压缩options%，把压缩后的数据存放到baos中
                Log.i("compress", options + "%");
                options -= 5;// 每次都减少5，注意有可能为负，为负会报错
            }
            byte[] bytes = stream.toByteArray();
            imageString = Base64.encodeToString(bytes, Base64.DEFAULT);

        }

        return imageString;
    }
}

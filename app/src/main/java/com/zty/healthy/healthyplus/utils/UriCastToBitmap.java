package com.zty.healthy.healthyplus.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;


public class UriCastToBitmap {

    /**
     * 通过图片的uri得到图片的bitmap
     *
     * @param context
     * @param uri
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public static Bitmap setImagePathToBitmap(Context context, Uri uri, int maxWidth, int maxHeight) {

        String path = FileUtil.getRealPath(context, uri);

        InputStream is = null;

        Bitmap result = null;

        if (!TextUtils.isEmpty(path)) {
            try {
                is = new FileInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // 图片配置对象，该对象可以配置图片加载的像素获取个数
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inTempStorage = new byte[100 * 1024];
            // 表示加载图像的原始宽高
            options.inJustDecodeBounds = true;
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            options.inPurgeable = true;
            options.inSampleSize = 4;
            result = BitmapFactory.decodeFile(path, options);
            // Math.ceil表示获取与它最近的整数（向上取值 如：4.1->5 4.9->5）

            int widthRatio = (int) Math.ceil(options.outWidth / maxWidth);
            int heightRatio = (int) Math.ceil(options.outHeight / maxHeight);

            // 设置最终加载的像素比例，表示最终显示的像素个数为总个数的
            if (widthRatio > 1 || heightRatio > 1) {
                if (widthRatio > heightRatio) {
                    options.inSampleSize = widthRatio;
                } else {
                    options.inSampleSize = heightRatio;
                }
            }
            // 解码像素的模式，在该模式下可以直接按照option的配置取出像素点
            options.inJustDecodeBounds = false;
            if (is != null)
                result = BitmapFactory.decodeStream(is, null, options);

        }

        return result;

    }

}

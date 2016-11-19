package com.zty.healthy.healthyplus.base;

import android.app.Application;
import android.graphics.Bitmap;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.db.FinalDb;

/**
 * Created by zty on 2016/8/26.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public FinalDb finalDb;

    public ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        SDKInitializer.initialize(getApplicationContext());

        initImageLoader();

        getFinalDb();
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    public void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                .memoryCacheExtraOptions(480, 800)
                // 缓存在内存的图片的宽和高度
                // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(5)
                // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache((MemoryCache) new LruMemoryCache(2 * 1024 * 1024))//你可以通过自己的内存缓存实现
                .memoryCacheSize(3 * 1024 * 1024)//你可以通过自己的内存缓存实现
                .memoryCacheSizePercentage(13)
                .diskCache(new UnlimitedDiscCache(getCacheDir()))
                .diskCacheSize(50 * 1024 * 1024)// //缓存到文件的最大数据
                .diskCacheFileCount(100)// 文件数量
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .imageDownloader(new BaseImageDownloader(this)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()// Remove for release app
                .build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);// 初始化
    }

    public DisplayImageOptions getDisplayImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.ic_default_avatar)
                .showImageForEmptyUri(R.mipmap.ic_default_avatar)
                .showImageOnFail(R.mipmap.ic_default_avatar)
                .considerExifParams(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }

    /**
     * 获取FinalDb实例
     */
    public FinalDb getFinalDb() {
        if (finalDb == null) {
            finalDb = FinalDb.create(this, true);
        }
        return finalDb;
    }
}

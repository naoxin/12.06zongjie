package com.hhzmy.util;


import android.app.Application;

import com.ehhzmy.hhzmy.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class MyApplication extends Application {

	{

		PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
		PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
		PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		UMShareAPI.get(this);
		imgto();
	}

	private void imgto() {
		// TODO Auto-generated method stub
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisk(true)
				.showImageOnLoading(R.mipmap.ic_launcher)
				.showImageOnFail(R.mipmap.ic_launcher)
				.showImageOnFail(R.mipmap.ic_launcher).build();
		int Maxsize = (int) (Runtime.getRuntime().maxMemory() / 8);
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.discCache(new UnlimitedDiskCache(getCacheDir()))
				.memoryCache(new UsingFreqLimitedMemoryCache(Maxsize))
				.threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 1)
				.defaultDisplayImageOptions(options).build();
		ImageLoader.getInstance().init(configuration);
	}

}

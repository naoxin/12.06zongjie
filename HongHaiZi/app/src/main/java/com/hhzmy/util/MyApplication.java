package com.hhzmy.util;


import android.app.Application;

import com.ehhzmy.hhzmy.R;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {
	private static final String TAG = "JPush";

	{

		PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
		PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
		PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID+"=584a506f ");
		JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
		JPushInterface.init(this);
		UMShareAPI.get(this);
		imgto();
		tui();
	}

	private void tui() {

//		PushAgent mPushAgent = PushAgent.getInstance(this);
////注册推送服务，每次调用register方法都会回调该接口
//		mPushAgent.register(new IUmengRegisterCallback() {
//
//			@Override
//			public void onSuccess(String deviceToken) {
//				//注册成功会返回device token
//				Log.e(TAG, "onSuccess+++++++++++++++++++++:  "+deviceToken );
//			}
//
//			@Override
//			public void onFailure(String s, String s1) {
//
//			}
//		});


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

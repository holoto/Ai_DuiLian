package com.ai.myduilian;

import android.app.Application;
import android.content.Context;
import android.view.Window;

import com.ai.myduilian.objectBoxModel.MyObjectBox;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.vondear.rxtool.RxTool;

import dev.DevUtils;
import dev.utils.app.DeviceUtils;
import dev.utils.app.logger.DevLogger;
import dev.utils.app.logger.LogConfig;
import dev.utils.app.logger.LogLevel;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class BaseApplication extends Application {
	private static BaseApplication baseApplicationinstance;
	private static Context contextinstance;
	private static RefWatcher refWatcher;
	public static final String TAG="duiliandata";
	public static final boolean EXTRNAL_DIR=false;
	private  BoxStore boxStore;
	private final String LOG_TAG = BaseApplication.class.getSimpleName();
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.initLeakCanary();
		
		contextinstance=this;
		baseApplicationinstance=this;
		// 初始化工具类
		RxTool.init(this);
		DevUtils.init(this.getApplicationContext());
		// == 初始化日志配置 ==
		// 设置默认Logger配置
		LogConfig logConfig = new LogConfig();
		logConfig.logLevel = LogLevel.DEBUG;
		logConfig.tag = LOG_TAG;
		logConfig.sortLog = true; // 美化日志, 边框包围
		DevLogger.init(logConfig);
		
		// 打开 lib 内部日志 - 线上环境, 不调用方法就行
		DevUtils.openLog();
		DevUtils.openDebug();
		initObjectbox();
		if (BuildConfig.DEBUG){
			new AndroidObjectBrowser(boxStore).start(this);
		}
		
		
	}
	private void initObjectbox(){
	boxStore= MyObjectBox.builder().androidContext(contextinstance).build();
	}
	public  BoxStore getBoxStore() {
		return boxStore;
	}
	
	public static RefWatcher getRefWatcher(){
		return refWatcher;
	}
	private void initLeakCanary(){
		if (LeakCanary.isInAnalyzerProcess(this)) {//1
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return;
		}
//		refWatcher=LeakCanary.install(this);
	}
	
	public static BaseApplication getBaseApplicationinstance() {
		if (baseApplicationinstance==null){
			baseApplicationinstance=new BaseApplication();
		}
		return baseApplicationinstance;
	}
	
	public static Context getContext(){
		
		return contextinstance;
	}
}

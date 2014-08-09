package com.example.frameworks;

//C5:40:F8:B5:52:D1:09:84:A4:A9:DD:19:8F:F7:2B:9D:0D:38:3B:40;com.example.phoneiot
//package com.example.framework.IOTApplication
import android.app.Application;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;

/**
 * 
 * 
 * @author llwoll
 *
 */
public class IOTApplication extends Application{

	private static BMapManager mBMapMan = null;
	private static IOTApplication mInstance = null;
	private static final String key = "rs3wjBNkpraBzWgkgbRuDn89";
	
	
	/**
	 * 启动后去的百度地图管理器,实例化本application
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = getInstance();
		
		mBMapMan = getBMapMan();

	}
	
	/**
	 * 
	 * @return  对此application进行封装,保证只被实例化一次
	 */
	public static final IOTApplication getInstance()
	{
		if (mInstance == null){
			mInstance = new IOTApplication();		
		}
		
		return mInstance;		
	}	
	
	/**
	 * 
	 * @return  百度地图管理器
	 */
	public BMapManager getBMapMan()
	{
		if(mBMapMan == null)
			mBMapMan = new BMapManager(this);
		else 
		if(mBMapMan != null)
		   if (!mBMapMan.init(key,new MyGeneralListener())) {
	           Toast.makeText(IOTApplication.getInstance().getApplicationContext(), 
	                   "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
	        }
		
		return mBMapMan;
	}
	
	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
    static class MyGeneralListener implements MKGeneralListener {
        
        @Override
        public void onGetNetworkState(int iError) {
            if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
                Toast.makeText(IOTApplication.getInstance().getApplicationContext(), "您的网络出错啦！",
                    Toast.LENGTH_LONG).show();
            }
            else if (iError == MKEvent.ERROR_NETWORK_DATA) {
                Toast.makeText(IOTApplication.getInstance().getApplicationContext(), "输入正确的检索条件！",
                        Toast.LENGTH_LONG).show();
            }
            // ...
        }

        @Override
        public void onGetPermissionState(int iError) {
        	//非零值表示key验证未通过
            if (iError != 0) {
                //授权Key错误：
                Toast.makeText(IOTApplication.getInstance().getApplicationContext(), 
                        "请在 IOTApplication.java文件输入正确的授权Key,并检查您的网络连接是否正常！error: "+iError, Toast.LENGTH_LONG).show();
                //IOTApplication.getInstance().m_bKeyRight = false;
            }
            else{
            	//IOTApplication.getInstance().m_bKeyRight = true;
            	//Toast.makeText(getBaseContext(), "key认证成功", Toast.LENGTH_LONG)
            	//Toast.makeText(IOTApplication.getInstance().getApplicationContext(), 
                  //      "key认证成功", Toast.LENGTH_LONG).show();
            }
        }
    }
}
	
	
	
	
	
	
	
	
	

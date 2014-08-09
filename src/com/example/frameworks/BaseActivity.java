package com.example.frameworks;


//import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

//import com.baidu.mapapi.map.MapFragment;



/**
 * 
 * @author llwoll
 *
 */
public class BaseActivity extends Fragment{

	/**
	 * 每一个继承ＢaseActivity的class 都拥有百度地图管理器
	 */
	public static final IOTApplication mApp = IOTApplication.getInstance();
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		ActivityStateManager.onCreate(this);
		
	}

	public void finish() {
	
	}
	
	public void onDestroy()
	{
		super.onDestroy();
		ActivityStateManager.onDestroy(this);
	}
	
	public void onPause()
	{
		super.onPause();
	}
	
	public void onResume()
	{
		super.onResume();
		ActivityStateManager.onResume(this);
		
		
	}
	
	public void onStop()
	{
		ActivityStateManager.onStop(this);
		super.onStop();
	}
	
}

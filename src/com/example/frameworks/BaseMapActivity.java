package com.example.frameworks;

import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapView;
//import com.example.phoneiot.R;

public class BaseMapActivity extends BaseActivity{

	protected BMapManager mBMapManager = mApp.getBMapMan();
	protected MapView mMapView = null;
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
	}
	@Override
	public void finish() {
		super.finish();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (this.mMapView != null)
		{
			this.mMapView.destroy();			
		}
	
	}
	@Override
	public void onPause() {
		super.onPause();
		if (this.mMapView != null)		
			this.mMapView.onPause();		
	}
	
	protected void onRestoreInstanceState(Bundle B)
	{
		this.mMapView.onRestoreInstanceState(B);
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		if(this.mMapView !=null) this.mMapView.onResume();
	}
	
	public void onSaveInstanceState(Bundle B)
	{
		super.onSaveInstanceState(B);
		
	}
	
	 
	public void setContentView(int paramInt)
	  {
	    if (this.mBMapManager == null) 
	      throw new RuntimeException("Call setBMapManager(BMapManager) first");
	    
	    
	 //   this.mMapView = ((MapView)findViewById(R.id.bmapView));
	  //  if (this.mMapView == null)
	    //  throw new RuntimeException("Your content must have a MapView whose id attribute is 'R.id.bmapView'");
	  }

	@Override
	public void onStop() {
		super.onStop();
	}
	
	
	
	
	
}

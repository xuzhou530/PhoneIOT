package com.example.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.frameworks.BaseMapActivity;
import com.example.frameworks.IOTApplication;
import com.example.phoneiot.R;

public  class MapFragment extends BaseMapActivity{

	private TextView tv;
	private MapController mMapController;
	private MKMapViewListener mMapListener;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
	
		return inflater.inflate(R.layout.map, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		tv = (TextView) getView().findViewById(R.id.titleTv);
		tv.setText("map");
		
		mMapView = (MapView) getView().findViewById(R.id.bmapView);
		mMapController = mMapView.getController();
		
		/**
         *  设置地图是否响应点击事件  .
         */
        mMapController.enableClick(true);
        /**
         * 设置地图缩放级别
         */
        mMapController.setZoom(12);
        
        /**
         * 将地图移动至指定点
         * 使用百度经纬度坐标，可以通过http://api.map.baidu.com/lbsapi/getpoint/index.html查询地理坐标
         * 如果需要在百度地图上显示使用其他坐标系统的位置，请发邮件至mapapi@baidu.com申请坐标转换接口
         */
        GeoPoint p ;
        double cLat = 39.945 ;
        double cLon = 116.404 ;
       
        //设置中心点为天安门
        p = new GeoPoint((int)(cLat * 1E6), (int)(cLon * 1E6));
        mMapController.setCenter(p);
        
        /**
    	 *  MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
    	 */
        mMapListener = new MKMapViewListener() {
			@Override
			public void onMapMoveFinish() {
				/**
				 * 在此处理地图移动完成回调
				 * 缩放，平移等操作完成后，此回调被触发
				 */
			}
			
			@Override
			public void onClickMapPoi(MapPoi mapPoiInfo) {
				/**
				 * 在此处理底图poi点击事件
				 * 显示底图poi名称并移动至该点
				 * 设置过： mMapController.enableClick(true); 时，此回调才能被触发
				 * 
				 */
				String title = "";
				if (mapPoiInfo != null){
					title = mapPoiInfo.strText;
					Toast.makeText(getActivity(),title,Toast.LENGTH_SHORT).show();
					mMapController.animateTo(mapPoiInfo.geoPt);
				}
			}

			@Override
			public void onGetCurrentMap(Bitmap b) {
				/**
				 *  当调用过 mMapView.getCurrentMap()后，此回调会被触发
				 *  可在此保存截图至存储设备
				 */
			}

			@Override
			public void onMapAnimationFinish() {
				/**
				 *  地图完成带动画的操作（如: animationTo()）后，此回调被触发
				 */
			}
            /**
             * 在此处理地图载完成事件 
             */
			public void onMapLoadFinish() {
					Toast.makeText(getActivity(), "地图加载完成", Toast.LENGTH_SHORT).show();
			}
			
		};
		mMapView.regMapViewListener(IOTApplication.getInstance().getBMapMan(), mMapListener);
		
	}



@Override
public void onPause() {
	/**
	 *  MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
	 */
    mMapView.onPause();
    super.onPause();
}

@Override
public void onResume() {
	/**
	 *  MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
	 */
    mMapView.onResume();
    super.onResume();
}

@Override
public void onDestroy() {
	/**
	 *  MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
	 */
    mMapView.destroy();
    super.onDestroy();
}

@Override
public void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	mMapView.onSaveInstanceState(outState);
	
}

@Override
protected void onRestoreInstanceState(Bundle savedInstanceState) {
	super.onRestoreInstanceState(savedInstanceState);
	mMapView.onRestoreInstanceState(savedInstanceState);
}

}


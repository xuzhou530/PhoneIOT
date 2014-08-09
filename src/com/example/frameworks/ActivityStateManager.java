package com.example.frameworks;

import android.app.Activity;
import android.util.SparseArray;

public final class ActivityStateManager {

	private static final SparseArray<ActivityState> mManagerActivity = new SparseArray<ActivityState>();
	
	
	public static synchronized void onCreate(BaseActivity baseActivity)
	{
		ActivityState state1 = mManagerActivity.get(baseActivity.hashCode());
		if(state1 == null)
		{
			state1 = new ActivityState();
			state1.mActivity = baseActivity;
			state1.mHashCode = baseActivity.hashCode();
			state1.mStatus = ActivityStateManager.ActivityState.State.ACTIVE;
			
			mManagerActivity.put(state1.mHashCode, state1);
		}
		else
			state1.mStatus = ActivityStateManager.ActivityState.State.ACTIVE;
	}
	
	public static synchronized void onDestroy(BaseActivity baseActivity)
	{
		mManagerActivity.remove(baseActivity.hashCode());
		return;
	}
	public static synchronized void onResume(BaseActivity activity)
	{
		ActivityState state = mManagerActivity.get(activity.hashCode());
		if (state != null)
			state.mStatus = ActivityState.State.ACTIVE;
		return;	
	}
	
	public static synchronized void onStop(BaseActivity activity)
	{
		ActivityState state = mManagerActivity.get(activity.hashCode());
		if (state != null)
			state.mStatus = ActivityState.State.STOP;
		return;	
		
	}

	/**
	 * 关闭所有的activity,并清除地图地图管理器
	 */
	public static synchronized void finishAll()
	{
		int i = mManagerActivity.size();
		while (i>=0)
		{
			((ActivityState)mManagerActivity.valueAt(i)).mActivity.finish();
			if (i == 0) mManagerActivity.clear();
		}
	}
	
	/**
	 * 根据activity 的hash指获取他的状态
	 */
	
	public static synchronized ActivityStateManager.ActivityState.State getActivityState(int hashcode)
	{
		return ((ActivityState)mManagerActivity.get(hashcode)).mStatus;	
	}
	/**
	 * 
	 * @param activity 
	 * @return 传入activity的状态
	 */
	public static synchronized ActivityStateManager.ActivityState.State getActivityState(Activity activity)
	{
		return getActivityState(activity.hashCode());
	}
	
	
	

public static class ActivityState{
	public BaseActivity mActivity;
	public int mHashCode;
	public State mStatus;
	
	public static enum State
	{
		ACTIVE,STOP,DESTROY,REMOVE;
		
	}
	
	
	
}


}


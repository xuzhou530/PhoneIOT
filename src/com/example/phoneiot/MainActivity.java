package com.example.phoneiot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.Window;
//import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {

	private Fragment[] mFragments;
	private RadioGroup bottomRg;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	//private RadioButton rbmap,rbme,rbnear,rbabout;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        mFragments = new Fragment[4];
        fragmentManager = getSupportFragmentManager();
        
        mFragments[0] = fragmentManager.findFragmentById(R.id.fra_map);
        mFragments[1] = fragmentManager.findFragmentById(R.id.fra_me);
        mFragments[2] = fragmentManager.findFragmentById(R.id.fra_near);
        mFragments[3] = fragmentManager.findFragmentById(R.id.fra_about);
        
        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);
        fragmentTransaction.show(mFragments[0]).commit();
        
        
        bottomRg = (RadioGroup) findViewById(R.id.bottomRg);
    	
    	//RadioButton rbmap = (RadioButton) findViewById(R.id.rbmap);
    	//RadioButton rbme = (RadioButton) findViewById(R.id.rbme);
    	//RadioButton rbnear = (RadioButton) findViewById(R.id.rbnear);
    	//RadioButton rbabout = (RadioButton) findViewById(R.id.rbabout);
    	
    	bottomRg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);
				
				if (arg1 == R.id.rbme) {
					fragmentTransaction.show(mFragments[1]).commit();
				} else if (arg1 == R.id.rbnear) {
					fragmentTransaction.show(mFragments[2]).commit();
				} else if (arg1 == R.id.rbabout) {
					fragmentTransaction.show(mFragments[3]).commit();
				} else {
				}
			}
    		
      	});
        //setFragmentIndicator();
    }

    /**
     * 设置radiogroup的监听器,控制各种fragment的切换
     */
    @SuppressWarnings("unused")
	private void setFragmentIndicator(){
    	
    	
    	
    	
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

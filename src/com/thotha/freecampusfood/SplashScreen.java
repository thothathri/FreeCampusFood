package com.thotha.freecampusfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class SplashScreen extends Activity {

	protected boolean _active = true;
	protected int _splashTime = 5000; 
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);  
    	this.setContentView(R.layout.splash);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(10);
                        if(_active) {
                            waited += 100;
                        }
                    }
                    Log.d("MMMM","In Try now!");
                } 
                catch(InterruptedException e) {
                	Log.d("DDDD","Exception Caught!");
                } 
                finally {
                	Log.d("TTTT","In Finally Now!");
                	Intent explicitIntent = new Intent(SplashScreen.this,MenuScreen.class);
                    startActivity(explicitIntent);
                    finish();
                }
            }
        };
        Log.d("OOOO","Before splashthreadstart!");
        splashTread.start();
       }
}
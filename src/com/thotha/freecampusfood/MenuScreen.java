package com.thotha.freecampusfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MenuScreen  extends Activity {
	private Button searchButton;
	private Button eventButton;
	private Button futureButton;
//	private Button newEventButton;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.searchButton = (Button)this.findViewById(R.id.search);
      //  this.newEventButton = (Button)this.findViewById(R.id.widget34);
  	  this.searchButton.setOnClickListener(new OnClickListener() {
  	    public void onClick(View v) {
			Intent intent = new Intent(MenuScreen.this, DisplayJSON.class);
			startActivity(intent);
  	    }
  	});
  	this.futureButton = (Button)this.findViewById(R.id.future);
    //  this.newEventButton = (Button)this.findViewById(R.id.widget34);
	  this.futureButton.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
			Intent intent = new Intent(MenuScreen.this, FutureEvent.class);
			startActivity(intent);
	    }
	});
  	  this.eventButton = (Button)this.findViewById(R.id.newevent);
  	  this.eventButton.setOnClickListener(new OnClickListener() {
  	    public void onClick(View v) {
			Intent intent = new Intent(MenuScreen.this, CreateEvent.class);
			startActivity(intent);
  	    }
  	  });
  	  /*
  	 this.newEventButton.setOnClickListener(new OnClickListener() {
   	    public void onClick(View v) {
			Log.d("XXXX","hey0");
 			Intent intent = new Intent(MenuScreen.this, PostJSON.class);
 			startActivity(intent);
   	    }
   	  });
   	  */
  	}

}
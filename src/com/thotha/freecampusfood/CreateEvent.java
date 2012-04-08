package com.thotha.freecampusfood;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CreateEvent extends Activity {
	private TextView dateText;
	private TextView timeText;
	private TextView endTimeText;
	private Button defaultButton;
	private Button timeButton;
	private Button endtimeButton;
	
	static final int DEFAULTDATESELECTOR_ID = 0;
	static final int DEFAULTTIMESELECTOR_ID = 1;
	static final int DEFAULTENDTIMESELECTOR_ID = 2;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.newevent);
	        dateText = (TextView) this.findViewById(R.id.selectedDateLabel);
	        timeText = (TextView) this.findViewById(R.id.selectedStartTime);
	        endTimeText = (TextView) this.findViewById(R.id.selectedEndTime);
	        this.defaultButton = (Button) this.findViewById(R.id.defaultDateSelectButton);
	        
	        // set up a listener for when the button is pressed 
	        defaultButton.setOnClickListener(new OnClickListener() {
	                        public void onClick(View arg0) {
	                                // call the internal showDialog method using the predefined ID
	                        	    Log.d("TTTT","Show Dialog Now!");
	                                showDialog(DEFAULTDATESELECTOR_ID);
	                        }               
	        });
	        
	        this.timeButton = (Button) this.findViewById(R.id.timeSelectButton);
	        
	        // set up a listener for when the button is pressed 
	        timeButton.setOnClickListener(new OnClickListener() {
	                        public void onClick(View arg0) {
	                                // call the internal showDialog method using the predefined ID
	                        	    Log.d("TTTT","Show Time Dialog Now!");
	                                showDialog(DEFAULTTIMESELECTOR_ID);
	                        }               
	        });
	        
	        this.endtimeButton = (Button) this.findViewById(R.id.endtimeSelectButton);
	     // set up a listener for when the button is pressed 
	        endtimeButton.setOnClickListener(new OnClickListener() {
	                        public void onClick(View arg0) {
	                                // call the internal showDialog method using the predefined ID
	                        	    Log.d("TTTT","Show Time Dialog Now!");
	                                showDialog(DEFAULTENDTIMESELECTOR_ID);
	                        }               
	        });
	    }

	    // define the listener which is called once a user selected the date.
	    private DateSlider.OnDateSetListener mDateSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	                // update the dateText view with the corresponding date
	                dateText.setText(String.format("%te, %tB %tY", selectedDate, selectedDate, selectedDate));
	            }
	    };    
	    
	    
	    private DateSlider.OnDateSetListener mTimeSetListener =
		        new DateSlider.OnDateSetListener() {
		            public void onDateSet(DateSlider view, Calendar selectedTime) {
		                // update the dateText view with the corresponding date
		           	    Log.d("TTTT","Show Time!");
		           	 timeText.setText(String.format("Starts: %tH:%tM", selectedTime, selectedTime, selectedTime));
		            }
		    }; 
		
		    private DateSlider.OnDateSetListener mEndTimeSetListener =
			        new DateSlider.OnDateSetListener() {
			            public void onDateSet(DateSlider view, Calendar selectedTime) {
			                // update the dateText view with the corresponding date
			           	    Log.d("TTTT","Show Time!: "+selectedTime);
			           	 endTimeText.setText(String.format("Ends: %tH:%tM", selectedTime, selectedTime, selectedTime));
			            }
			    }; 
			
	    @Override
	    protected Dialog onCreateDialog(int id) {
	        // this method is called after invoking 'showDialog' for the first time
	        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller

	        // get todays date and the time
	        final Calendar c = Calendar.getInstance();
	        switch (id) {
	        case DEFAULTDATESELECTOR_ID:
	            return new DefaultDateSlider(this,mDateSetListener,c);
	        case DEFAULTTIMESELECTOR_ID:
	       	    Log.d("TTTT","Show Time  Now!");
	            return new TimeSlider(this,mTimeSetListener,c);    
	        case DEFAULTENDTIMESELECTOR_ID:
	       	    Log.d("TTTT","Show Time  Now!");
	            return new TimeSlider(this,mEndTimeSetListener,c);
	        }
	        return null;
	    }
	 

}


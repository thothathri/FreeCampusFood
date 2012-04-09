package com.thotha.freecampusfood;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateEvent extends Activity {
	private TextView dateText;
	private TextView timeText;
	private TextView endTimeText;
	private String title;
	private String food;
	private String location;
	private String description;
	private Button defaultButton;
	private Button timeButton;
	private Button endtimeButton;
	private Button createEventButton;
	
	static final int DEFAULTDATESELECTOR_ID = 0;
	static final int DEFAULTTIMESELECTOR_ID = 1;
	static final int DEFAULTENDTIMESELECTOR_ID = 2;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.newevent);
	        dateText = (TextView) this.findViewById(R.id.selectedDateLabel);
	        timeText = (TextView) this.findViewById(R.id.selectedStartTime);
	        endTimeText = (TextView) this.findViewById(R.id.selectedEndTime);
	        //title = (TextView) this.findViewById(R.id.title);
	      
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
	        
	        this.createEventButton = (Button)this.findViewById(R.id.create);
	        createEventButton.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					HttpClient httpclient = new DefaultHttpClient();
			        HttpPost httppost = new HttpPost("http://campusfoodie.heroku.com/campus_foods");
			        final EditText ET = (EditText) findViewById(R.id.title);
			        title = ET.getText().toString();
			        food = ((EditText) findViewById(R.id.food)).getText().toString();
			        location = ((EditText) findViewById(R.id.location)).getText().toString();
			        description = ((EditText) findViewById(R.id.description)).getText().toString();
			        String date = ((EditText) findViewById(R.id.selectedDateLabel)).getText().toString();
			        String start = ((EditText) findViewById(R.id.selectedStartTime)).getText().toString();
			        String end = ((EditText) findViewById(R.id.selectedEndTime)).getText().toString();
			        
			        Log.d("title is ",title);
			        try {
			            // Add your data
			            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);
			            nameValuePairs.add(new BasicNameValuePair("campus_food[title]", title));
			            nameValuePairs.add(new BasicNameValuePair("campus_food[food]", food));
			            nameValuePairs.add(new BasicNameValuePair("campus_food[desc]", description));
			            nameValuePairs.add(new BasicNameValuePair("campus_food[location]","D. H. Hill Library, North Carolina State University, 2205 Hillsborough St, Raleigh, NC 27607, USA".toString()));
			            nameValuePairs.add(new BasicNameValuePair("campus_food[date]",date));
			            nameValuePairs.add(new BasicNameValuePair("campus_food[start]", start));
			            nameValuePairs.add(new BasicNameValuePair("campus_food[end]", end));
			            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			            // Execute HTTP Post Request
			            HttpResponse response = httpclient.execute(httppost);
			            
			        } catch (ClientProtocolException e) {
			            // TODO Auto-generated catch block
			        } catch (IOException e) {
			            // TODO Auto-generated catch block
			        }
					Intent intent = new Intent(CreateEvent.this, DisplayJSON.class);
					startActivity(intent);
					
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
		           	 timeText.setText(String.format("%tH:%tM", selectedTime, selectedTime, selectedTime));
		            }
		    }; 
		
		    private DateSlider.OnDateSetListener mEndTimeSetListener =
			        new DateSlider.OnDateSetListener() {
			            public void onDateSet(DateSlider view, Calendar selectedTime) {
			                // update the dateText view with the corresponding date
			           	    Log.d("TTTT","Show Time!: "+selectedTime);
			           	 endTimeText.setText(String.format("%tH:%tM", selectedTime, selectedTime, selectedTime));
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


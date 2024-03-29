package com.thotha.freecampusfood;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowEvent extends MapActivity implements DialogListener{
	MapView mv;
	MapController controller;
	List<Map<String, String>> data;
	String eventId;
	int flag;
	 private static String APP_ID = "276515325771923"; // Replace your App ID here
	 
	    // Instance of Facebook Class
	    private Facebook facebook;
	    private AsyncFacebookRunner mAsyncRunner;
	    String FILENAME = "AndroidSSO_data";
	    private SharedPreferences mPrefs;

		private Button btnFbLogin;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		Date dt;
		
		  //date = (Date)formatter.parse(str_date);  
		private Button btnPostToWall;
		Facebook facebookClient;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);

        facebook = new Facebook(APP_ID);
        mAsyncRunner = new AsyncFacebookRunner(facebook);
        this.btnFbLogin = (Button)this.findViewById(R.id.facebook);
        btnFbLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if (!facebook.isSessionValid()) {
            		loginToFacebook();
            	}
            	else{
            		 postToWall();
            	}
            		
                }
        });

        this.btnFbLogin = (Button)this.findViewById(R.id.like);
        btnFbLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if (!facebook.isSessionValid()) {
            		loginToFacebook();
            	}
     	
            	getProfileInformation();	
                }
        });
        Log.d("Temp Acco","ABCD");
        Bundle bundle = this.getIntent().getExtras();
        
        eventId = new String(bundle.getString("id"));
        /////////////////////////////////////////////////
        data = new ArrayList<Map<String, String>>();     
        String imageurl="http://warm-mountain-2574.herokuapp.com/campus_foods/"+eventId+".json";
        URL url = null;
        
		try 
		{
			url = new URL(imageurl);
		} 

		catch (MalformedURLException e2) 
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

        InputStream is = null;
        URLConnection connection = null;
        
		try 
		{
			connection = url.openConnection();
		} 

		catch (IOException e2) 
		{
				// TODO Auto-generated catch block
				e2.printStackTrace();
		}

        Log.d("BACCCCCCKKKKK", "1.0");
        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        
        try 
        {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} 
        
        catch (IOException e1) 
        {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		} //problem is here
        
        Log.d("read", "2.0");
        
        try 
        {
			while ((line = reader.readLine()) != null) 
			{
				builder.append(line);
			}
		} 
        
        catch (IOException e1) 
        {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        Log.d("done read", "3.0");
        String jSONString = builder.toString();
        //after cutting off the junk, its ok
        Log.d("response", "String is : "+jSONString);
		TextView newtv = (TextView)this.findViewById(R.id.textView5);
        newtv.setText(jSONString+"Votes");        
        //////////////////////////////////////////////////
        Log.d("Event id:" , eventId);
        String title = ("Event:  "+bundle.getString("title"));
        String t = new String(bundle.getString("title"));
        String food = ("Food:  "+bundle.getString("food"));
        String f = new String(bundle.getString("food"));
        String l = new String(bundle.getString("location"));
        String location = ("Location:\n"+bundle.getString("location"));
        String start = ("Time:  "+bundle.getString("start"));
        String eventDate = new String(bundle.getString("date"));
        try {
        	SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        	dt = new Date(myFormat.parse(eventDate).getTime()); 
        	//dt = (Date)formatter.parse(eventDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String date = ("Date:  "+ dt +"  Time:  "+bundle.getString("start"));
        //TextView startView = (TextView) findViewById(R.id.textView4);
        TextView titleView = (TextView) findViewById(R.id.textView1);
        TextView foodView = (TextView) findViewById(R.id.textView2);
        TextView locationView = (TextView) findViewById(R.id.textView3);
        TextView dateView = (TextView) findViewById(R.id.eventDt);
        foodView.setText(food);
        titleView.setText(title);
        //startView.setText(start);
        locationView.setText(location);
        dateView.setText(date);
        Log.d("Before Map","hello");
        /*mv = (MapView)findViewById(R.id.map_view);
        controller = mv.getController();
        Geocoder coder = new Geocoder(this);
        List<Address> address = null;
        try {
			address = coder.getFromLocationName(l,1);
			Log.d("Addr",address.get(0).toString());
		} 
        catch (IOException e) {
			e.printStackTrace();
			Log.d("DDD","In Catch");
		}            
        Address loc = address.get(0);
        Log.d("YYY","Here1");
        Log.d("YYY",loc.toString());
        GeoPoint point = new GeoPoint((int)(loc.getLatitude() * 1e6),(int)(loc.getLongitude() * 1e6) );
        Log.d("YYY","Here2");
        controller.animateTo(point);
        Log.d("YYY","Here3");
        controller.setZoom(17);   
        List<Overlay> mapOverlays = mv.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.pizza);
        HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable, this);
        OverlayItem overlayitem = new OverlayItem(point, t, f);
        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);*/ 
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	
	   public void loginToFacebook() {
	        mPrefs = getPreferences(MODE_PRIVATE);
	        String access_token = mPrefs.getString("access_token", null);
	        long expires = mPrefs.getLong("access_expires", 0);
	     
	        if (access_token != null) {
	            facebook.setAccessToken(access_token);
	        }
	     
	        if (expires != 0) {
	            facebook.setAccessExpires(expires);
	        }
	     
	        if (!facebook.isSessionValid()) {
	            facebook.authorize(this,
	                    new String[] { "email", "publish_stream" },
	                    new DialogListener() {
	     
	                        public void onCancel() {
	                            // Function to handle cancel event
	                        }
	     
	                        public void onComplete(Bundle values) {
	                            // Function to handle complete event
	                            // Edit Preferences and update facebook acess_token
	                            SharedPreferences.Editor editor = mPrefs.edit();
	                            editor.putString("access_token",
	                                    facebook.getAccessToken());
	                            editor.putLong("access_expires",
	                                    facebook.getAccessExpires());
	                            editor.commit();
	                        }
	     
	                        public void onError(DialogError error) {
	                            // Function to handle error
	     
	                        }
	     
	                        public void onFacebookError(FacebookError fberror) {
	                            // Function to handle Facebook errors
	     
	                        }

							
	     
	                    });
	        }
	        
	    }
	   
	   public void postToWall() {
           // post on user's wall.
          //facebook.dialog(this, "feed", new DialogListener() {
       	Bundle parameters = new Bundle();
           //parameters.putString("message", "this is a new test");
           parameters.putString("link", "www.campusfoodie.heroku.com");
           Bundle bundle = this.getIntent().getExtras();
           parameters.putString("description", "Event:  "+bundle.getString("title")+", Food:  "+bundle.getString("food")+"\n Time:  "+bundle.getString("start"));
           
           facebook.dialog(this, "feed", parameters,new DialogListener(){
               public void onFacebookError(FacebookError e) {
               }
        
               public void onError(DialogError e) {
               }
        
               public void onComplete(Bundle values) {
               	
            
               }
        
               public void onCancel() {
               }
           });
        
       }

	   public void getProfileInformation() {
		    mAsyncRunner.request("me", new RequestListener() {
		        public void onComplete(String response, Object state) {
		            Log.d("Profile", response);
		            
		            String json = response;
		            try {
		                JSONObject profile = new JSONObject(json);
		                // getting name of the user
		                final String name = profile.getString("name");
		                // getting email of the user
		                final String id = profile.getString("id");
		                Log.d("User info is", "id:"+id+"   name:" +name);
		                
		    			HttpClient httpclient = new DefaultHttpClient();
		    		    HttpPost httppost = new HttpPost("http://warm-mountain-2574.herokuapp.com/uservotes");
        
	    		        try {
		    		            // Add your data
	    		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	    		            nameValuePairs.add(new BasicNameValuePair("uservote[fuid]", id));
	    		            nameValuePairs.add(new BasicNameValuePair("uservote[eventid]", eventId));
	    		            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    		            // Execute HTTP Post Request
	    		            HttpResponse resp = httpclient.execute(httppost);
	    		            Log.d("QQQQQQ",resp.getStatusLine().toString());
	    		            Log.d("**********response is: ", resp.toString());
	    		            if(resp.getStatusLine().toString().indexOf('4')!=-1)
	    		            {
	    		            	flag = 1;
	    		            }
	    		        } catch (ClientProtocolException e) {
	    		            // TODO Auto-generated catch block
	    		        } catch (IOException e) {
	    		            // TODO Auto-generated catch block
	    		        }
		    		    
	    		    	
		    		       // Intent intent = new Intent(ShowEvent.this, ShowEvent.class);
		    		    //    intent.putExtras(bundle);
		    		     //   Log.d("in bundle",bundle.getString("location"));
		    				//startActivity(intent);
		    		       
		            
		                
		                
		                     runOnUiThread(new Runnable() {
		 
		                    public void run() {
		                        //Toast.makeText(getApplicationContext(), "Name: " + name + "\nEmail: " + id, Toast.LENGTH_LONG).show();
		                        if(flag ==1){
		                        	Toast.makeText(getApplicationContext(), "You cannot vote more than once!",Toast.LENGTH_LONG).show();
		                        }
		                    }
		 
		                });
		 
		            } catch (JSONException e) {
		                e.printStackTrace();
		            }
		        }
		 
		        public void onIOException(IOException e, Object state) {
		        }
		 
		       	 
		        public void onFacebookError(FacebookError e, Object state) {
		        }

				public void onFileNotFoundException(FileNotFoundException e,
						Object state) {
					// TODO Auto-generated method stub
					
				}

				public void onMalformedURLException(MalformedURLException e,
						Object state) {
					// TODO Auto-generated method stub
					
				}
		    });
		}

	public void onComplete(Bundle values) {
		// TODO Auto-generated method stub
		
	}

	public void onFacebookError(FacebookError e) {
		// TODO Auto-generated method stub
		
	}

	public void onError(DialogError e) {
		// TODO Auto-generated method stub
		
	}

	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

}

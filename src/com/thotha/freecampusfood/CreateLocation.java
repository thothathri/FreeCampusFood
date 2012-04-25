package com.thotha.freecampusfood;

import java.io.IOException;
import java.util.ArrayList;
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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

public class CreateLocation extends Activity {
	private Button createLocationButton;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        
        this.createLocationButton = (Button)this.findViewById(R.id.location);
        createLocationButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://campusfoodie.heroku.com/locations");
		        final EditText ET = (EditText) findViewById(R.id.address);
		        String location = ET.getText().toString();
		        
		        try {
		            // Add your data
		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		            nameValuePairs.add(new BasicNameValuePair("location[loc]", location));
		            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            // Execute HTTP Post Request
		            HttpResponse response = httpclient.execute(httppost);
		        } catch (ClientProtocolException e) {
		            // TODO Auto-generated catch block
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		        }
		        
		        Bundle bundle = new Bundle();
		        bundle.putString("location", location);
		        Intent intent = new Intent(CreateLocation.this, CreateEvent.class);
		        intent.putExtras(bundle);
		        Log.d("in bundle",bundle.getString("location"));
				startActivity(intent);
				}
        });
        }
        
      
}

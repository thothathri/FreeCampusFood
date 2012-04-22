package com.thotha.freecampusfood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class SearchFood extends Activity {
	List<Map<String, String>> data;
	private Button searchButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        this.searchButton = (Button)this.findViewById(R.id.search);
        //  this.newEventButton = (Button)this.findViewById(R.id.widget34);
    	  this.searchButton.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v) {
    	    	final EditText ET = (EditText) findViewById(R.id.food);
  		        String food = ET.getText().toString();
    	    	Bundle bundle = new Bundle();
   		        bundle.putString("food", food);
   		        Log.d("food in search is", food);
   		        Intent intent = new Intent(SearchFood.this, SearchResult.class);
   		        intent.putExtras(bundle);
   		        startActivity(intent);
    	    }
    	});
    	  
    	 
    }
    
}
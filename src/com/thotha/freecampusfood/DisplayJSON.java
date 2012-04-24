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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayJSON extends Activity {
	List<Map<String, String>> data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayjson);
        ListView listView = (ListView) findViewById(R.id.listView1);
        data = new ArrayList<Map<String, String>>();     
        String imageurl="http://warm-mountain-2574.herokuapp.com/campus_foods/today.json";
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

        Log.d("conn", "1.0");
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
        String[] values=null;
		JSONArray j,jt;
		try 
		{
					j = new JSONArray(jSONString);
					Integer v1= j.length();
					Log.d("length",v1.toString());
					int temp = j.length();
			        values=new String[v1];
					for(int i=0;i<temp;i++)
					{
						//jt = new JSONArray(j.get(i).toString());
						JSONObject jo = j.getJSONObject(i);
						values[i]=jo.getString("title").toString();
						Log.d("VALUE",values[i]);
						Map<String, String> datum = new HashMap<String, String>(2);
						datum.put("title", jo.getString("title").toString());
						datum.put("id", jo.getString("id").toString());
						datum.put("food", jo.getString("food").toString());
						datum.put("location", jo.getString("location").toString());
						datum.put("start", jo.getString("start").toString());
						data.add(datum);
					}
					Log.d("AAAA",j.get(1).toString());
					JSONObject obj = (JSONObject) j.get(1);

					Log.d("BBBB",obj.get("food").toString());
		} 
		catch (JSONException e1) 
		{
					// TODO Auto-generated catch block
					e1.printStackTrace();
		}

        Integer v = jSONString.length();
        try 
        {
					Log.d("TTTT",v.toString());
		}
        catch (Exception e) 
        {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data,
                        R.layout.my_list,
                        new String[] {"title", "food"},
                        new int[] {android.R.id.text1,
                                   android.R.id.text2});
		listView.setAdapter(adapter);


		OnItemClickListener abcd= new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View arg1,
									int arg2, long arg3) {
						//Toast.makeText(getBaseContext(), "You clciked "+arg3, Toast.LENGTH_LONG).show();
								//	data.
									Map<String, String> datum = new HashMap<String, String>(2);
								datum=data.get(arg2);
						Bundle bundle = new Bundle();
						bundle.putString("id", datum.get("id"));
						bundle.putString("title",datum.get("title"));
						bundle.putString("food",datum.get("food"));
						bundle.putString("location",datum.get("location"));
						bundle.putString("start",datum.get("start"));
						//Toast.makeText(getBaseContext(),datum.get("location") , Toast.LENGTH_LONG).show();
						Intent intent = new Intent(DisplayJSON.this,ShowEvent.class);
						intent.putExtras(bundle);
						startActivity(intent);
						//finish();
					}

		};
		listView.setOnItemClickListener(abcd);
            
    }
}

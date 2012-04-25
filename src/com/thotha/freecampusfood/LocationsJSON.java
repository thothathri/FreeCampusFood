package com.thotha.freecampusfood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class LocationsJSON extends Activity{
	List<Map<String, String>> data;
	private Button newLocationButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationsjson);
        ListView listView = (ListView) findViewById(R.id.listView2);
        data = new ArrayList<Map<String, String>>();     
        String imageurl="http://warm-mountain-2574.herokuapp.com/locations.json";
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
					Log.d("String is",j.toString());
					int temp = j.length();
			        values=new String[v1];
					for(int i=0;i<temp;i++)
					{
						//jt = new JSONArray(j.get(i).toString());
						JSONObject jo = j.getJSONObject(i);
						values[i]=jo.getString("loc").toString();
						Log.d("VALUE",values[i]);
						Map<String, String> datum = new HashMap<String, String>(2);
						datum.put("loc", jo.getString("loc").toString());
						data.add(datum);
					}
					Log.d("AAAA",j.get(0).toString());
					//JSONObject obj = (JSONObject) j.get(1);

					//Log.d("BBBB",obj.get("food").toString());
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
                new String[] {"loc"},
                new int[] {android.R.id.text1});
listView.setAdapter(adapter);
OnItemClickListener abcd= new OnItemClickListener() {
	public void onItemClick(AdapterView parent, View arg1,
							int arg2, long arg3) {
				//Toast.makeText(getBaseContext(), "You clciked "+arg3, Toast.LENGTH_LONG).show();
						//	data.
							Map<String, String> datum = new HashMap<String, String>(2);
						datum=data.get(arg2);
				Bundle bundle = new Bundle();
				bundle.putString("location",datum.get("loc"));
				Log.d("UUUUU",datum.get("loc").toString());
				//Toast.makeText(getBaseContext(),datum.get("location") , Toast.LENGTH_LONG).show();
				Intent intent = new Intent(LocationsJSON.this,CreateEvent.class);
				intent.putExtras(bundle);
				startActivity(intent);
				//finish();
			}

};
listView.setOnItemClickListener(abcd);

this.newLocationButton = (Button) this.findViewById(R.id.newlocation);
// set up a listener for when the button is pressed 
newLocationButton.setOnClickListener(new OnClickListener() {
	public void onClick(View v) {
		Intent intent = new Intent(LocationsJSON.this, CreateLocation.class);
		startActivity(intent);
	    }             
   });

    }
}

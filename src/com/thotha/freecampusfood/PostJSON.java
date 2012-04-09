package com.thotha.freecampusfood;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class PostJSON extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postjson);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://falling-mountain-9910.herokuapp.com/posts.json");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("post[name]", "praveen"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        /*
        Log.d("XXXX","hey1");
        setContentView(R.layout.postjson);
        Map<String, String> ml= new HashMap<String, String>();
        ml.put("name", "Thotha");
        String s = "http://falling-mountain-9910.herokuapp.com/posts";
        String s1 = null;
		try {
			s1 = makeRequest(s, ml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Log.d("XXXX",s1);
        */
        
	}
	
	/*
	public static String makeRequest(String path, Map params)
			throws Exception {
			Log.d("XXXX","hey2");
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httpost = new HttpPost(path);
			Iterator iter = params.entrySet().iterator();

			JSONObject holder = new JSONObject();
			Log.d("XXXX","hey3");
			while(iter.hasNext()) {
			Log.d("XXXX","hey3.5");
			Map.Entry pairs = (Map.Entry)iter.next();
			Log.d("XXXX","hey3.6");
			String key = (String)pairs.getKey();
			Log.d("XXXX","hey3.7");
			Map m = (Map)pairs.getValue();
			Log.d("XXXX","hey4");
			JSONObject data = new JSONObject();
			Iterator iter2 = m.entrySet().iterator();
			while(iter2.hasNext()) {
			Map.Entry pairs2 = (Map.Entry)iter2.next();
			data.put((String)pairs2.getKey(), (String)pairs2.getValue());
			}
			holder.put(key, data);
			}
			Log.d("XXXX","hey5");
			StringEntity se = new StringEntity(holder.toString());
			httpost.setEntity(se);
			httpost.setHeader("Accept", "application/json");
			httpost.setHeader("Content-type", "application/json");
			Log.d("XXXX","hey6");
			ResponseHandler responseHandler = new BasicResponseHandler();
			String response = httpclient.execute(httpost, responseHandler);
			Log.d("PPPP","sup");
			return response;
			}*/

}

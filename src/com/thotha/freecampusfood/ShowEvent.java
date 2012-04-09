package com.thotha.freecampusfood;

import java.io.IOException;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowEvent extends MapActivity{
	MapView mv;
	MapController controller;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        Bundle bundle = this.getIntent().getExtras();
        String title = ("Event:  "+bundle.getString("title"));
        String t = new String(bundle.getString("title"));
        String food = ("Food:  "+bundle.getString("food"));
        String f = new String(bundle.getString("food"));
        String l = new String(bundle.getString("location"));
        String location = ("Location:\n"+bundle.getString("location"));
        String start = ("Time:  "+bundle.getString("start"));
        TextView startView = (TextView) findViewById(R.id.textView4);
        TextView titleView = (TextView) findViewById(R.id.textView1);
        TextView foodView = (TextView) findViewById(R.id.textView2);
        TextView locationView = (TextView) findViewById(R.id.textView3);
        foodView.setText(food);
        titleView.setText(title);
        startView.setText(start);
        locationView.setText(location);
        Log.d("Before Map","hello");
        mv = (MapView)findViewById(R.id.map_view);
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
        mapOverlays.add(itemizedoverlay);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}

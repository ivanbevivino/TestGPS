package com.example.testgps.servicios;



import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;



public class miServicio extends	Service implements LocationListener{



	private final Context ctx;  
	double latitud;
	double longitud;
	double velocidad;
	double altura;
	float presicion;
	Location location;
	boolean gpsactivo;
	TextView texto;
	LocationManager locationmanager;



	public miServicio (Context c){
		super();
		this.ctx = c; 
		try {  
			locationmanager = (LocationManager)this.ctx.getSystemService(LOCATION_SERVICE);
			gpsactivo =locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER);

		}catch(Exception e){e.printStackTrace();}

		getLocation();

	}

	public miServicio (){
		super();
		this.ctx = getApplicationContext();
	}

	public void setView (View v){
		texto = (TextView)v;
		texto.setText(latitud+ " ; "+longitud +" ; "+altura+" m  ; "+ velocidad*3.6 +" Km/Hs" + " " +presicion+ " m");


	}


	

	public void getLocation(){
		try {
			if(gpsactivo){

				locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,10,this);
				if(locationmanager!=null){
					location = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					latitud=location.getLatitude();
					longitud=location.getLongitude();
					velocidad=location.getSpeed();
					altura=location.getAltitude();
					presicion=location.getAccuracy();
					

				}
			}
		}
		catch(Exception e){e.printStackTrace();}




	}



	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}


	public double getLatitud(){return latitud;}
	public double getLongitud(){return longitud;}
	public double getVelocidad(){return velocidad;}
	public double getAltura(){return altura;}
	public double getPresicion(){return presicion;}


	@Override
	public void onLocationChanged(Location location) {
		latitud=location.getLatitude();
		longitud=location.getLongitude();
		velocidad=location.getSpeed();
		altura=location.getAltitude();
	}

	@Override
	public void onProviderDisabled(String provider) {


	}

	@Override
	public void onProviderEnabled(String provider) {


	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extra) {


	}

}

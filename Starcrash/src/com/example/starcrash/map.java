package com.example.starcrash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;



public class map extends FragmentActivity implements
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {
	String sel;
	Spinner spin;
	protected JSONObject mData;
	public GoogleMap mMap;
	LocationClient loc ;
	Marker mark =null;
	String[] nv = { "Leve", "Grave","Muy grave" };

	String usuario;
	String parseuser;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mps);
		

		Parse.initialize(this, "wxRgc6TqQnkvj51xcf0uyl9IPBbxUnMpqXJrCZmj", "CGLz5cB0cgUNUwiZj9oSTYfVWSqXBn0dbIUHB7UT");
		ParseFacebookUtils.initialize("762158053820867");
		 request(ParseFacebookUtils.getSession());
			   
		 
	TextView txt=(TextView)findViewById(R.id.logout);
	Button btn = (Button) findViewById(R.id.reportes);
    btn.setBackgroundColor(Color.argb(110,77, 253, 42));

	Button btn2 = (Button) findViewById(R.id.rts);
    btn2.setBackgroundColor(Color.argb(110,77, 253, 42));
    
    
    
    
    loc = new LocationClient(this,this,this);
    loc.connect();
	
    spin =(Spinner)findViewById(R.id.nivel);

  
    
    
    
    llenarspin();
    
    Parse.initialize(this, "wxRgc6TqQnkvj51xcf0uyl9IPBbxUnMpqXJrCZmj", "CGLz5cB0cgUNUwiZj9oSTYfVWSqXBn0dbIUHB7UT");

    spin.setOnItemSelectedListener(new OnItemSelectedListener() {

           @Override
           public void onItemSelected(AdapterView<?> arg0, View arg1,
                   int position, long id) { 
               // TODO Auto-generated method stub
              sel = nv[position];//saving the value selected
         

           }

           @Override
           public void onNothingSelected(AdapterView<?> arg0) {
               // TODO Auto-generated method stub

           }

       });
    
    
    
    
	btn2.setOnClickListener(new OnClickListener() 
	{

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(map.this, reportes.class);
            startActivity(intent);
            	
			
		}
	});
    
    
    
	
	txt.setOnClickListener(new OnClickListener() 
	{

		@SuppressWarnings("static-access")
		@Override
		public void onClick(View arg0) {
			
			
			ParseUser.getCurrentUser().logOut();
			Intent intent = new Intent(map.this, MainActivity.class);
            startActivity(intent);
            	
			
		}
	});
	
	
	
    
    
    
    
	btn.setOnClickListener(new OnClickListener() 
	{

		@Override
		public void onClick(View arg0) {
    
		
			
			
			if(mark!=null && sel!=null){
				
		    Date date= new Date();
			ParseObject gameScore = new ParseObject("maps");
			gameScore.put("usuario",usuario);
			gameScore.put("lat", String.valueOf(mark.getPosition().latitude));
			gameScore.put("long",String.valueOf(mark.getPosition().longitude));
			gameScore.put("fecha", date);
			gameScore.put("nivel",sel);
			gameScore.saveInBackground();
			mMap.clear();
			
			if(isNetworkAvailable()){
	    		
	    		GetDataTask getDataTask = new GetDataTask();
				getDataTask.execute();
	    		
	    	}
	    	
			
			
			}
			else{
				if(mark==null){
				 Toast.makeText(getApplicationContext(), "Seleccione punto", Toast.LENGTH_SHORT).show();
				
				
				}
				
				if(sel==null){
					
					 Toast.makeText(getApplicationContext(), "Seleccione nivel de gravedad", Toast.LENGTH_SHORT).show();
					
				}
			}
			
		}
		
	
    
    
    
	});

	}
	
	
	  private void request(final Session session) {
  	    Request request = Request.newMeRequest(session, 
  	            new Request.GraphUserCallback() {

  	        @Override
  	        public void onCompleted(GraphUser user, Response response) {
  	            // If the response is successful
  	            if (session == Session.getActiveSession()) {
  	                if (user != null) {
  	                  
  	                  Toast.makeText(getApplicationContext(), "Bienvenido " +user.getFirstName() +" "+user.getLastName(), Toast.LENGTH_SHORT).show();
  	                  usuario= user.getFirstName();
  	                }
  	            }
  	            if (response.getError() != null) {
  	                // Handle error
  	            }
  	        }

			
  	    });
  	    request.executeAsync();
  	} 
	
	
	
	
	
	
	
	Thread localizar = new Thread(new Runnable() {
    	@Override
    	
    	
    	public void run(){
    		
    		 mMap= null;
    		  Location act = loc.getLastLocation();
    	    
    	    	if(act!=null){
    	    		
    	    		if (mMap == null) {
    	         	     
    	       	      mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    	      	
    	      	   
    	      	   
    	      	   if (mMap != null) {
    	     	        
    	       	      
    	     	        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    	     	        	
    	     	         mMap.setMyLocationEnabled(true);
    	     	         mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
    	                  act.getLatitude(), act.getLongitude()), 15));
    	     	         
    	     	        
    	     	         
    	     	    
    	     	  
    	     	         
    	     	        ;
    	     	      }
    	      	   
    	      	   
    	      	  mMap.setOnMapClickListener(new OnMapClickListener() {

    	  			@Override
    	  			public void onMapClick(LatLng arg) {
    	  				
    	  				
    	  				if(mark==null){
    	  					
    	  					
    	  				    mark= mMap.addMarker(new MarkerOptions()
    	   	     	       .position(arg)
    	   	     	     
    	   	     	       .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
    	  				}
    	  				else{
    	  					mark.remove();
    	  					mark=null;
    	  					
    	  				
    	  					
    	  				    mark= mMap.addMarker(new MarkerOptions()
    	   	     	       .position(arg)
    	   	     	     
    	   	     	       .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
    	  					
    	  					
    	  				}
    	  				
    	  				
    	  			}
    	  			
    	  	     
    	  	     
    	  	     });
    	      	   
    	      	   }
    	    		
    	    		
    	    		
    	    		
    	    	}
    		
    	    	
    	    	if(isNetworkAvailable()){
    	    		
    	    		GetDataTask getDataTask = new GetDataTask();
    				getDataTask.execute();
    	    		
    	    	}
    	    	
    	    	
    	    		
    	    		
    	    	
    		
    	}
    	
    });
	
	
	
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		
		localizar.run();
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	
	
public void cargar(){
		
		try {
			JSONArray jsonPosts = mData.getJSONArray("incidentes");
	

			
			for(int i=0; i<jsonPosts.length();i++){
				
				JSONObject post = jsonPosts.getJSONObject(i);
				String lat=post.getString("latitud");
				String lg= post.getString("longitud");
				String lvl = post.getString("nivel");
				String date = post.getString("fecha");
				LatLng p = new LatLng(Double.valueOf(lat),Double.valueOf(lg));
			   String dt =date.substring(9, 19);

				if(lvl.equals("Leve")){
					
					
					Geocoder geocoder = new Geocoder(getBaseContext());
					
					  List<Address> direcciones = null;
					  
					
					  try {
					   direcciones = geocoder.getFromLocation(Double.valueOf(lat), Double.valueOf(lg),1);
					  } catch (Exception e) {
					   Log.d("Error", "Error en geocoder:"+e.toString());
					  }
					  
					 
					  if(direcciones != null && direcciones.size() > 0 ){
					   
					
					   Address direccion = direcciones.get(0);
					   
					
					  String dir = String.format("%s",
					                    direccion.getMaxAddressLineIndex() > 0 ? direccion.getAddressLine(0) : "");
					                                 
			                           
					  Marker myMaker = mMap.addMarker(new MarkerOptions()
   	     	       .position(p)
   	     	       .title(dir)  
   	     	       .snippet("Fecha :" +dt + " Nivel :"+lvl)
   	     	       .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))); 

					  
					  }
					
					
					
				

					

					
				}
                  if(lvl.equals("Grave")){
                	 
                	  Geocoder geocoder = new Geocoder(getBaseContext());
  					
					  List<Address> direcciones = null;
					  
					
					  try {
					   direcciones = geocoder.getFromLocation(Double.valueOf(lat), Double.valueOf(lg),1);
					  } catch (Exception e) {
					   Log.d("Error", "Error en geocoder:"+e.toString());
					  }
					  
					 
					  if(direcciones != null && direcciones.size() > 0 ){
					   
					
					   Address direccion = direcciones.get(0);
					   
					
					  String dir = String.format("%s",
					                    direccion.getMaxAddressLineIndex() > 0 ? direccion.getAddressLine(0) : "");
					                
			                          
                	  
                	 
                	  
                	  
                	  
                		Marker myMaker = mMap.addMarker(new MarkerOptions()
	    	     	       .position(p)
	    	     	       .title(dir)  
	    	     	   .snippet("Fecha :" +dt + " Nivel :"+lvl)
	    	     	       .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

					  }
				}
                if(lvl.equals("Muy grave")){
                	
                	Geocoder geocoder = new Geocoder(getBaseContext());
					
					  List<Address> direcciones = null;
					  
					
					  try {
					   direcciones = geocoder.getFromLocation(Double.valueOf(lat), Double.valueOf(lg),1);
					  } catch (Exception e) {
					   Log.d("Error", "Error en geocoder:"+e.toString());
					  }
					  
					 
					  if(direcciones != null && direcciones.size() > 0 ){
					   
					
					   Address direccion = direcciones.get(0);
					   
					
					  String dir = String.format("%s",
					                    direccion.getMaxAddressLineIndex() > 0 ? direccion.getAddressLine(0) : "");
					                
                	
                	
                	
                	
                	
                	
                	
                	
                	Marker myMaker = mMap.addMarker(new MarkerOptions()
    	     	       .position(p)
    	     	       .title(dir)  
    	     .snippet("Fecha :" +dt + " Nivel :"+lvl)
    	     	       .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


                           }
				
                }
			}
			
			
			
			
			
		} catch (JSONException e) {
		
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public class GetDataTask extends AsyncTask<Object, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(Object... params) {
			int responseCode = -1;
			JSONObject jsonResponse = null;
			try {
				URL blogFeedUsr = new URL("http://www.starcrash.esy.es/finalweb/maps.php");
				HttpURLConnection connection = (HttpURLConnection) blogFeedUsr
						.openConnection();
				connection.connect();

				responseCode = connection.getResponseCode();

				if (responseCode == HttpURLConnection.HTTP_OK) {

					try {
						
						runOnUiThread(new Runnable() {
							  public void run() {
							    Toast.makeText(getApplicationContext(), "Cargando incidentes...", Toast.LENGTH_SHORT).show();
							  }
							});
						
						jsonResponse = new JSONObject(
								readUrl("http://www.starcrash.esy.es/finalweb/maps.php"));

					} catch (JSONException e) {
						e.printStackTrace();
					}

				} else {

				}
				
			} catch (MalformedURLException e) {
			
			} catch (IOException e) {
			
			} catch (Exception e) {
				
			}
			return jsonResponse;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			if (result==null){
				Toast.makeText(getApplicationContext(), "Hubo un problema al realizar la transacción. Por favor inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
			}else{
				mData = result;
				cargar();
			
			}
		}

	}
	
	private boolean isNetworkAvailable() {
		ConnectivityManager manager = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		boolean isNetworkAvaible = false;
		if (networkInfo != null && networkInfo.isConnected()) {
			isNetworkAvaible = true;
		
		} else {
			Toast.makeText(this, "No hay red disponible ", Toast.LENGTH_LONG)
					.show();
		}
		return isNetworkAvaible;
	}
	
	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	
	
public void llenarspin(){
		
		
		Spinner spinner_animales = (Spinner) findViewById(R.id.nivel);
		ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.lvl , android.R.layout.simple_spinner_item);
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_animales.setAdapter(spinner_adapter);
	 
	}
	
	
}

package com.example.starcrash;



import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	
	
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn= (Button)findViewById(R.id.login);
		btn.setBackgroundColor(Color.argb(186,18, 214, 236));
		
		
		
		Parse.initialize(this, "wxRgc6TqQnkvj51xcf0uyl9IPBbxUnMpqXJrCZmj", "CGLz5cB0cgUNUwiZj9oSTYfVWSqXBn0dbIUHB7UT");
		ParseFacebookUtils.initialize("762158053820867");
		
		
		
		btn.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0) {
			
				
				ParseFacebookUtils.logIn(MainActivity.this, new LogInCallback() {
					  @Override
					  public void done(ParseUser user, ParseException err) {
					    if (user == null) {
					      Log.d("Starcrash ", "Uh oh. The user cancelled the Facebook login.");
					    } else if (user.isNew()) {
					      Log.d("Starcrash ", "User signed up and logged in through Facebook!");
					    } else {
					    	
					    	
					    	
					    	Intent intent = new Intent(MainActivity.this, map.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
       
                            startActivity(intent);
                            
                           
					    }
					  }

				
					 	  
					  
					} );
				
				
			}
			
		}
			
		);
		
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}
	
	
	
	
}

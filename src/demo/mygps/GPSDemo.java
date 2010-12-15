package demo.mygps;


import java.util.LinkedList;
import java.util.Queue;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GPSDemo extends Activity {
    private LocationManager lm;
	private MyLocationListener locationListener;
	public Location[] locationhistory = new Location[10];
	public LinkedList<Location> locq = new LinkedList<Location>();
	
	int loccount = 0;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	for(int i = 0;i<10;i++)
    	{
    		locationhistory[i] = null;
    		
    	}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        lm = (LocationManager) 
        getSystemService(Context.LOCATION_SERVICE);    
     
        LinearLayout layout = new
        LinearLayout(getApplicationContext());
        layout.setOrientation(1);
     
        TextView label = new
        TextView(getApplicationContext());
        
        Button b = new Button(this);
        b.setText("Print Last Locations");
        
        
        final TextView locations = new
        TextView(getApplicationContext());
        locations.setPadding(5, 3, 10, 3);
        //locations.setId(R.id.loc_text);
        //locations.
        
        
        
        label.setText("GPS Location Demo");
        label.setPadding(5, 3, 10, 3);
        layout.addView(label);
        layout.addView(b);
        layout.addView(locations);
        
        
        
        
        

        LinearLayout container = (LinearLayout)
        findViewById
        (R.id.my_view);
        container.addView(layout);
        
        
        
        locationListener = new MyLocationListener();
    
    lm.requestLocationUpdates(
        LocationManager.GPS_PROVIDER, 
        0, 
        0, 
        locationListener);    
    
    b.setOnClickListener(new View.OnClickListener()  {
    	public void onClick(View v) 
    	{
    	String s ="";
        for(int i = 0;i<loccount;i++)
        {
        	Log.i("getgps",""+i);
        	s = s+locq.get(i).getLatitude()+" "+locq.get(i).getLongitude()+"\n";
        }
        locations.setText(s);
    	}
    });
    
    
    
    
    

        
        
    }
    
    
    
    
    
    
    
    public class MyLocationListener implements LocationListener 				//function getting GPS co ords
    {
        @Override
        public void onLocationChanged(Location loc) {
            if (loc != null) {
                Toast.makeText(getBaseContext(), 
                    "Location is : Lat: " + loc.getLatitude() + 
                    " Lng: " + loc.getLongitude(), 
                    Toast.LENGTH_SHORT).show();
           
            }
            
            //locationhistory[loccount] = loc;
            locq.add(loc);
            loccount++;
            if(loccount == 10)
            	locq.removeFirst();
            
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider, int status, 
            Bundle extras) {
            // TODO Auto-generated method stub
        }
    }     
    
    
}
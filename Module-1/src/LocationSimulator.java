

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Enumeration;
import java.util.StringTokenizer;

public class LocationSimulator
{
	private Properties locationData;
	private Location location;

	public LocationSimulator() throws java.io.FileNotFoundException,java.io.IOException 
	{
		this.locationData = new Properties();
		this.locationData.load(new FileInputStream("locationdata.txt"));
		this.loadLocation();		
	}

	
	public Location getLocation()
	{
		return this.location;
	}
	
	public Location getLocation(String prevLocId)
	{
		this.loadLocation(prevLocId);
		return this.location;
	}
	
	
	// Load a location related to the near by location
	private void loadLocation(String prevLocId)
	{
		String prevLoc = this.locationData.getProperty(prevLocId);
		StringTokenizer st = new StringTokenizer(prevLoc, ":!:");
		String nearbyLocValue = "";
		while (st.hasMoreElements()) 
		{
			nearbyLocValue = st.nextElement().toString();
		}
		if(nearbyLocValue.equals("NA"))
		  loadLocation();
		else
		{
		  if(nearbyLocValue.indexOf(",") > 0)
		  {
		    int i = 0;
		    Random randomGenerator = new Random();
		    String[] nearbyLocs = nearbyLocValue.split(",");
		    int randomInt = randomGenerator.nextInt(nearbyLocs.length);
		    populateLocationFields(nearbyLocs[randomInt]);
		  }
		  else
		      populateLocationFields(nearbyLocValue);
		}
	}
	
	// Load a random location
	private void loadLocation()
	{
		// Start with a random location row from the locationdata.txt
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(this.locationData.size());
		int i = 0;
		String key = "";
		for (Enumeration e = this.locationData.keys(); e.hasMoreElements();) 
		{
			key = (String) e.nextElement();	
			i++;
			if(i >= randomInt) break;
		}
		populateLocationFields(key);  
	}
	
	private void populateLocationFields(String key)
	{
		this.location = new Location();	
		this.location.setLocationId(key);
		String locationRow = this.locationData.getProperty(key);
		StringTokenizer st = new StringTokenizer(locationRow, ":!:");
		while (st.hasMoreElements()) 
		{
		  this.location.setLocationName(st.nextElement().toString());
		  this.location.setCityName(st.nextElement().toString());
		  this.location.setLongitude(st.nextElement().toString());
		  this.location.setLatitude(st.nextElement().toString());
		  //Skip the nearest locations data
		  st.nextElement();
		}
	}
	
	
}
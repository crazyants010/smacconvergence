/**
	Project:IMS Project
	File: MobileDevice.java
	Author: Gopalakrishnan.T
	Description: This java class simulates several Mobile devices. This class is 
		implementing Runnable interface. Below is the example of initiating several
		Mobile Devices:
		
			MobileDevice iphone = new MobileDevice("Apple iPhone");
			new Thread(iphone).start();
			MobileDevice samsung = new MobileDevice("Samsung Galaxy S");
			new Thread(samsung).start();
			MobileDevice lg = new MobileDevice("LG Optimus One");
			new Thread(lg).start();
			MobileDevice htc = new MobileDevice("HTC Wildfire");
			new Thread(htc).start();
			MobileDevice micromax = new MobileDevice("Micromax");
			new Thread(micromax).start();
		
		Once initiated, these Mobile devices will keep changing their location every 
		one minute.
		
		At a given point of time, the mobile's current location can be got by calling
		getCurrentLocation() as below:
		
			Location loc = iphone.getCurrentLocation();
		
		The current location information can be printed by calling printLocationInfo()
			
			iphone.printLocationInfo();

**/


public class MobileDevice implements Runnable 
{
	private LocationSimulator locsim;
	private String mobileId;
	private String prevLocId;
	
	public MobileDevice() throws java.io.FileNotFoundException,java.io.IOException 
	{
		this.mobileId = "Mobile 1";
		this.prevLocId = "500001";
		this.locsim = new LocationSimulator();
	}
	
	public MobileDevice(String mobileId) throws java.io.FileNotFoundException,java.io.IOException
	{
		this.mobileId = mobileId;
		this.prevLocId = "500001";
		this.locsim = new LocationSimulator();
	}
	
	public MobileDevice(String mobileId, String prevLocId) throws java.io.FileNotFoundException,java.io.IOException
	{
		this.mobileId = mobileId;
		this.prevLocId = prevLocId;
		this.locsim = new LocationSimulator();
	}

	public Location getNewLocation()
	{
	   Location currLoc = this.locsim.getLocation(prevLocId);
	   this.prevLocId = currLoc.getLocationId();
	   return currLoc;
	}

	public Location getCurrentLocation()
	{
		return this.locsim.getLocation();
	}
	
	public String getMobileId()
	{
	  return mobileId;
	}

	public void printLocationInfo()
	{
		Location mobLoc = this.locsim.getLocation();
		System.out.println("Mobile: "+this.getMobileId()+ "\nLocation: "+ mobLoc.getLocationName()+"\nCity: "+mobLoc.getCityName()+"\nLongitude: "+mobLoc.getLongitude()+"\nLatitude: "+mobLoc.getLatitude()+"\n\n\n");
	}
	/**
		The run method, gets the mobile location every 1 minute.
	**/
	public void run() 
	{
		try 
		{
			while(true)
			{
				this.getNewLocation();
				Thread.sleep(2000);
			}
		}
		catch (InterruptedException e) 
		{
			System.out.println("Child interrupted.");
		}
	}
	
	public static void main(String args[]) throws Exception
	{
			//Initate several mobile devices
			MobileDevice iphone = new MobileDevice("Apple iPhone");
			new Thread(iphone).start();
		/*	MobileDevice samsung = new MobileDevice("Samsung Galaxy S");
			new Thread(samsung).start();
			MobileDevice lg = new MobileDevice("LG Optimus One");
			new Thread(lg).start();
			MobileDevice htc = new MobileDevice("HTC Wildfire");
			new Thread(htc).start();
			MobileDevice micromax = new MobileDevice("Micromax");
			new Thread(micromax).start(); */

			// print the mobile's current location every 1 minute
			while(true)
			{
				iphone.printLocationInfo();
			/*	samsung.printLocationInfo();
				lg.printLocationInfo();
				htc.printLocationInfo();
				micromax.printLocationInfo();				
				System.out.println("-----------------------------------------------------"); */
				Thread.sleep(2000);
			}
			
	}
}
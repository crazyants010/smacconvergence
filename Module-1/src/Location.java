

public class Location
{
	private String locationId;
	private String locationName;
	private String cityName;
	private String longitude;
	private String latitude;

	public Location()
	{
		this.locationId = "";
		this.locationName = "";
		this.cityName = "";
		this.longitude = "";
		this.latitude = "";
	}
	
	public String getLocationId()
	{
		return locationId;
	}
	
	public String getLocationName()
	{
		return locationName;
	}
	
	public String getCityName()
	{
		return cityName;
	}
	
	public String getLongitude()
	{
		return longitude;
	}
	
	public String getLatitude()
	{
		return latitude;
	}
	
	public void setLocationId(String locationId)
	{
		this.locationId = locationId;
	}

	public void setLocationName(String locationName)
	{
		this.locationName = locationName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	

	
}
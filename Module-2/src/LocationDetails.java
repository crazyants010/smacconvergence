import java.io.Serializable;

// Stores the location information and the cutoff information 
// required for application of rules.
public class LocationDetails implements Serializable  {
	String locationName;
	String area;
	String longitude;
	String latitude;
	int temperature;
	int minTemperature, maxTemperature;
	String weatherDescription;
	int humidity;
	int cutOff_humid;
	int cutoff_min_temp;
	int cutoff_max_temp;
	int cutoff_sentiment;
	int veooz_value;

	public int getVeooz_value() {
		return veooz_value;
	}

	public void setVeooz_value(int veooz_value) {
		this.veooz_value = veooz_value;
	}

	public int getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(int minTemperature) {
		this.minTemperature = minTemperature;
	}

	public int getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getCutOff_humid() {
		return cutOff_humid;
	}

	public void setCutOff_humid(int cutOff_humid) {
		this.cutOff_humid = cutOff_humid;
	}

	public int getCutoff_min_temp() {
		return cutoff_min_temp;
	}

	public void setCutoff_min_temp(int cutoff_min_temp) {
		this.cutoff_min_temp = cutoff_min_temp;
	}

	public int getCutoff_max_temp() {
		return cutoff_max_temp;
	}

	public void setCutoff_max_temp(int cutoff_max_temp) {
		this.cutoff_max_temp = cutoff_max_temp;
	}

	public int getCutoff_sentiment() {
		return cutoff_sentiment;
	}

	public void setCutoff_sentiment(int cutoff_sentiment) {
		this.cutoff_sentiment = cutoff_sentiment;
	}
}

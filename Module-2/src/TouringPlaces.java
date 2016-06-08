import java.io.IOException;
import java.util.Vector;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TouringPlaces 
{
	public  LocationDetails getWeather(String url,Dataclass dc)
	{
		LocationDetails loc = new LocationDetails();
		int size=dc.getData_list().size();
	//	System.out.println(size +"is the size");
		DataHelper datahelper_array[]=new DataHelper[size];
		int k=0;
		for(DataHelper dh : dc.getData_list())
		{
			datahelper_array[k] =dh;
			k++;
		}
		try 
		{	 
			System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
			System.getProperties().put("http.proxyPort", "8080");
			Document doc = Jsoup.connect(url).get();
			String tag=datahelper_array[0].getTag_name();
			System.out.println(datahelper_array[0].getAttr_name());
			Element urls;
			urls = doc.select(tag).select(datahelper_array[0].getAttr_name()).get(0);							
			loc.setTemperature(Integer.parseInt(urls.text()));
			//System.out.println(urls.text());
			urls = doc.select(tag).select(datahelper_array[1].getAttr_name()).get(0);
			loc.setWeatherDescription(urls.text());			
			urls = doc.select(tag).select(datahelper_array[2].getAttr_name()).get(0);			
			loc.setHumidity(Integer.parseInt(urls.text()));	
			
			tag=datahelper_array[3].getTag_name();
			urls = doc.select(tag).select(datahelper_array[3].getAttr_name()).get(0);							
			loc.setMaxTemperature(Integer.parseInt(urls.text()));
			//System.out.println(urls.text());
			urls = doc.select(tag).select(datahelper_array[4].getAttr_name()).get(0);
			loc.setMinTemperature(Integer.parseInt(urls.text()));			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return loc;
	}
	
	public  LocationDetails getLongitude(String url,Dataclass dc)
	{		
		LocationDetails loc = new LocationDetails();
		int size=dc.getData_list().size();
		DataHelper datahelper_array[]=new DataHelper[size];
		int k=0;
		for(DataHelper dh : dc.getData_list())
		{
			datahelper_array[k] =dh;
			k++;
		}
		try 
		{	 
			System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
			System.getProperties().put("http.proxyPort", "8080");
			Document doc = Jsoup.connect(url).get();
			String tag=datahelper_array[0].getTag_name();						
			Element urls;
			urls = doc.select(tag).select(datahelper_array[0].getAttr_name()).get(0);		
						
			loc.setLatitude(urls.text());
			//System.out.println(urls.text());
						
			urls = doc.select(tag).select(datahelper_array[1].getAttr_name()).get(0);			
			loc.setLongitude(urls.text());		
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return loc;
	}
	
	public  Vector<LocationDetails> getLocationInfo(Vector<String> urls,Dataclass dc)
	{
		Vector<LocationDetails> locInfo = new Vector<LocationDetails>();
		int size=dc.getData_list().size();
		DataHelper datahelper_array[]=new DataHelper[size];
		int k=0;
		for(DataHelper dh : dc.getData_list())
		{
			datahelper_array[k] =dh;
			k++;
		}
		int i=0;
		
		for(String url : urls)
		{		
			try 
			{	 
				System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
				System.getProperties().put("http.proxyPort", "8080");

				Document doc = Jsoup.connect(url).timeout(100000).get();									
				Elements stores = doc.select(datahelper_array[1].getTag_name());
				Elements loc = doc.select(datahelper_array[2].getTag_name());
				
				for(Element element : stores)
				{
					if(element.attr(datahelper_array[1].getAttr_name()).compareTo(datahelper_array[1].getAttr_val())==0)
					{
						LocationDetails location=new LocationDetails();
						location.setLocationName(element.text());
						locInfo.add(location);
						//System.out.println(sInfo.getStoreName());
					}
				}
			
			
			for(Element element : loc)
			{
				if(element.attr(datahelper_array[2].getAttr_name()).compareTo(datahelper_array[2].getAttr_val())==0)
				{
					LocationDetails location = new LocationDetails();
					location=locInfo.get(i);
					location.setArea(element.text());
					String uri;
					XMLParsing xml = new XMLParsing();
					Dataclass dc_latlong = xml.parseXmlFile(Mainclass.XMLFILENAME,"latlong data");
					uri = dc_latlong.getUrl();
					uri += location.getArea()+"&sensor=true";
					LocationDetails location1 = new LocationDetails();
					location1=getLongitude(uri,dc_latlong);
					location.setLatitude(location1.getLatitude());
					location.setLongitude(location1.getLongitude());
					XMLParsing xml1 = new XMLParsing();
					Dataclass dc_weather=xml1.parseXmlFile(Mainclass.XMLFILENAME, "weather data");
					uri =dc_weather.getUrl();
					uri+=location.getLatitude()+"%2C"+location.getLongitude()+"&format=xml&num_of_days=1&date=today&key=r97p75r72feu3tqxchj6d3r2";
					//System.out.println(uri);
//"http://api.worldweatheronline.com/free/v1/weather.ashx?q="+longi+"%2c"+latti+"&format=xml&num_of_days=1&date=today&key=r97p75r72feu3tqxchj6d3r2";	
					location1=getWeather(uri,dc_weather);
					location.setTemperature(location1.getTemperature());
					location.setHumidity(location1.getHumidity());
					location.setWeatherDescription(location1.getWeatherDescription());
					location.setMinTemperature(location1.getMinTemperature());
					location.setMaxTemperature(location1.getMaxTemperature());
					XMLParsing xml_2 = new XMLParsing();
					Dataclass dc_2 = xml_2.parseXmlFile(Mainclass.XMLFILENAME,"veeoz data");
					VeeozParsing vp = new VeeozParsing();
					int c =vp.getVeeozData(location.getLocationName(),dc);
					if(c==-101)
					{
						location.setVeooz_value(Mainclass.Random_veeoz_location_value);
					}
					else
						location.setVeooz_value(c);
					locInfo.setElementAt(location, i);
					i++;
				}
			}
		
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		}
	
		return locInfo;
	}
	
	public  Vector<String> getURLList(String url,Dataclass dc)
	{
		Vector<String> v=new Vector<String>();
		System.out.println(url);
		try 
		{	 
			System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
			System.getProperties().put("http.proxyPort", "8080");
			Connection con = Jsoup.connect(url).timeout(10*1000);
			Document doc = con.get();
			int size=dc.getData_list().size();
			DataHelper datahelper_array[]=new DataHelper[size];
			int i=0;
			for(DataHelper dh : dc.getData_list())
			{
				datahelper_array[i] =dh;
				i++;
			}
			String tag=datahelper_array[0].getTag_name();
			String attr=datahelper_array[0].getAttr_name();
			String attr_val=datahelper_array[0].getAttr_val();
			System.out.println(tag);
			Elements urls = doc.select(tag);
			
			for(Element element: urls)
			{
				if(element.attr(attr).compareTo(attr_val)==0)
				{
					System.out.println("entered here");
					Elements e=element.select("a[href]");
					v.add(e.attr("href"));
				}
								
			}
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return v;		
	}
	
	public  Vector<LocationDetails> getData(String tour,String area,String city) 
	{
		String url;
		String query=tour.replaceAll(" ", "+");	
		
		Vector<String> urlList = new Vector<String>();		
		XMLParsing xml = new XMLParsing();
		System.out.println(tour);
		Dataclass dc = xml.parseXmlFile(Mainclass.XMLFILENAME,tour);
		url=dc.getUrl();
		url+=city+"/"+area+"/"+query+"/?searchNearby=true";
	//	urlList=getURLList(url,dc);
	//	System.out.println(urlList.size());
		urlList.add(0, url);
		return getLocationInfo(urlList,dc);
		//System.out.println(urlList.size());
		
		
		
	}


}
	
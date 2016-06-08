
public class TouristXML 
{
	String UserID = "", Location = "", City = "", Action = "", DeveloperID = "", Radius = "";
	String Veeoz = "", Time = "";
	int RequestId;
	int minimum_temp;
	int maximum_temp;
	int humidity ;
	TouristXML(int RequestId, String UserID, String Location, String City, String Action, String DeveloperID, String Radius, String Veeoz, String Time,int max,int min,int hum)
	{
		this.RequestId = RequestId;
		this.UserID = UserID;
		this.Location = Location;
		this.City = City;
		this.Action = Action;
		this.DeveloperID = DeveloperID;
		this.Radius = Radius;
		this.Veeoz = Veeoz;
		this.Time = Time;
		this.maximum_temp=max;
		this.minimum_temp=min;
		this.humidity=hum;
	}
	
	public String getTouristXML()
	{
		String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<UserRequest>\n" +
						"	<RequestId>"+this.RequestId+"</RequestId>\n" +
						"	<UserId>"+this.UserID+"</UserId>\n" +
						"   <Location>"+this.Location+"</Location>\n" +
						"   <City>"+this.City+"</City>\n" +
						"   <DeveloperId>"+this.DeveloperID+"</DeveloperId>\n" +
						"   <Tourist>\n" +
						"	  	<Radius>"+this.Radius+"</Radius>\n" +
						"   </Tourist>\n" +
						"	<Temperature>" +
						"<Mim>"+this.minimum_temp+"</Min>"+
						"<Max>"+this.maximum_temp+"+</Max>"+
						"   </Temperature>" +
						"<Humidity>"+this.humidity+"</Humidity>"+
						"   <Veooz>"+this.Veeoz+"</Veooz>\n" +
						"   <Action>"+this.Action+"</Action>\n" +
						"   <Time>"+this.Time+"</Time>\n" +
						"</UserRequest>";
		
		return XML;
	}
}
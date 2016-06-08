import javax.ws.rs.FormParam;


public class ProductXML 
{
	String UserID = "", Location = "", City = "", Action = "", DeveloperID = "", ProductName = "", PStart = "", PEnd = "";
	String Veeoz = "", Time = "", ShopRating = "";
	int RequestId;
	
	ProductXML(int RequestId, String UserID, String Location, String City, String Action, String DeveloperID, String ProductName, String PStart, String PEnd, String Veeoz, String Time, String ShopRating)
	{
		this.RequestId = RequestId;
		this.UserID = UserID;
		this.Location = Location;
		this.City = City;
		this.Action = Action;
		this.DeveloperID = DeveloperID;
		this.ProductName = ProductName;
		this.PStart = PStart;
		this.PEnd = PEnd;
		this.Veeoz = Veeoz;
		this.Time = Time;
		this.ShopRating = ShopRating;
	}
	
	public String getProductXML()
	{
		String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<UserRequest>\n" +
						"	<RequestId>"+this.RequestId+"</RequestId>\n" +
						"	<UserId>"+this.UserID+"</UserId>\n" +
						"   <Location>"+this.Location+"</Location>\n" +
						"   <City>"+this.City+"</City>\n" +
						"   <DeveloperId>"+this.DeveloperID+"</DeveloperId>\n" +
						"   <Product>\n" +
						"		<ProductName>"+this.ProductName+"</ProductName>\n" +
						"		<PriceRange>\n" +
						"			<Start>"+this.PStart+"</Start>\n" +
						"			<End>"+this.PEnd+"</End>\n" +
						"     	</PriceRange>\n" +
						"		<ShopRating>"+this.ShopRating+"</ShopRating>\n" +
						"	</Product>\n"+
						"   <Veooz>"+this.Veeoz+"</Veooz>\n" +
						"   <Action>"+this.Action+"</Action>\n" +
						"   <Time>"+this.Time+"</Time>\n" +
						"</UserRequest>";
		
		return XML;
	}
}

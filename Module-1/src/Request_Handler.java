import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Request_Handler {	
	
	public static int RequestId=0;

 	@POST
    @Path("/userRequest1")
	 	public void getTouristData(@FormParam("UserID") String UserID, @FormParam("Location") String Location, @FormParam("City") String City, @FormParam("Action") String Action, @FormParam("DeveloperID") String DeveloperID, @FormParam("Radius") String Radius) throws Exception
	 	{
		 	String Veeoz = "50.0";
		 	String Time = "10";
		 	RequestId = RequestId+1;
		 	int max=40;
		 	int min=20;
		 	int hum=8;
		 	TouristXML t = new TouristXML(RequestId, UserID, Location, City, Action, DeveloperID, Radius, Veeoz, Time,max,min,hum);
		 	String TXML = t.getTouristXML();
		 	t=null;
		 	String inputFilePath = "./User_Input_Files/";

	    	String fileName = inputFilePath+"TouristXML_"+RequestId;
		 	FileWriter fwo = new FileWriter( fileName, false );
			BufferedWriter bwObj = new BufferedWriter( fwo );
			bwObj.write(TXML);
			bwObj.flush();
			bwObj.close();
	 	}



		@POST
	@Path("/userRequest2")  
	public void getProductData(@FormParam("UserID") String UserID, @FormParam("Location") String Location, @FormParam("City") String City, @FormParam("Action") String Action, @FormParam("DeveloperID") String DeveloperID, @FormParam("ProductName") String ProductName, @FormParam("PStart") String PStart, @FormParam("PEnd") String PEnd) throws Exception 
	{
			String ShopRating = "4";
			String Veeoz = "50.0";
			String Time = "10";
			RequestId = RequestId+1;
			ProductXML p = new ProductXML(RequestId, UserID, Location, City, Action, DeveloperID, ProductName, PStart, PEnd, Veeoz, Time, ShopRating);
			String PXML = p.getProductXML();
			p=null;
			String inputFilePath = "./User_Input_Files/";		 	
			
	    	String fileName = inputFilePath+"ProductXML_"+RequestId;
		 	FileWriter fwo = new FileWriter(fileName , false );
			BufferedWriter bwObj = new BufferedWriter( fwo );

			bwObj.write(PXML);
			bwObj.flush();
			bwObj.close();

	}
}
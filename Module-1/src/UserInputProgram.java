import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.codec.DecoderException;

public class UserInputProgram 
{

	public static void main(String args[])
	 {
		boolean toggle = true;
		//while(true)
		try
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter the UserID: ");
			
			String UserId = "1";//br.readLine().toString();
			
			System.out.println("Enter the Location: ");
			String Location = "gachi";//br.readLine().toString();
			
			System.out.println("Enter the City: ");
			String City = "hyd";//br.readLine().toString();
			
			System.out.println("Enter the Action: ");
			String Action = "post";//br.readLine().toString();
			
			System.out.println("Enter the DeveloperID: ");
			
			String DevId=br.readLine().toString();;
		/*	if(toggle)
			DevId = "1";//br.readLine().toString();
			else DevId="2"; */
			
			if(DevId.equalsIgnoreCase("1"))
			{
				System.out.println("Enter the Radius: ");
				String Radius = "3";//br.readLine().toString();
				
				HttpClient client = new HttpClient();
				PostMethod postMethod = new PostMethod("http://localhost:5535/userRequest1");
				postMethod.addParameter("UserID", UserId);
				postMethod.addParameter("Location", Location);
				postMethod.addParameter("City", City);
				postMethod.addParameter("Action", Action);
				postMethod.addParameter("DeveloperID", DevId);
				postMethod.addParameter("Radius", Radius);
				client.executeMethod(postMethod);
				
				//System.out.println("2");
			}
			else if(DevId.equalsIgnoreCase("2"))
			{
				System.out.println("Enter the ProductName: "); 
				String ProductName = "WASHING MACHINE";//br.readLine().toString();
				
				System.out.println("Enter the Start Price Value: ");
				String PStart = "0";//br.readLine().toString();
				
				System.out.println("Enter the End Price Value: ");
				String PEnd = "12500";//br.readLine().toString();
				
				HttpClient client = new HttpClient();
				PostMethod postMethod = new PostMethod("http://localhost:5535/userRequest2");
				postMethod.addParameter("UserID", UserId);
				postMethod.addParameter("Location", Location);
				postMethod.addParameter("City", City);
				postMethod.addParameter("Action", Action);
				postMethod.addParameter("DeveloperID", DevId);
				postMethod.addParameter("ProductName", ProductName);
				postMethod.addParameter("PStart", PStart);
				postMethod.addParameter("PEnd", PEnd);
				client.executeMethod(postMethod);
				
				//System.out.println("2");
			}
			else
				System.out.println("Error: Wrong Developer Id ");
			
			if(toggle) toggle=false;
			else toggle=true;
		}
		catch (HttpException e) {
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
	 }
}

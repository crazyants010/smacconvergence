
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.codec.DecoderException;

public class Differentiator {
	 String param4;
	public  PostMethod findType(String s,TouristDeveloper td,PriceDeveloper pd) {
		
		String substr=s.substring(s.indexOf("(")+1,s.indexOf(")"));
		String param[]=substr.split(",");
		String res="http://localhost:5565/getinfo/"+param[0];
	
		PostMethod postMethod = new PostMethod(res);
	
		{
			if(param[0].compareToIgnoreCase("Product")==0)
			{
				postMethod.addParameter(param[1],pd.getQuery());
				postMethod.addParameter(param[2],pd.getMin()+"");
				postMethod.addParameter(param[3],pd.getMax()+"");
			}
			else if(param[0].compareToIgnoreCase("location")==0)
			{
				postMethod.addParameter(param[1],td.getLoc());
				postMethod.addParameter(param[2],td.getCity());
			}
		}
		param4=param[param.length-1];
		return postMethod;
	}
	
}

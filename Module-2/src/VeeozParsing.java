import java.io.IOException;
import java.util.HashMap;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class VeeozParsing {

	 HashMap<String,Integer> hm = new HashMap<String, Integer>();
	 int getVeeozData(String query,Dataclass dc) {
		 
			Document doc;
			int result=0;
			boolean positive=false;
			boolean neutral=false;
			query=query.replaceAll(" ","+");
			
			if(hm.get(query)!=null)
			{
				return hm.get(query);
			}
			System.out.println(query);
			try {
		 
				System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
				System.getProperties().put("http.proxyPort", "8080");
				System.getProperties().put("http.proxyUser", "");
				System.getProperties().put("http.proxyPassword", "");
				// need http protocol
				doc = Jsoup.connect(dc.getUrl()+query).get();
				int size=dc.getData_list().size();
				DataHelper datahelper_array[]=new DataHelper[size];
				int i=0;
				for(DataHelper dh : dc.getData_list())
				{
					datahelper_array[i] =dh;
					i++;
				}				
				Elements link1 = doc.select(datahelper_array[0].getTag_name());
				
				for(Element lnk : link1)
				{
					if(lnk.attr(datahelper_array[0].getAttr_name()).compareTo(datahelper_array[0].getAttr_val()) == 0 && lnk.attr("style")=="")
					{
						
						positive=true;
						break;
					}
					
					if(lnk.attr(datahelper_array[1].getAttr_name()).compareTo(datahelper_array[1].getAttr_val()) == 0 && lnk.attr("style")=="")
					{
						positive=false;
						break;
					}
					
					if(lnk.attr(datahelper_array[2].getAttr_name()).compareTo(datahelper_array[2].getAttr_val()) == 0 && lnk.attr("style")=="")
					{
						neutral =true;
						break;
					}
						
				}
				
				Elements links = doc.select(datahelper_array[3].getTag_name());
				for (Element link : links) {
		 
					// get the value from href attribute
					//System.out.println("class: "+link.attr("id"));
					if(link.attr(datahelper_array[3].getAttr_name()).compareTo(datahelper_array[3].getAttr_val())==0)
					{
						if(link.text().compareToIgnoreCase("N?A")!=0)
						result=Integer.parseInt(link.text());
						
						else
							result=-40;
					}
		 
				}
		 
			} catch (HttpStatusException e) {
				hm.put(query, -101);
			
				return -101;
			}
			catch (IOException e) {
				hm.put(query, -101);
				
				return -101;
			}
		 
			if(positive) {   hm.put(query, result); return result; }
			else     {   hm.put(query, (0-result)); return (0-result); }
		  }
  }
 


	import java.util.Vector;

	import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;
	import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


	public class Mobileprices {
		void getPriceDatafromPage(String url,Vector<Productlist> products_list,DataHelper[] datahelper_array)
		{
			Document doc;
			try
			{
				System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
				System.getProperties().put("http.proxyPort", "8080");
				System.getProperties().put("http.proxyUser", "");
				System.getProperties().put("http.proxyPassword", "");
			//	System.out.println(url);
				doc = Jsoup.connect(url).timeout(10*1000).get();
				
				
				Elements link1 = doc.select(datahelper_array[1].getTag_name());
				String temp_brand=null;
				String desc=null;
				for(Element lnk : link1)
				{
					Productlist pd=new Productlist();
					
					if(lnk.attr(datahelper_array[1].getAttr_name()).compareTo(datahelper_array[1].getAttr_val()) == 0)
					{
						temp_brand=lnk.text();
					}
					if(lnk.attr(datahelper_array[2].getAttr_name()).compareTo(datahelper_array[2].getAttr_val())==0)
					{
						desc=lnk.text();
					}
					if(lnk.attr(datahelper_array[3].getAttr_name()).compareTo(datahelper_array[3].getAttr_val()) == 0)
					{
						
						String temp=lnk.text().substring(4,lnk.text().length());
						pd.setPrice(Integer.parseInt(temp));
						pd.setBrandname(temp_brand.substring(0, temp_brand.indexOf(" ")));
						pd.setProductid(temp_brand);
						pd.setDescription(desc);
						products_list.add(pd);
					}
					
					
				}
			/*	Elements link2 = doc.select("a");
				int i=0;
				for(Element lnk : link2)
				{
				
					if(lnk.attr("style").compareTo("text-decoration:none; color:#347235; font-weight:bold") == 0)
					{
						
						Productlist pd =products_list.get(i);
						pd.setProductid(lnk.text());
						products_list.set(i, pd);
						i++;
					}
					
				} */
				 
				
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		Vector <Productlist> getMobilePriceData(int lower_limit,int upper_limit,Dataclass dc,Vector <Productlist> products_list)
		{
			
			Document doc;
			int number = 0;
			String url="http://www.mobilepriceindia.co.in/search-price/"+lower_limit+"-"+upper_limit+"/all";
			try
			{
				System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
				System.getProperties().put("http.proxyPort", "8080");
				System.getProperties().put("http.proxyUser", "");
				System.getProperties().put("http.proxyPassword", "");
				doc = Jsoup.connect(url).get();
				int size=dc.getData_list().size();
				DataHelper datahelper_array[]=new DataHelper[size];
				int i=0;
				for(DataHelper dh : dc.getData_list())
				{
					datahelper_array[i] =dh;
					i++;
				}
				Elements link1 = doc.select(datahelper_array[0].getTag_name());
				System.out.println("entered here");
				for(Element lnk : link1)
				{
					if(lnk.attr(datahelper_array[0].getAttr_name()).compareTo(datahelper_array[0].getAttr_val()) == 0)
					{
						
						String txt=lnk.text();
						String num=txt.substring(6,txt.length());
						num=num.substring(0, num.indexOf(" "));
						 number=Integer.parseInt(num);
						number=number/10;
					}
				}
				getPriceDatafromPage(url, products_list,datahelper_array);
				for(int m=1;m<number;m++)
				{
					getPriceDatafromPage(url+"?p="+m, products_list,datahelper_array);
				}
				
				
				
			}
				
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return products_list;
		}
	}



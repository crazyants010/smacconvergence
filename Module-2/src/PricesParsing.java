import java.util.HashMap;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class PricesParsing {

	Vector <Productlist> getData(String query)
	{
		System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
		System.getProperties().put("http.proxyPort", "8080");
		XMLParsing xm = new XMLParsing();
		Vector <Productlist> products_list=new Vector<Productlist>();
		Dataclass dc=xm.parseXmlFile(Mainclass.XMLFILENAME, query);
		if(dc.isMultiple())
		{
		    if(dc.getProduct_name().compareToIgnoreCase("mobile phones")==0)
		    {
		    	Mobileprices mp=new Mobileprices();
		    //	products_list=mp.getMobilePriceData(1,1000, dc,products_list);
		    //	products_list=mp.getMobilePriceData(1000,2000, dc,products_list);
		   // 	products_list=mp.getMobilePriceData(2000,3000, dc,products_list);
		  //  	products_list=mp.getMobilePriceData(3000,4000, dc,products_list);
		   // 	products_list=mp.getMobilePriceData(4000,5000, dc,products_list);
		    	products_list=mp.getMobilePriceData(5000,10000, dc,products_list);
		    	products_list=mp.getMobilePriceData(10000,15000, dc,products_list);
		    	products_list=mp.getMobilePriceData(15000,20000, dc,products_list);
		    	products_list=mp.getMobilePriceData(20000,30000, dc,products_list);
		  //  	products_list=mp.getMobilePriceData(30000,40000, dc,products_list);
		    	
		    }
		    else
		    {
		    	
		    }
			
		}
		else
		{
		 products_list =getPriceData(query,dc);
		}
		
		return products_list;
	}
	Vector <StoreInfo> getData(String query,String product_query,String location,String city)
	{
		
		Vector <StoreInfo> stores_list=new Vector <StoreInfo>();
		XMLParsing xm = new XMLParsing();
		Dataclass dc=xm.parseXmlFile(Mainclass.XMLFILENAME, query);
		FetchData fd=new FetchData();
		stores_list=fd.getData(product_query,location,city, dc);
		return stores_list;
	}
	Vector <Productlist> getData(String query,Vector<Productlist> products_list)
	{
		XMLParsing xm = new XMLParsing();
		Dataclass dc=xm.parseXmlFile(Mainclass.XMLFILENAME, query);
		VeeozParsing vm = new VeeozParsing();
		
		
		int final_veeozvalue=0;
		int i=0;
		for(Productlist p : products_list )
		{
			
			int c= vm.getVeeozData(p.getProductid(),dc);
			if(c==-101)
			{
				int d = vm.getVeeozData(p.getBrandname(),dc);
				if(d==-101)
				{
					final_veeozvalue=Mainclass.Random_veeoz_product_value;
				}
				else
				{
					final_veeozvalue=d;
				}
			}
			else
			{
				final_veeozvalue=c;
			}
			p.setVeooz_value(final_veeozvalue);
			products_list.set(i, p);
		}
		return products_list;
		
	}
	public  Vector<LocationDetails> getData(String tour,String area,String city) 
	{
		TouringPlaces tp = new TouringPlaces();
		return tp.getData(tour,area,city);
	}
	Vector <Productlist> getPriceData(String query,Dataclass dc)
	{
		Document doc;
		Vector <Productlist> products_list = new Vector<Productlist>();
		try
		{
			doc = Jsoup.connect(dc.getUrl()).get();
			int size=dc.getData_list().size();
			DataHelper datahelper_array[]=new DataHelper[size];
			int i=0;
			for(DataHelper dh : dc.getData_list())
			{
				datahelper_array[i] =dh;
				i++;
			}
			Elements link1 = doc.select(datahelper_array[0].getTag_name());
			String temp_brand=null;
			for(Element lnk : link1)
			{
				Productlist pd=new Productlist();
				
				if(lnk.attr(datahelper_array[0].getAttr_name()).compareTo(datahelper_array[0].getAttr_val()) == 0)
				{
					temp_brand=lnk.text();
					//pd.setBrandname(lnk.text());
				}
				if(lnk.attr(datahelper_array[1].getAttr_name()).compareTo(datahelper_array[1].getAttr_val()) == 0)
				{
					String temp=lnk.text().substring(3,lnk.text().length());
					pd.setPrice(Integer.parseInt(temp));
					pd.setBrandname(temp_brand);
					products_list.add(pd);
				}
				
				
			}
			Elements link2 = doc.select(datahelper_array[2].getTag_name());
			 i=0;
			for(Element lnk : link2)
			{
			
				if(lnk.attr(datahelper_array[2].getAttr_name()).compareTo(datahelper_array[2].getAttr_val()) == 0)
				{
					
					Productlist pd =products_list.get(i);
					pd.setProductid(lnk.text());
					products_list.set(i, pd);
					i++;
				}
				
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products_list;
	}
}

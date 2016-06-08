import java.util.Vector;


public class Mainclass {

	public static String XMLFILENAME = "properties.xml";
	public static int Random_veeoz_product_value = 40;
	public static int Random_veeoz_location_value = 40;
	public static void main(String args[])
	{
		
		 PricesParsing ps =new PricesParsing();
		Vector <Productlist> products_list=null;
		Vector <Productlist> products_list1=null;
		Vector <StoreInfo> stores_list=null;
		Vector<LocationDetails> locationInfo=new Vector<LocationDetails>();
		
	/*	 TouringPlaces tp = new TouringPlaces();
		 locationInfo = tp.getData("tourist places","Dilsukhnagar");
			int i=0;
		
		while(i<locationInfo.size())
		{
			System.out.println("Place:"+locationInfo.get(i).getLocationName());
			System.out.println("Longi:"+locationInfo.get(i).getLongitude());
			System.out.println("Lati:"+locationInfo.get(i).getLatitude());
			System.out.println("weather Description:"+locationInfo.get(i).getWeatherDescription());
			System.out.println("min temperature:"+locationInfo.get(i).minTemperature);
			System.out.println("max temperature:"+locationInfo.get(i).maxTemperature);
			System.out.println("temperature :"+locationInfo.get(i).temperature);
			System.out.println("veeoz :"+locationInfo.get(i).veeoz_value);
			i++;
		}
	//	long e=System.currentTimeMillis(); */
		
	  
	   
		products_list=ps.getData("mobile phones");
		
		for(Productlist p : products_list)
		{
			System.out.println(p.getBrandname()+"  "+p.getProductid()+" "+p.getPrice()+" "+p.getVeooz_value());
		} 
		
		System.out.println(products_list.size());
	//	long l=System.currentTimeMillis();
	//	System.out.println(l-e);
		
	/*	products_list1=ps.getData("veeoz data",products_list);  
		
		for(Productlist p : products_list1)
		{
			System.out.println(p.getBrandname()+"  "+p.getProductid()+" "+p.getPrice()+" "+p.getVeooz_value());
		}
		
		System.out.println(products_list1.size()); */
		
		/*stores_list=ps.getData("store info","washing machine samsung","Dilsukhnagar","Hyderabad");
		
		for(StoreInfo s : stores_list)
		{
			System.out.println(s.getStoreName()+" "+s.getLocation()+"  "+s.getRatings());
		}
		System.out.println(stores_list.size()); */
		/*
		System.out.println(dc.getProduct_name());
		System.out.println(dc.getUrl());
		System.out.println(dc.isMultiple());
		Vector<DataHelper> vc = dc.getData_list();
		for (DataHelper dh : vc)
		{
			System.out.println(dh.getTag_name());
			System.out.println(dh.getAttr_val());
			System.out.println(dh.getAttr_name());
			
		}  
	//	Mobileprices mp = new Mobileprices();
	//	mp.getPriceData();*/
	}
}

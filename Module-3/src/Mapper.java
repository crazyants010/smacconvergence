import java.util.HashMap;


public class Mapper {

	public Object createObject(HashMap<String,String> map)
	{
		Productlist pl=new Productlist();
		pl.setBrandname(map.get("Brandname"));
		pl.setPrice(Integer.parseInt(map.get("price")));
		pl.setCutOff(Integer.parseInt(map.get("cutOff")));
		pl.setDescription(map.get("description"));
		pl.setEnd_price(Integer.parseInt(map.get("end_price")));
		pl.setMax_price(Integer.parseInt(map.get("max_price")));
		pl.setMin_price(Integer.parseInt(map.get("min_price")));
		pl.setProductid(map.get("productid"));
		pl.setReview(map.get("review"));
		pl.setStart_price(Integer.parseInt(map.get("start_price")));
		pl.setVeooz_value(Integer.parseInt(map.get("veooz_value")));
		return pl;
	}
}

//This file is a class that stores all the information that come from first developer.

public class PriceHelper {

	private int id;
	private int start_price;
	private int end_price;
	private int sentiment;
	private int time_series;

	private int rating;
	private String productName;
	private String action;
	private String loc;
	private String city;

	public PriceHelper() {

	}

	public PriceHelper(PriceHelper p) {
		this.setId(p.getId());
		this.setStart_price(p.getStart_price());
		this.setEnd_price(p.getEnd_price());
		this.setSentiment(p.getSentiment());
		this.setTime_series(p.getTime_series());
		this.setRating(p.getRating());
		this.setProductName(p.getProductName());
		this.setAction(p.getAction());
		this.setLoc(p.getLoc());
		this.setCity(p.getCity());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStart_price() {
		return start_price;
	}

	public void setStart_price(int start_price) {
		this.start_price = start_price;
	}

	public int getEnd_price() {
		return end_price;
	}

	public void setEnd_price(int end_price) {
		this.end_price = end_price;
	}

	public int getSentiment() {
		return sentiment;
	}

	public void setSentiment(int sentiment) {
		this.sentiment = sentiment;
	}

	public int getTime_series() {
		return time_series;
	}

	public void setTime_series(int time_series) {
		this.time_series = time_series;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}

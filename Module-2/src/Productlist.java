import java.io.Serializable;

// This class stores all the information bout product.

public class Productlist implements Serializable {

	private String Brandname;
	private String productid;
	private int price;
	private String description;
	private String review;
	private int veooz_value;

	public int start_price;
	public int end_price;
	public int cutOff;

	public Productlist() {
		start_price = 9000;
		end_price = 9500;
		cutOff = 0;
	}

	public int getVeooz_value() {
		return veooz_value;
	}

	public void setVeooz_value(int veooz_value) {
		this.veooz_value = veooz_value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getBrandname() {
		return Brandname;
	}

	public void setBrandname(String brandname) {
		Brandname = brandname;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public int getCutOff() {
		return cutOff;
	}

	public void setCutOff(int cutOff) {
		this.cutOff = cutOff;
	}
}

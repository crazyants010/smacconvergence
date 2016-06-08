import java.io.Serializable;

/*
 * StoreInfo class stores all the information regarding a store that we bring.
 * Cutoff stores the cutoff value that is necessary for a particular shop.
 */
public class StoreInfo implements Serializable {
	private String storeName;
	private String location;
	private int ratings;
	private int cutOff;

	public StoreInfo() {
		cutOff = 3;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getRatings() {
		return ratings;
	}

	public int getCutOff() {
		return cutOff;
	}

	public void setCutOff(int cutOff) {
		this.cutOff = cutOff;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
}

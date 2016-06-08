import java.util.*;

public class Dataclass {
	private String product_name;
	private String url;
	private boolean multiple;
	private Vector<DataHelper> data_list;

	String getProduct_name() {
		return product_name;
	}

	void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	String getUrl() {
		return url;
	}

	void setUrl(String url) {
		this.url = url;
	}

	boolean isMultiple() {
		return multiple;
	}

	void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	Vector<DataHelper> getData_list() {
		return data_list;
	}

	void setData_list(Vector<DataHelper> data_list) {
		this.data_list = data_list;
	}
}

public class DataHelper {
	private String tag_name;
	private String attr_name;
	private String attr_val;

	DataHelper() {

	}

	DataHelper(DataHelper dh) {
		this.tag_name = dh.getTag_name();
		this.attr_val = dh.getAttr_val();
		this.attr_name = dh.getAttr_name();
	}

	String getTag_name() {
		return tag_name;
	}

	void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	String getAttr_name() {
		return attr_name;
	}

	void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}

	String getAttr_val() {
		return attr_val;
	}

	void setAttr_val(String attr_val) {
		this.attr_val = attr_val;
	}
}

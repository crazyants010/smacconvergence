
import java.io.Serializable;
public class Modul12communicator implements Serializable

{

	private boolean xml;
	private int request_id;
	private String str;
	private int developer_id;
	int getDeveloper_id() {
		return developer_id;
	}
	void setDeveloper_id(int developer_id) {
		this.developer_id = developer_id;
	}
	boolean isXml() {
		return xml;
	}
	void setXml(boolean xml) {
		this.xml = xml;
	}
	int getRequest_id() {
		return request_id;
	}
	void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	String getStr() {
		return str;
	}
	void setStr(String str) {
		this.str = str;
	}
	
	
}

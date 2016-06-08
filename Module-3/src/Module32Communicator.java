import java.io.Serializable;
import java.util.Vector;


public class Module32Communicator implements Serializable
{
	private int request_id;
	private int type;
	private Vector<String> function_call;
	private Vector<Productlist> veeoz_call;
	
	public Module32Communicator()
	{
		function_call=new Vector<String>();
		veeoz_call=new Vector<Productlist>();
	}
	
	int getType() {
		return type;
	}
	void setType(int type) {
		this.type = type;
	}
	Vector<Productlist> getVeeoz_call() {
		return veeoz_call;
	}
	void setVeeoz_call(Vector<Productlist> veeoz_call) {
		this.veeoz_call = veeoz_call;
	}
	int getRequest_id() {
		return request_id;
	}
	void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	Vector<String> getFunction_call() {
		return function_call;
	}
	void setFunction_call(Vector<String> function_call) {
		this.function_call = function_call;
	}
}

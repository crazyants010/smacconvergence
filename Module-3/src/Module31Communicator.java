import java.io.Serializable;

public class Module31Communicator implements Serializable {
	private int request_id;
	private String final_ans;
	private int type; // type=2 mail type=1 post
	private String emailid;

	int getRequest_id() {
		return request_id;
	}

	void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	String getFinal_ans() {
		return final_ans;
	}

	void setFinal_ans(String final_ans) {
		this.final_ans = final_ans;
	}

	int getType() {
		return type;
	}

	void setType(int type) {
		this.type = type;
	}

	String getEmailid() {
		return emailid;
	}

	void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public void parse(String action) {
		if (action.compareToIgnoreCase("post") == 0) {
			this.type = 1;
		} else {
			this.type = 2;
			int index = action.indexOf(":");
			this.emailid = action.substring(index + 1, action.length());
		}
	}
}

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.drools.runtime.StatefulKnowledgeSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class Process implements Runnable {
	private int reqid;
	private String loc;
	private int dev_id;
	TouristDeveloper td;
	PriceDeveloper pd;
	AnalyticsInitializer init;

	public Process() {
		init = new AnalyticsInitializer();
	}

	int getReqid() {
		return reqid;
	}

	void setReqid(int reqid) {
		this.reqid = reqid;
	}

	String getLoc() {
		return loc;
	}

	void setLoc(String loc) {
		this.loc = loc;
	}

	int getDev_id() {
		return dev_id;
	}

	void setDev_id(int dev_id) {
		this.dev_id = dev_id;
	}

	void executeRules(Productlist p) {
		StatefulKnowledgeSession ks = init.initializer(dev_id);
		p.setMin_price(pd.getMin());
		p.setMax_price(pd.getMax());
		p.setCutOff(pd.getSentient());
	}

	void execute(PostMethod postMethod, String retclass) {
		HttpClient client = new HttpClient();
		try {
			client.executeMethod(postMethod);
			String json = postMethod.getResponseBodyAsString();
			// postMethod.g
			System.out.println(json);
			try {
				// json.trim();
				// json.trim();
				// json = json.substring(json.indexOf("["), json.indexOf("]") +
				// 1);
				// System.out.println(json);
				JSONArray jObject = new JSONArray(json);
				for (int i = 0; i < jObject.length(); i++) {
					JSONObject obj = new JSONObject(jObject.get(i));
					JSONObject productlist = obj.getJSONObject(retclass);
					Iterator iter = productlist.keys();
					Productlist p = new Productlist();
					HashMap<String, String> map = new HashMap<String, String>();
					while (iter.hasNext()) {
						String key = (String) iter.next();
						String value = productlist.getString(key);
						System.out.println(key + " " + value);
						map.put(key.toLowerCase(), value.toLowerCase());
					}
					Mapper mp = new Mapper();
					Productlist pr = (Productlist) mp.createObject(map);
					System.out.println(pr.getMax_price() + " "
							+ pr.getMin_price() + " " + pr.getPrice());

				}

			} catch (JSONException e) {

				e.printStackTrace();
			}

		} catch (HttpException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	void parser() {
		FileGenerator fg = new FileGenerator();
		String s = fg.generateFile(loc, reqid, dev_id);
		System.out.println(s + "\n" + loc);
		td = new TouristDeveloper();
		pd = new PriceDeveloper();
		// td.parse(s);
		pd.parse(s);
		System.out.println("PARSER..");
		File f = new File("config/developer_" + dev_id
				+ "/Init_files/init_func_call");
		try {
			RandomAccessFile rf = new RandomAccessFile(f, "r");
			String line = rf.readLine();
			Differentiator d = new Differentiator();
			PostMethod postMethod = d.findType(line, td, pd);
			execute(postMethod, d.param4);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		parser();
	}

}
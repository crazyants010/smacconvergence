import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FetchData {
	public Vector<StoreInfo> getStoreInfo(Vector<String> urls, Dataclass dc) {
		Vector<StoreInfo> storesInfo = new Vector<StoreInfo>();

		int size = dc.getData_list().size();
		DataHelper datahelper_array[] = new DataHelper[size];
		int k = 0;
		for (DataHelper dh : dc.getData_list()) {
			datahelper_array[k] = dh;
			k++;
		}
		int i = 0;

		for (String url : urls) {

			try {
				System.getProperties()
						.put("http.proxyHost", "proxy.iiit.ac.in");
				System.getProperties().put("http.proxyPort", "8080");

				Document doc = Jsoup.connect(url).timeout(100 * 1000).get();
				Elements stores = doc.select(datahelper_array[1].getTag_name());
				Elements rankInfo = doc.select(datahelper_array[2]
						.getTag_name());
				Elements loc = doc.select(datahelper_array[3].getTag_name());

				for (Element element : stores) {
					if (element.attr(datahelper_array[1].getAttr_name())
							.compareTo(datahelper_array[1].getAttr_val()) == 0) {
						StoreInfo sInfo = new StoreInfo();
						sInfo.setStoreName(element.text());
						storesInfo.add(sInfo);
						// System.out.println(sInfo.getStoreName());
					}
				}

				int j = i;

				for (Element element : rankInfo) {
					if (element.attr(datahelper_array[2].getAttr_name())
							.compareTo(datahelper_array[2].getAttr_val()) == 0) {
						StoreInfo sinfo = new StoreInfo();
						String s = element.attr("style");
						sinfo = storesInfo.get(i);
						int rating = Integer.parseInt(s.substring(
								s.indexOf(':') + 1, s.indexOf('%'))) / 20;
						if (rating == 0) {
							Random randomGenerator = new Random();
							rating = randomGenerator.nextInt(4) + 1;

						}
						sinfo.setRatings(rating);
						storesInfo.setElementAt(sinfo, i);
						// System.out.println(sinfo.getStoreName());
						i++;
					}

				}

				i = j;

				for (Element element : loc) {
					if (element.attr(datahelper_array[3].getAttr_name())
							.compareTo(datahelper_array[3].getAttr_val()) == 0) {
						StoreInfo sinf = new StoreInfo();
						sinf = storesInfo.get(i);
						sinf.setLocation(element.text());
						storesInfo.setElementAt(sinf, i);
						i++;
					}
				}

			}

			catch (IOException e) {
				e.printStackTrace();
			}

		}

		return storesInfo;
	}

	public Vector<String> getURLList(String url, Dataclass dc) {
		Vector<String> v = new Vector<String>();

		try {
			System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
			System.getProperties().put("http.proxyPort", "8080");
			int size = dc.getData_list().size();
			DataHelper datahelper_array[] = new DataHelper[size];
			int k = 0;
			for (DataHelper dh : dc.getData_list()) {
				datahelper_array[k] = dh;
				k++;
			}
			// url="http://www.asklaila.com/search/Hyderabad/Dilsukhnagar/washing%20machine%20samsung/?searchNearby=true";
			// url="http://www.asklaila.com/search/Hyderabad/"+place+"/"+brandName+"%20"+productName+"/?searchNearby=true";
			Document doc = Jsoup.connect(url).get();

			Elements urls = doc.select(datahelper_array[0].getTag_name());

			for (Element element : urls) {
				if (element.attr(datahelper_array[0].getAttr_name()).compareTo(
						datahelper_array[0].getAttr_val()) == 0) {
					Elements e = element.select("a[href]");
					v.add(e.attr("href"));
				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return v;

	}

	public Vector<StoreInfo> getData(String query, String area, String place,
			Dataclass dc) {
		String brandName, productName, url;
		query = query.replaceAll(" ", "+");
		url = dc.getUrl();

		Vector<StoreInfo> storesInfo = new Vector<StoreInfo>();
		Vector<String> urlList = new Vector<String>();
		url += place + "/" + area + "/" + query + "/?searchNearby=true";

		// http://www.asklaila.com/search/Hyderabad/Dilsukhnagar/washingmachine%20samsung/?searchNearby=true
		System.out.println(url);
		urlList = getURLList(url, dc);
		urlList.add(0, url);

		storesInfo = getStoreInfo(urlList, dc);
		return storesInfo;

	}

}
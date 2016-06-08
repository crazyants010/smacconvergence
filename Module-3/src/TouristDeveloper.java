import java.util.Vector;

import org.drools.runtime.StatefulKnowledgeSession;

// This class has flow of operations for the developer id-2.

public class TouristDeveloper {

	GenerateTouristRules g;
	TouristInfo t;

	public TouristDeveloper() {
		g = new GenerateTouristRules();
		t = new TouristInfo();
	}

	public void parse(String fName) {
		this.t = g.getObject(fName);
	}

	public String getLoc() {
		return this.t.getLoc();
	}

	public String getCity() {
		return this.t.getCity();
	}
	
	public String getAction() {
		return this.t.getAction();
	}

	public Vector<LocationDetails> applyTouristRules(
			Vector<LocationDetails> locationInfo) {
		System.out.println(t.getHumidity()+" is humidity "+t.getMaxTemp());
		for (LocationDetails l : locationInfo) {
			l.setCutoff_max_temp(t.getMinTemp());
			l.setCutoff_max_temp(t.getMaxTemp());
			l.setCutoff_sentiment(t.getSentiment());
			l.setCutOff_humid(t.getHumidity());
		}

		AnalyticsInitializer a = new AnalyticsInitializer();
		StatefulKnowledgeSession ks = a.initializer(2);
		locationInfo = a.fireTouristRules(ks, "temperature", locationInfo);
		System.out.println(locationInfo.size());
		locationInfo = a.fireTouristRules(ks, "veooz", locationInfo);
		System.out.println(locationInfo.size());
		locationInfo = a.fireTouristRules(ks, "humidity", locationInfo);
		System.out.println(locationInfo.size());

		return locationInfo;
	}
}

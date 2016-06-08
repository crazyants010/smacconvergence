import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// This class will parse the xml document of developer-2 and stores necessary
// information from that file.
public class GenerateTouristRules extends DefaultHandler {
	String final_temp;
	boolean isId;
	boolean isLoc;
	boolean isCity;
	boolean isRadius;
	boolean isMin;
	boolean isMax;
	boolean isVeooz;
	boolean isTime;
	boolean isAction;
	boolean isHumidity;
	TouristInfo t;

	public GenerateTouristRules() {
		isId = false;
		isLoc = false;
		isCity = false;
		isRadius = false;
		isMin = false;
		isMax = false;
		isVeooz = false;
		isTime = false;
		isAction = false;
		isHumidity = false;
		final_temp = new String();
		t = new TouristInfo();
	}

	public void startDocument() throws SAXException {
	}

	public void endDocument() throws SAXException {
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attrs) throws SAXException {

		if (qName.equalsIgnoreCase("UserId")) {
			isId = true;
		}
		if (qName.equalsIgnoreCase("location")) {
			isLoc = true;
		}
		if (qName.equalsIgnoreCase("city")) {
			isCity = true;
		}
		if (qName.equalsIgnoreCase("Radius")) {
			isRadius = true;
		}
		if (qName.equalsIgnoreCase("min")) {
			isMin = true;
		}
		if (qName.equalsIgnoreCase("max")) {
			isMax = true;
		}
		if (qName.equalsIgnoreCase("veooz")) {
			isVeooz = true;
		}
		if (qName.equalsIgnoreCase("action")) {
			isAction = true;
		}
		if (qName.equalsIgnoreCase("time")) {
			isTime = true;
		}
		if (qName.equalsIgnoreCase("humidity")) {
			isHumidity = true;
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equalsIgnoreCase("UserId")) {
			t.setId(Integer.parseInt(final_temp));
			final_temp = new String();
			// System.out.println(t.getId());
			isId = false;
		}

		if (qName.equalsIgnoreCase("location")) {
			t.setLoc(final_temp);
			final_temp = new String();
			// System.out.println(t.getLoc());
			isLoc = false;
		}

		if (qName.equalsIgnoreCase("city")) {
			t.setCity(final_temp);
			final_temp = new String();
			// System.out.println(t.getCity());
			isCity = false;
		}
		if (qName.equalsIgnoreCase("radius")) {
			t.setRadius(Integer.parseInt(final_temp));
			final_temp = new String();
			// System.out.println(t.getRadius());
			isRadius = false;
		}
		if (qName.equalsIgnoreCase("min")) {
			t.setMinTemp(Integer.parseInt(final_temp));
			final_temp = new String();
			// System.out.println(t.getMinTemp());
			isMin = false;
		}
		if (qName.equalsIgnoreCase("max")) {
			t.setMaxTemp(Integer.parseInt(final_temp));
			final_temp = new String();
			// System.out.println(t.getMaxTemp());
			isMax = false;
		}

		if (qName.equalsIgnoreCase("veooz")) {
			t.setSentiment(Integer.parseInt(final_temp));
			final_temp = new String();
			// System.out.println(t.getSentiment());
			isVeooz = false;
		}
		if (qName.equalsIgnoreCase("action")) {
			t.setAction(final_temp);
			final_temp = new String();
			// System.out.println(t.getAction());
			isAction = false;
		}
		if (qName.equalsIgnoreCase("time")) {
			t.setTime(Integer.parseInt(final_temp));
			final_temp = new String();
			// System.out.println(t.getTime());
			isTime = false;
		}

		if (qName.equalsIgnoreCase("humidity")) {
			t.setHumidity(Integer.parseInt(final_temp));
			final_temp = new String();
			// System.out.println(t.getHumidity());
			isHumidity = false;
		}
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (isId) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}

		if (isLoc) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}

		if (isCity) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
		if (isRadius) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
		if (isMin) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
		if (isMax) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
		if (isVeooz) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
		if (isAction) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
		if (isTime) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
		if (isHumidity) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
	}

	// Returns a TouristInfo object which is used in populating Tourist class
	// for application of rules.
	public TouristInfo getObject(String fName) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			File file = new File(fName);
			parser.parse(file, this);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		return t;
	}
}

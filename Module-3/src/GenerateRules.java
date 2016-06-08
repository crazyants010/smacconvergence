import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GenerateRules extends DefaultHandler {
	String final_temp;
	boolean isId;
	boolean isLoc;
	boolean isCity;
	boolean isPName;
	boolean isStrt;
	boolean isEnd;
	boolean isRating;
	boolean isVeooz;
	boolean isTime;
	boolean isAction;
	PriceHelper p;

	public GenerateRules() {
		isId = false;
		isLoc = false;
		isCity = false;
		isPName = false;
		isStrt = false;
		isEnd = false;
		isRating = false;
		isVeooz = false;
		isTime = false;
		isAction = false;
		final_temp = new String();
		p = new PriceHelper();
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
		if (qName.equalsIgnoreCase("ProductName")) {
			isPName = true;
		}
		if (qName.equalsIgnoreCase("start")) {
			isStrt = true;
		}
		if (qName.equalsIgnoreCase("end")) {
			isEnd = true;
		}
		if (qName.equalsIgnoreCase("ShopRating")) {
			isRating = true;
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
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equalsIgnoreCase("UserId")) {
			p.setId(Integer.parseInt(final_temp));
			final_temp = new String();
			System.out.println(p.getId());
			isId = false;
		}

		if (qName.equalsIgnoreCase("location")) {
			p.setLoc(final_temp);
			final_temp = new String();
			System.out.println(p.getLoc());
			isLoc = false;
		}

		if (qName.equalsIgnoreCase("city")) {
			p.setCity(final_temp);
			final_temp = new String();
			System.out.println(p.getCity());
			isCity = false;
		}
		if (qName.equalsIgnoreCase("ProductName")) {
			p.setProductName(final_temp);
			final_temp = new String();
			System.out.println(p.getProductName());
			isPName = false;
		}
		if (qName.equalsIgnoreCase("start")) {
			p.setStart_price(Integer.parseInt(final_temp));
			final_temp = new String();
			System.out.println(p.getStart_price());
			isStrt = false;
		}
		if (qName.equalsIgnoreCase("end")) {
			p.setEnd_price(Integer.parseInt(final_temp));
			final_temp = new String();
			System.out.println(p.getEnd_price());
			isEnd = false;
		}
		if (qName.equalsIgnoreCase("ShopRating")) {
			p.setRating(Integer.parseInt(final_temp));
			final_temp = new String();
			System.out.println(p.getRating());
			isRating = false;
		}

		if (qName.equalsIgnoreCase("veooz")) {
			p.setSentiment(Integer.parseInt(final_temp));
			final_temp = new String();
			System.out.println(p.getSentiment());
			isVeooz = false;
		}
		if (qName.equalsIgnoreCase("action")) {
			p.setAction(final_temp);
			final_temp = new String();
			System.out.println(p.getAction());
			isAction = false;
		}
		if (qName.equalsIgnoreCase("time")) {
			// System.out.println(final_temp);
			p.setTime_series(Integer.parseInt(final_temp));
			final_temp = new String();
			System.out.println(p.getTime_series());
			isTime = false;
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
		if (isPName) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
		if (isStrt) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
		if (isEnd) {
			String temp = new String(ch, start, length);
			final_temp = final_temp + temp;
		}
		if (isRating) {
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
	}

	public PriceHelper getObject(String fName) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			File file = new File(fName);
			parser.parse(file, this);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		return p;
	}
}

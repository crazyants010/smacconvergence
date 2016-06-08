import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParsing  extends DefaultHandler{
	
	 Dataclass dc =new Dataclass();
	 DataHelper dh=new DataHelper();
	 String query;
	 Vector<DataHelper> vec = new  Vector<DataHelper>();
		boolean p_name = false;
		boolean url = false;
		boolean check = false;
		boolean checks = false;
		boolean tag = false;
		boolean attribute = false;
		boolean attribute_value = false;
		boolean match=false;
	Dataclass parseXmlFile(String filename,String query)
	{
		this.query=query;
		try
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
		    saxParser.parse(filename, this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dc;
	}

	public void startElement(String uri, String localName,String qName, 
	        Attributes attributes) throws SAXException {
	
	
	
	if (qName.equalsIgnoreCase("Product-name")) {
		p_name = true;
	}
	
				if (qName.equalsIgnoreCase("url")) {
					if(match)
						{
						url = true;
						String multiple=attributes.getValue("multiple");
						if(multiple.compareToIgnoreCase("yes")==0)
						{
							dc.setMultiple(true);
						}
						else 
							dc.setMultiple(false);
						}
				}
				
				if (qName.equalsIgnoreCase("check")) {
					if(match)check = true;
				}
				if (qName.equalsIgnoreCase("checks")) {
					if(match)checks = true;
				}
				if (qName.equalsIgnoreCase("tag")) {
					if(match)tag = true;
				}
				if (qName.equalsIgnoreCase("attribute")) {
					if(match)attribute = true;
				}
				if (qName.equalsIgnoreCase("attribute-value")) {
					if(match) attribute_value = true;
				}
		
	}
	public void characters(char ch[], int start, int length) throws SAXException {
		 
		if (p_name) {
			String s =new String(ch,start,length);
			if(s.compareToIgnoreCase(query)==0)
			{
			match=true;
			dc.setProduct_name(s);
			}
			else
			{
				match=false;
			}
			p_name = false;
			
		}
 
		if ( url) {
			dc.setUrl(new String(ch,start,length));
			url = false;
		}
 
		if (check) {
			
		        if(!tag && !attribute && !attribute_value)
		        {

		          
		         /*  System.out.println(dh.getTag_name());
		           System.out.println(dh.getAttr_name());
		           System.out.println(dh.getAttr_val()); */
		           
		           check=false;
		        }
			
			
		}
		if(tag) 
			{
			dh.setTag_name(new String(ch,start,length)); 
			
		//	.out.println("tag updated");
			tag=false;
			}
		if(attribute) 
		{
			dh.setAttr_name(new String(ch,start,length));
			
			attribute=false;
		}
		if(attribute_value)
			{
			dh.setAttr_val(new String(ch,start,length));
			
			attribute_value=false;
			}
      if(checks)
      {
    	  dc.setData_list(vec);
    	  checks =false;
      }
		
 
	}
 
public void endElement(String uri, String localName,
		String qName) throws SAXException {
 
	//	.out.println("End Element :" + qName);
	if (qName.equalsIgnoreCase("check")) {
		
		if(match)
		{
			DataHelper dh1=new DataHelper(dh);
			vec.add(dh1);
		}
	}
 
	}
				
}

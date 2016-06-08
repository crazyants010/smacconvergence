import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XML_Validation {

    /**
     * @param args
     */
    public int xmlStatus(String fileName, int val) throws Exception {
        // TODO Auto-generated method stub

        if(validateAgainstXSD(new File(fileName), val))
        	return 1 ;
        else
        {	
        	System.out.println("Error: Invalid XML Format by User for file = "+ fileName);
        	return 0 ;
        }
    }

    public boolean validateAgainstXSD(File xml, int val)
    {
        try
        {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = null;
			if(val == 1)
            	schema = factory.newSchema(new StreamSource("./Valid XSD/Tourist.xsd"));
            else
            	schema = factory.newSchema(new StreamSource("./Valid XSD/Product.xsd"));
            

            Validator validator  = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        }
        catch(Exception exe)
        {
        	return false;
        }
    }
}
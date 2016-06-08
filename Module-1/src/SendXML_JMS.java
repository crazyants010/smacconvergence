

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import javax.jms.*;
import javax.naming.InitialContext;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.QueueSender;
import javax.jms.DeliveryMode;
import javax.jms.QueueSession;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.util.ByteSequence;

public class SendXML_JMS 
{	 
	private static final String ACTIVEMQ_SERVER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;//"failover://tcp://localhost:61616";
	
	public void callMain(String fileData) throws IOException, JMSException
	{		
		try
		{
			javax.jms.Connection jms_connection;
			ActiveMQSession jms_session;
			MessageProducer csb;
		
			javax.jms.ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_SERVER_URL);
			jms_connection = connectionFactory.createConnection();
			jms_connection.start();
			jms_session = (ActiveMQSession) jms_connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Destination module2_destination = jms_session.createQueue("MODULE12_QUEUE");
			csb = jms_session.createProducer(module2_destination);    
			
			TextMessage txtMessage = jms_session.createTextMessage(fileData);			
			csb.send(txtMessage);
			System.out.println("@SendXML_JMS: XML sent to CSB");
			csb.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}						
	}
}

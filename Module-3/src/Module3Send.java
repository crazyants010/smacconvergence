import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class Module3Send {
	 private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;	//which is localhost:61616 change it later to server address
	 
	    private static String subject = "MODULESEND31_QUEUE";
	    private static String subject1 = "MODULESEND32_QUEUE";
	    public static void sendto1(Module31Communicator mdc) throws JMSException {
	       
	        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	        Connection connection = connectionFactory.createConnection();
	        connection.start();
	        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
	        Destination destination = session.createQueue(subject); //MODULE3_QUEUE
	        MessageProducer producer = session.createProducer(destination);
	        ObjectMessage obMsg=session.createObjectMessage();
	        obMsg.setObject(mdc);
	        producer.send(obMsg);
	       
	      
	        connection.close();
	    }
	    public static void sendto2(Module32Communicator mdc) throws JMSException {
		       
	        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	        Connection connection = connectionFactory.createConnection();
	        connection.start();
	        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
	        Destination destination = session.createQueue(subject1); //MODULE3_QUEUE
	        MessageProducer producer = session.createProducer(destination);
	        ObjectMessage obMsg=session.createObjectMessage();
	 //       TextMessage message = session.createTextMessage(str);
	        obMsg.setObject(mdc);
	        producer.send(obMsg);
	    
	        connection.close();
	    }
}

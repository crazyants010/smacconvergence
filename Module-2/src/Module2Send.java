import org.apache.activemq.ActiveMQConnection;
import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Module2Send {
	 private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;	//which is localhost:61616 change it later to server address
	 
	    private static String subject = "MODULESEND23_QUEUE";
	    public static void send(Module23communicator md) throws JMSException {
	       
	        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	        Connection connection = connectionFactory.createConnection();
	        connection.start();
	        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
	        Destination destination = session.createQueue(subject); //MODULE3_QUEUE
	        MessageProducer producer = session.createProducer(destination);
	        ObjectMessage obMsg=session.createObjectMessage();
	 //       TextMessage message = session.createTextMessage(str);
	        obMsg.setObject(md);
	        producer.send(obMsg);
	        System.out.println("Sent message '" );
	        connection.close();
	    }
	    
	    
}

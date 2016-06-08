
//import java.io.Serializable;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class JmsDispatcherSend {
    // URL of the JMS server. DEFAULT_BROKER_URL will just mean
    // that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;	//which is localhost:61616 change it later to server address

    // Name of the queue we will be sending messages to
    private static String subject = "MODULESEND12_QUEUE";

    public static void send(Modul12communicator md) throws JMSException {
        // Getting JMS connection from the server and starting it
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // JMS messages are sent and received using a Session. We will
        // create here a non-transactional session object. If you want
        // to use transactions you should set the first parameter to 'true'
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        // Destination represents here our queue 'TESTQUEUE' on the
        // JMS server. You don't have to do anything special on the
        // server to create it, it will be created automatically.
        Destination destination = session.createQueue(subject); //TESTQUEUE

        // MessageProducer is used for sending messages (as opposed
        // to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);

        // We will send a small text message saying 'Hello' in Japanese
       // TextMessage message = session.createTextMessage(str);

        ObjectMessage obMsg=session.createObjectMessage();
        
        
        // Here we are sending the message!
       // producer.send(message);
        obMsg.setObject(md);
        producer.send(obMsg);
        System.out.println("Sent message '" );
        
    

		// Here we receive the message.
		// By default this call is blocking, which means it will wait
		// for a message to arrive on the queue.
//		Message message = consumer.receive();
//		consumer.receive();
		
        connection.close();
    }
    
}

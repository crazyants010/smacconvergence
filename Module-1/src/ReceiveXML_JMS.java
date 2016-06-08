
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.util.ByteSequence;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReceiveXML_JMS 
{
	private static final String ACTIVEMQ_SERVER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;//"failover://tcp://localhost:61616";	
	
	public void main(String[] args) throws IOException, JMSException
	{
		ConnectionFactory connectionFactory = null;
        Connection connection = null;               
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;
        TextMessage message = null;
        
        connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_SERVER_URL);
        connection = connectionFactory.createConnection();
        connection.start(); 
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);                  
        destination = session.createQueue("MODULE31_QUEUE");        
        consumer = session.createConsumer(destination);
        
        
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				try 
				{
					if (message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						//System.out.println("Received message:'" + textMessage.getText() + "'");
						
						String outputFilePath = "./User_Output_Files/";
				        Calendar cal = Calendar.getInstance();
				    	cal.getTime();
				    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				    	String ts=sdf.format(cal.getTime()) ;
				    	String fileName = outputFilePath+"output.txt_"+ts;
				        
				        
						FileWriter fwo = new FileWriter( fileName, false );
						BufferedWriter bwObj = new BufferedWriter( fwo );
						bwObj.write(textMessage.getText());
						bwObj.flush();
						bwObj.close();
					}
//					if (message instanceof ObjectMessage) {
//						ObjectMessage objectMessage = (ObjectMessage) message;
//					//	Place pl=(Place)objectMessage.getObject();
//						Try t =(Try)objectMessage.getObject();
//						System.out.println("Received message ");
//						System.out.println(t.c);
//						
//					}
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		);
		
	}
}

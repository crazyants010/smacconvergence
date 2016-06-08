
import java.util.Scanner;
import java.util.Vector;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class JmsDispatcherReceive {
	
	
	// URL of the JMS server
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;


	// Name of the queue we will receive messages from
	private static String subject = "MODULESEND12_QUEUE";
	private static String subject_new = "MODULESEND32_QUEUE";
	public static void main(String[] args) throws JMSException {
		// Getting JMS connection from the server
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Creating session for seding messages
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

		// Getting the queue 'TESTQUEUE'
		Destination destination = session.createQueue(subject);

		// MessageConsumer is used for receiving (consuming) messages
		MessageConsumer consumer = session.createConsumer(destination);

		// Here we receive the message.
		// By default this call is blocking, which means it will wait
		// for a message to arrive on the queue.
//		Message message = consumer.receive();
//		consumer.receive();
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				try {
					if (message instanceof TextMessage) {
//						TextMessage textMessage = (TextMessage) message;
//						System.out.println("Received message '" + textMessage.getText() + "'");
//						Module2Send.send("sedning a message from 2 to 3");
					}
						if (message instanceof ObjectMessage) {
						ObjectMessage objectMessage = (ObjectMessage) message;
						Modul12communicator md=(Modul12communicator)objectMessage.getObject();
						Module23communicator md23= new Module23communicator();
						md23.setRequest_id(md.getRequest_id());
						md23.setDeveloper_id(md.getDeveloper_id());
						if(md.isXml())
						md23.setType(1);
						else
							md23.setType(2);
						md23.setStr(md.getStr());
						System.out.println(md23.getStr());
						Module2Send.send(md23);
						System.out.println("message received and sent to module3");
					}
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		ConnectionFactory connectionFactory_new = new ActiveMQConnectionFactory(url);
		Connection connection_new = connectionFactory_new.createConnection();
		connection_new.start();
		Session session_new = connection_new.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Destination destination_new = session_new.createQueue(subject_new);
		MessageConsumer consumer_new = session.createConsumer(destination_new);
		consumer_new.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				try {
					if (message instanceof TextMessage) {
//						TextMessage textMessage = (TextMessage) message;
//						System.out.println("Received message '" + textMessage.getText() + "'");
//						Module2Send.send("sending a message from 2 to 3");
					}
						if (message instanceof ObjectMessage) {
						ObjectMessage objectMessage = (ObjectMessage) message;
						Module32Communicator md=(Module32Communicator)objectMessage.getObject();
						System.out.println("message received from module 3 to ask about data");
						PricesParsing ps = new PricesParsing();
						switch(md.getType())
						{
						case 2:  
										Vector<Productlist> vec = ps.getData(md.getFunction_call().get(0));
										Module23communicator mdc=new Module23communicator();
										mdc.setRequest_id(md.getRequest_id());
										mdc.setType(3);
										mdc.setProduct_data(vec);
									//	mdc.setStr("rajasthan won");
										System.out.println("collected data of size "+vec.size()+" to send");
										Module2Send.send(mdc);
										System.out.println("Sent final answer to module3");
										break;
						
						case 3:		  Vector<Productlist> vec_veeoz = ps.getData("veooz data",md.getVeeoz_call());
									   Module23communicator mdc_veeoz=new Module23communicator();
									   mdc_veeoz.setRequest_id(md.getRequest_id());
									   mdc_veeoz.setType(4);
									   mdc_veeoz.setProduct_data(vec_veeoz);
									   Module2Send.send(mdc_veeoz);
									   break;
									   
									   
						case 4:   		Vector<StoreInfo> vec_store = ps.getData("store info",md.getFunction_call().get(0),md.getFunction_call().get(1),md.getFunction_call().get(2));
										Module23communicator mdc_store=new Module23communicator();
										mdc_store.setRequest_id(md.getRequest_id());
										mdc_store.setType(5);
										mdc_store.setStore_data(vec_store);
										Module2Send.send(mdc_store);
										break;
										
						case 5: 		Vector<LocationDetails> vec_location = ps.getData("tourist places",md.getFunction_call().get(0), md.getFunction_call().get(1));
										Module23communicator mdc_tour=new Module23communicator();
										mdc_tour.setRequest_id(md.getRequest_id());
										mdc_tour.setType(5);
										mdc_tour.setLocation_data(vec_location);
										Module2Send.send(mdc_tour);
										break;
						}
							 
						System.out.println("message received and sent to module3");
					}
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
	}
}

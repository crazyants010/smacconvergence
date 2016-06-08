import java.util.Scanner;
import java.util.Vector;

import org.apache.activemq.ActiveMQConnection;
import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Module3Reciver {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	// Name of the queue we will receive messages from
	private static String subject = "MODULESEND23_QUEUE";

	public static void main(String[] args) throws JMSException {
		System.out.println(url);
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(subject);
		MessageConsumer consumer = session.createConsumer(destination);
		Vector<Productlist> vec_price_prod_final = new Vector<Productlist>();
		consumer.setMessageListener(new MessageListener() {
			PriceDeveloper pd = null;
			TouristDeveloper td = null;
			int cnt = 0;

			@Override
			public void onMessage(Message message) {
				try {

					if (message instanceof ObjectMessage) {
						ObjectMessage objectMessage = (ObjectMessage) message;
						// Place pl=(Place)objectMessage.getObject();
						Module23communicator md = (Module23communicator) objectMessage
								.getObject();
						if (md.getType() == 1 || md.getType() == 2) {

							Process pr = new Process();
							pr.setLoc(md.getStr());
							pr.setReqid(md.getRequest_id());
							pr.setDev_id(md.getDeveloper_id());
							Thread th = new Thread(pr);
							th.setName(md.getRequest_id() + "");
							th.start();
							// else if (md.getDeveloper_id() == 1) {
							// System.out
							// .println("Modue 3 asking data to modulee2");
							// td = new TouristDeveloper();
							// td.parse(s);
							// fg.deleteFile(s);
							// Module32Communicator mdc = new
							// Module32Communicator();
							// mdc.setRequest_id(md.getRequest_id());
							// mdc.setType(5);
							// Vector<String> vec_query = new Vector<String>();
							// vec_query.add(td.getLoc());
							// vec_query.add(td.getCity());
							// mdc.setFunction_call(vec_query);
							// Module3Send.sendto2(mdc);
							// }

						}

						// else if (md.getType() == 3) {
						// Vector<Productlist> vec_price_prod = md
						// .getProduct_data();
						//
						// vec_price_prod = new Vector<Productlist>(pd
						// .applyPriceRules(vec_price_prod));
						// Vector<String> vec_query = new Vector<String>();
						// Module32Communicator mdc = new
						// Module32Communicator();
						// mdc.setRequest_id(md.getRequest_id());
						// mdc.setType(3);
						// mdc.setVeeoz_call(vec_price_prod);
						// mdc.setFunction_call(vec_query);
						// Module3Send.sendto2(mdc);
						//
						//
						//
						// } else if (md.getType() == 4) {
						// Vector<Productlist> vec_price_prod = md
						// .getProduct_data();
						//
						// vec_price_prod = new Vector<Productlist>(pd
						// .applyVeoozRules(vec_price_prod));
						// String area = pd.getLoc();
						// String city = pd.getCity();
						// Module32Communicator mdc = new
						// Module32Communicator();
						// Vector<String> vec_query = new Vector<String>();
						// vec_query.add(pd.getQuery());
						// vec_query.add(area);
						// vec_query.add(city);
						// mdc.setType(4);
						// mdc.setVeeoz_call(vec_price_prod);
						// mdc.setRequest_id(md.getRequest_id());
						// mdc.setFunction_call(vec_query);
						// Module3Send.sendto2(mdc);
						//
						// } else if (md.getType() == 5) {
						// Vector<Vector<StoreInfo>> vec_stores_list = md
						// .getStore_data();
						// Vector<Productlist> vec = md.getProduct_data();
						// vec_stores_list = pd
						// .applyStoreRules(vec_stores_list);
						// System.out.println("Computation done");
						// String finalans = "";
						// int i = 0;
						// for (Productlist p : vec) {
						// finalans += p.getBrandname() + " "
						// + p.getProductid() + " " + p.getPrice();
						// if (p.getDescription() != null) {
						// finalans += "\n" + p.getDescription()
						// + "\n" + "Stores Information \n";
						// } else {
						// finalans += "\n"
						// + "NO description \n Stores Information \n";
						//
						// }
						// for (StoreInfo stores : vec_stores_list.get(i)) {
						// finalans += "\n" + stores.getStoreName()
						// + " " + stores.getLocation() + "\n";
						// }
						// i++;
						// finalans += "\n\n\n\n";
						// }
						// Module31Communicator mdc = new
						// Module31Communicator();
						// mdc.setRequest_id(md.getRequest_id());
						// mdc.setFinal_ans(finalans);
						// mdc.parse(pd.getAction());
						// Module3Send.sendto1(mdc);
						// // System.out.println(finalans);
						//
						// } else if (md.getType() == 6) {
						// Vector<LocationDetails> vec_location = md
						// .getLocation_data();
						// String finalans = "";
						// vec_location=td.applyTouristRules(vec_location);
						//
						// for (LocationDetails ld : vec_location) {
						// finalans += ld.getLocationName() + "\n\n\n";
						// finalans += "area: " + ld.getArea()
						// + "\n temperature: "
						// + ld.getTemperature() + "\n humidity: "
						// + ld.getHumidity();
						// finalans += "\n weather description: "
						// + ld.getWeatherDescription()
						// + "\n\n\n\n";
						//
						// }
						// Module31Communicator mdc = new
						// Module31Communicator();
						// mdc.setRequest_id(md.getRequest_id());
						// mdc.setFinal_ans(finalans);
						// mdc.parse(td.getAction());
						// Module3Send.sendto1(mdc);
						// // System.out.println(finalans);
						// }
						// System.out.println("Received message ");
						// System.out.println(md.getRequest_id());
						// System.out.println("Developer id is "+md.getDeveloper_id());
						// System.out.println("type of message is "+md.getType());
						// if(md.getType()==2)
						// {
						// System.out.println(md.getProduct_data().size()+"  is the size of result");
						// }
						// Module32Communicator mdc= new Module32Communicator();
						// mdc.setRequest_id(md.getRequest_id());
						// mdc.setType(1);
						// Vector<String> vec = new Vector<String>();
						// vec.add("washing machine");
						// mdc.setFunction_call(vec);
						// System.out.println("sending ----message");
						// Module3Send.sendto2(mdc);
						// System.out.println("sent--- message ");
					}
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// There are many types of Message and TextMessage
		// is just one of them. Producer sent us a TextMessage
		// so we must cast to it to get access to its .getText()
		// method.
		/*
		 * System.out.println("Enter the numbers"); Scanner in = new
		 * Scanner(System.in); int a=in.nextInt(); int b=in.nextInt();
		 * System.out.println(a*b);
		 */

		// connection.close();
	}

}
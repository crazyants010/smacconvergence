import java.io.*;
import java.util.*;

import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;


public class ProcessUserRequest implements Runnable
{

	public void processClientRequest() throws Exception
	{
		String processedFilePath = "./Processed/";
		String fileName = Thread.currentThread().getName();
		int developer_id=0;
		if(fileName.indexOf("TouristXML_")!=0)
		{
			developer_id=1;
			
		}
		if(fileName.indexOf("ProductXML_")!=0)
		{
			
			developer_id=2;
		}
		String fileContent = "" ;
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String temp = "" ;
		while((temp = br.readLine())!=null)
		{				 
			 fileContent += temp+"\n" ; 
		}
		fileContent.trim();
		
		Modul12communicator md = new Modul12communicator();
		int x =fileName.lastIndexOf("_");
		System.out.println("x is "+x);
		String num=fileName.substring(x+1, fileName.length());
		md.setXml(true);
		System.out.println("number is "+num);
		md.setRequest_id(Integer.parseInt(num));
		md.setStr(fileContent);
		System.out.println(developer_id+"is developer id ");
		md.setDeveloper_id(developer_id);
		JmsDispatcherSend.send(md);
		//Send XML
		
	/*	javax.jms.Connection jms_connection;
		ActiveMQSession jms_session;
		MessageProducer csb;
		final String ACTIVEMQ_SERVER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;//"failover://tcp://localhost:61616";
		javax.jms.ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_SERVER_URL);
		jms_connection = connectionFactory.createConnection();
		jms_connection.start();
		jms_session = (ActiveMQSession) jms_connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Destination module2_destination = jms_session.createQueue("MODULE12_QUEUE");
		csb = jms_session.createProducer(module2_destination);    
		
		TextMessage txtMessage = jms_session.createTextMessage(fileContent);			
		csb.send(txtMessage); */
		System.out.println("@SendXML_JMS: XML sent to CSB");
		
		//delete the input-file from the folder
		File f = new File(fileName);
		f.delete();
		
		//create the input-file under processed folder
		String[] str = fileName.split("/");
		
		FileWriter fwo = new FileWriter(processedFilePath+str[str.length-1], false);
		BufferedWriter bwObj = new BufferedWriter( fwo );
		bwObj.write(fileContent);
		bwObj.flush();
		bwObj.close();
		
		boolean toggle=true;
		MobileDevice mod = new MobileDevice();
		while(true)
		{
			Thread.sleep(1000*1000);
			System.out.println( "Thread Name: "+Thread.currentThread().getName());
			String Location = "";
		/*	if(toggle)
			{	Location = "Gachibowli_"+fileName; toggle = false;	}
			else
			{	Location = "InderaNagar_"+fileName; toggle = true;	}
			*/
			Location l =mod.getNewLocation();
			String rep="<Location>.*</Location>";
			String repl="<Location>"+l.getLocationName()+"</Location>";
			fileContent=fileContent.replaceAll(rep,repl);
			md.setRequest_id(Integer.parseInt(num));
			md.setXml(false);
			md.setStr(fileContent);
			JmsDispatcherSend.send(md);
		/*	txtMessage = jms_session.createTextMessage(l.getLocationName());			
			csb.send(txtMessage);  */
			
		}
		
		
		
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub

		ProcessUserRequest obc = new ProcessUserRequest();
		try {
			synchronized(this){
				obc.processClientRequest();
			}
		} 
		catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}
	
	public  void theMain() throws Exception
	{
		while(true){
			ArrayList<String> fileNameList = new ArrayList<String>();
			
			String inputFilePath = "./User_Input_Files";
			File folder = new File(inputFilePath);
			File[] listOfFiles = folder.listFiles();	
			
			int no_files = listOfFiles.length;
			for(int i =  0; i< no_files; i++)
			{
				String fileName = listOfFiles[i].toString();
				ProcessUserRequest pur1 = new ProcessUserRequest();
				Thread t1=new Thread(pur1);
				t1.setName(fileName);
				System.out.println("New Thread started:"+fileName);
				t1.start();
				t1.sleep(2000);
			}
		
		Thread.sleep(10000);
		}
		
//		fileName = listOfFiles[1].toString();
//		ProcessUserRequest pur2 = new ProcessUserRequest();
//		Thread t2=new Thread(pur2);
//		t2.setName(fileName);
//		t2.start();
//		t2.sleep(2000);
	}

}

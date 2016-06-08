import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;

public class UserRequestServer {
	
    protected UserRequestServer() throws Exception 
    {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();        
//        JSONProvider provider=new JSONProvider();
        
//        provider.setDropRootElement(true);
//        sf.setProvider(provider);        
        
        sf.setResourceClasses(Request_Handler.class);
        sf.setResourceProvider(Request_Handler.class, 
        new SingletonResourceProvider(new Request_Handler()));
        
        sf.setAddress("http://localhost:5535/");
        sf.create();
    }

    public static void main(String args[]) throws Exception 
    {
        new UserRequestServer();
        System.out.println("SERVER STARTED");
        ProcessUserRequest pr =new ProcessUserRequest();
        pr.theMain();
        Thread.sleep(5 * 60 * 10000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
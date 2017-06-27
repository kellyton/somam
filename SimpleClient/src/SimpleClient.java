import java.util.concurrent.TimeUnit;

import soma.clientproxy.SmProxy;
import soma.naming.INaming;
import soma.naming.NamingProxy;


public class SimpleClient {

	private static String nameServerHost = "localhost";
	private static int nameServerPort = 9001;
	
	//private static String server1Name = "SimpleServer001";
	private static String server1Name = "social machine 1";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Configurar
		INaming nameServer = NamingProxy.getNamingProxy(nameServerHost, nameServerPort);
		
		try {
			SmProxy sm = nameServer.lookup(server1Name);
		
			while (true){
				
				System.out.println("#######Vai fazer a chamada 1");
				System.out.println(sm.call("chamada1", null));
				
				System.out.println("#######Vai fazer a chamada 2");
				System.out.println(sm.call("chamada2", null));
				
				System.out.println("#######Vai fazer a chamada 3");
				System.out.println(sm.call("chamada3", null));
				
				TimeUnit.SECONDS.sleep(30);
			}
		} catch (Exception e) {
			System.out.println("Erro nas chamadas: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}

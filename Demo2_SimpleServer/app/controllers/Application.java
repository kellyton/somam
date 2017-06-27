package controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;

import play.*;
import play.mvc.*;
import soma.clientproxy.SmProxy;
import soma.naming.INaming;
import soma.naming.NamingProxy;
import static play.libs.Json.toJson;

public class Application extends Controller {
  
	private static String nameServerHost = "localhost";
	private static int nameServerPort = 9001;
	
	////////////////////////////////////////////////
	public static Result registrarse() {
		INaming nameServer = NamingProxy.getNamingProxy(nameServerHost, nameServerPort);
		
		String thisName = "SimpleServer001";
		int thisPort = 0;
		String thisHost = request().host();
		
		if(thisHost.contains(":")){
			thisPort = Integer.valueOf(thisHost.substring(thisHost.lastIndexOf(":")+1));
			thisHost = thisHost.substring(0, thisHost.lastIndexOf(":"));
		}
		
		SmProxy me = new SmProxy(thisHost,thisPort, thisName);
		
		try {
			JsonNode retorno = nameServer.bind(thisName, me);
			return ok(retorno);
		} catch (IOException e) {
			Logger.error("Error registering " + e.getLocalizedMessage(), e);
			return badRequest("Error registering " + e.getLocalizedMessage());
		}
		
    }
	
	////////////////////////////////////////////////
    public static Result chamada1() {
        return ok(toJson("Esta é a chamada UM"));
    }
    
    public static Result chamada2() {
        return ok(toJson("Esta é a chamada DOIS"));
    }
    
    public static Result chamada3() {
        return ok(toJson("Esta é a chamada TRÊS"));
    }
  
}

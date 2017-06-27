package controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;

import play.*;
import play.mvc.*;
import soma.clientproxy.SmProxy;
import soma.naming.INaming;
import soma.naming.NamingProxy;
import soma.relationship.IRelationshipManager;
import soma.relationship.Relationship;
import soma.relationship.RelationshipManagerImpl;
import static play.libs.Json.toJson;

public class Configuration extends Controller {
  
	private static String nameServerHost = "localhost";
	private static int nameServerPort = 9001;
	
	private static String thisName = "SimpleServer001";
	
	private static String clientName1 = "SimpleClient001";
	
	////////////////////////////////////////////////
	public static Result selfRegister() {
		INaming nameServer = NamingProxy.getNamingProxy(nameServerHost, nameServerPort);
		
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
	
	public static Result createBasicRelationships() {
		IRelationshipManager relManager = RelationshipManagerImpl.getRelationshipManager();
		
		try {
		
			//Localiza o cliente ao qual quero me relacionar
			INaming nameServer = NamingProxy.getNamingProxy(nameServerHost, nameServerPort);
			SmProxy client = nameServer.lookup(clientName1);
			
			relManager.grant(client, "chamada1", Relationship.RelType.GRANT, null);
			relManager.grant(client, "chamada2", Relationship.RelType.GRANT, null);
			relManager.grant(client, "chamada3", Relationship.RelType.GRANT, null);
			
			return ok(toJson(relManager.list()));
			
		} catch (Exception e){
			Logger.error("Erro criando os relacionamentos básicos: " + e.getLocalizedMessage(), e);
			return badRequest("Erro criando os relacionamentos básicos: " + e.getLocalizedMessage());
		}
	}
	
	public static Result revokeRelationships() {
		return TODO;
	}
  
}

package controllers;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import play.mvc.*;


public class Application extends Controller {
  
    public static Result index() {
    	ObjectNode obj = new ObjectMapper().createObjectNode();

		obj.put("Server", "Social Machines Name Server");
    	obj.put("Host", request().host());
    	obj.put("Status", "Up and running");
        return ok(obj);
        
		//Logger.error("Error Application.index()",e);
		//return badRequest("Server with problems: " + e.getLocalizedMessage());
		
    }
  
}

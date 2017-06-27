package controllers;

import static play.data.Form.form;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import static play.libs.Json.toJson;

import play.*;
import play.data.DynamicForm;
import play.mvc.*;
import soma.clientproxy.SmProxy;
import soma.naming.NamingRepository;
import soma.util.JsonUtil;

public class RegistryController extends Controller {
  
    public static Result bind() {
    	try {
	    	DynamicForm dynamicForm = form().bindFromRequest();
			
			String id = dynamicForm.get("id");
			String host = dynamicForm.get("host");
			int port = Integer.valueOf(dynamicForm.get("port"));
			
			SmProxy smProxy = new SmProxy(host, port, id);
			
			SmProxy old = NamingRepository.get(id);
			if (old != null){
				return badRequest(JsonUtil.error(id + " already exists in this server. Try using rebind."));
			} else {
				NamingRepository.put(id, smProxy);
				return ok(toJson(smProxy));
			}
    	} catch (Exception e){
    		Logger.error("Erro no bind: " + e.getMessage(), e);
    		return badRequest(JsonUtil.error("Parâmetros incorretos: Devem ser passados id, host e port"));
    	}
    }
    
    public static Result rebind() {
    	try {
	    	DynamicForm dynamicForm = form().bindFromRequest();
			
			String id = dynamicForm.get("id");
			String host = dynamicForm.get("host");
			int port = Integer.valueOf(dynamicForm.get("port"));
			
			SmProxy smProxy = new SmProxy(host, port, id);
			NamingRepository.put(id, smProxy);
			
			return ok(toJson(smProxy));
    	} catch (Exception e){
    		Logger.error("Erro no rebind: " + e.getMessage(), e);
    		return badRequest(JsonUtil.error("Parâmetros incorretos: Devem ser passados id, host e port"));
    	}
    }
    
    public static Result unbind() {
    	try {
	    	DynamicForm dynamicForm = form().bindFromRequest();
			
			String id = dynamicForm.get("id");
			
			boolean exists = NamingRepository.remove(id);
			
			if (exists){
				return ok(JsonUtil.message(id + " unbinded"));
			} else {
				return ok(JsonUtil.error(id + " is not registered"));
			}
    	} catch (Exception e){
    		Logger.error("Erro no unbind: " + e.getMessage(), e);
    		return badRequest(JsonUtil.error("Parâmetros incorretos: Deve ser passado id"));
    	}
    }    
    
    public static Result list() {
        return ok(toJson(NamingRepository.getAll()));
    }
    
    public static Result lookup() {
    	try {
	    	DynamicForm dynamicForm = form().bindFromRequest();
			String id = dynamicForm.get("id");
	        return ok(toJson(NamingRepository.get(id)));
    	} catch (Exception e){
    		Logger.error("Erro no lookup: " + e.getMessage(), e);
    		return badRequest(JsonUtil.error("Parâmetros incorretos: Deve ser passado id"));
    	}
    }
}

package soma.util;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

public class JsonUtil {
	
	public static JsonNode message(String message){
		ObjectNode msg = new ObjectMapper().createObjectNode();
		msg.put("Message", message);
		return msg;
	}
	
	public static JsonNode error(String message){
		ObjectNode msg = new ObjectMapper().createObjectNode();
		msg.put("Error", message);
		return msg;
	}

}

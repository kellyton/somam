package soma.naming;

import java.util.ArrayList;
import java.util.HashMap;

import soma.clientproxy.SmProxy;

public class NamingRepository {

	private static HashMap<String, SmProxy> records = new HashMap<String, SmProxy>();
	
	public static SmProxy put(String smId, SmProxy smProxy){
		return records.put(smId, smProxy);
	}
	
	public static SmProxy get(String smId){
		return records.get(smId);
	}
	
	public static ArrayList<SmProxy> getAll(){
		return new ArrayList<SmProxy>(records.values());
	}

	public static boolean remove(String id) {
		if (records.remove(id) != null){
			return true;
		} else {
			return false;
		}
		
	}
	
}

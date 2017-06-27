package soma.relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import soma.clientproxy.SmProxy;

public class RelationshipRepository {

	//Two hierarchical hashmaps: 1st level the client is the key. Second level, the action
	private HashMap<String, HashMap<String, Relationship>> records = 
			new HashMap<String, HashMap<String, Relationship>>();
	
	
	/**
	 * Put the relationshio overwriting previous, if exists
	 * @param rel
	 * @return
	 */
	public Relationship put(Relationship rel){
		String clientName = rel.getClient().getSmID();
		String action = rel.getAction();
		
		HashMap<String, Relationship> lowerHash = records.get(clientName);
		
		//No relationships with this client
		if (lowerHash == null){
			lowerHash = new HashMap<String, Relationship>();
			rel = lowerHash.put(action, rel);
			records.put(clientName, lowerHash);
		} else { //Overwrite relationship
			rel = lowerHash.put(action, rel);
		}
		
		return rel;
	}

	
	public Relationship get(SmProxy client, String action){
		String clientName = client.getSmID();
		
		HashMap<String, Relationship> lowerHash = records.get(clientName);
		
		if (lowerHash == null) return null;
		
		return lowerHash.get(action);
	}
	
	public ArrayList<Relationship> get(SmProxy client){
		String clientName = client.getSmID();
		
		HashMap<String, Relationship> lowerHash = records.get(clientName);
		
		if (lowerHash == null) return null;
		
		return new ArrayList<Relationship>(lowerHash.values());
	}
	
	public ArrayList<Relationship> getAll(){
		ArrayList<Relationship> allRelations = new ArrayList<Relationship>();
		
		//Itera sobre todos os elementos do hash de alto nível
		for (Map.Entry<String, HashMap<String, Relationship>> entry : records.entrySet()) {
			//Adiciona todos os elementos do hash de baixo nível no ArrayList
		    allRelations.addAll(entry.getValue().values());
		}
		
		return allRelations;
	}

	public Relationship remove(SmProxy client, String action){
		String clientName = client.getSmID();
		
		HashMap<String, Relationship> lowerHash = records.get(clientName);
		
		if (lowerHash == null) return null;
		
		return lowerHash.remove(action);
	}
	
	public ArrayList<Relationship> remove(SmProxy client){
		String clientName = client.getSmID();
		
		HashMap<String, Relationship> lowerHash = records.remove(clientName);
		
		if (lowerHash == null) return null;
		
		return new ArrayList<Relationship>(lowerHash.values());
	}
	
}

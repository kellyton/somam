package soma.relationship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonNode;

import soma.clientproxy.SmProxy;
import soma.naming.INaming;
import soma.naming.NamingProxy;

public class RelationshipManagerImpl implements IRelationshipManager {
	
	private static RelationshipManagerImpl instance = new RelationshipManagerImpl();
	private RelationshipRepository repo;
	
	public static IRelationshipManager getRelationshipManager(){
		return instance;
	}

	private RelationshipManagerImpl(){
		repo = new RelationshipRepository();
	}
	
	@Override
	public Relationship grant(SmProxy client, String action, Relationship.RelType relType, 
			Date expDate) throws IOException {
		
		Relationship rel = new Relationship(client, action, relType, expDate);
		
		return repo.put(rel);
		
	}

	@Override
	public Relationship revoke(SmProxy client, String action) throws IOException {
		return repo.remove(client, action);
	}

	@Override
	public int revokeAll(SmProxy client) throws IOException {
		List<Relationship> removed = repo.remove(client);
		if (removed == null){
			return 0;
		} else {
			return removed.size();
		}
	}

	@Override
	public Relationship lookup(SmProxy client, String action) throws IOException {
		return repo.get(client, action);
	}
	
	@Override
	public boolean allowed(SmProxy client, String action) throws IOException {
		Relationship rel = lookup(client, action);
		if (rel == null){
			return false;
		} else {
			if (rel.getRelType() == Relationship.RelType.GRANT){
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public ArrayList<?> list() throws IOException {
		return repo.getAll();
	}

	@Override
	public ArrayList<?> list(SmProxy client) throws IOException {
		return repo.get(client);
	}


}

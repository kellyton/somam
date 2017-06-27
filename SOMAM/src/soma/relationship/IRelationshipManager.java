package soma.relationship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.JsonNode;

import soma.clientproxy.SmProxy;

public interface IRelationshipManager {

	public Relationship grant(SmProxy client, String call, Relationship.RelType relType, Date expDate) throws IOException;
	
	public Relationship revoke(SmProxy client, String action) throws IOException;
	
	public int revokeAll(SmProxy client) throws IOException;
	
	public Relationship lookup(SmProxy client, String action) throws IOException;
	
	public boolean allowed(SmProxy client, String action) throws IOException;
	
	public ArrayList<?> list() throws IOException;
	
	public ArrayList<?> list(SmProxy client) throws IOException;

}

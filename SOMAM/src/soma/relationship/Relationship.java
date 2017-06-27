package soma.relationship;

import java.util.Date;

import soma.clientproxy.SmProxy;

public class Relationship {
	
	public enum RelType{
		GRANT, DENY, SUSPEND
	}
	
	private SmProxy client;
	private String action;
	private RelType relType;
	
	private Date creationDate;
	private Date revokationDate;
	
	//null if infinite
	private Date expDate;

	public Relationship(SmProxy client, String action, RelType relType, Date expDate) {
		super();
		this.client = client;
		this.action = action;
		this.relType = relType;
		this.expDate = expDate;
		this.creationDate = new Date();
	}

	public SmProxy getClient() {
		return client;
	}

	public void setClient(SmProxy client) {
		this.client = client;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getRevokationDate() {
		return revokationDate;
	}

	public void setRevokationDate(Date revokationDate) {
		this.revokationDate = revokationDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public RelType getRelType() {
		return relType;
	}

	public void setRelType(RelType relType) {
		this.relType = relType;
	}

}

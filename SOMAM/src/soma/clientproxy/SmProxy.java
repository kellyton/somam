package soma.clientproxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import soma.util.JsonUtil;

public class SmProxy {

	private final long timeout = 10000;
	
	protected String host;
	protected int port;
	protected String smID;

	public SmProxy(String host, int port, String smID) {
		super();
		this.host = host;
		this.port = port;
		this.smID = smID;
	}

	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getSmID() {
		return smID;
	}
	
	public void setSmID(String smID) {
		this.smID = smID;
	}
	
	public JsonNode call(String endpoint, ArrayList<ServiceParam> params){
		
		if(endpoint.endsWith("/")) endpoint.subSequence(0, endpoint.length() -1);
		
		try {
			
			//Building the call
			HttpClient client = new DefaultHttpClient();
			
			HttpHost target = new HttpHost(this.host, this.port, "http");
			
			// specify the get request
		    String get = "/" + endpoint;
			if (params != null && !params.isEmpty()){
				get += "?";
				for (ServiceParam sp: params){
					get += sp.getName() + "=" + sp.getValue() + "&";
				}
			}
			
			HttpGet getRequest = new HttpGet(get);
			
			HttpResponse httpResponse = client.execute(target, getRequest);
			HttpEntity entity = httpResponse.getEntity();
	        
	        String text = EntityUtils.toString(entity);
			
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode actualObj = mapper.readTree(text);
			
			return actualObj;
			
		} catch (Exception ex) {
			String error = "Error accessing endpoint: " + endpoint + " - " + ex.getMessage();
			System.out.println(error);
			return JsonUtil.error(error);
		}
		
	}
	
	private static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
}

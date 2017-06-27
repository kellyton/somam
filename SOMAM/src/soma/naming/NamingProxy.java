package soma.naming;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;

import soma.clientproxy.ServiceParam;
import soma.clientproxy.SmProxy;

public class NamingProxy extends SmProxy implements INaming{
	
	public NamingProxy(String host, int port, String smID) {
		super(host, port, smID);
	}
	
	public static INaming getNamingProxy(String host, int port){
		return new NamingProxy(host, port, "Naming");
	}

	@Override
	public JsonNode bind(String smName, SmProxy smProxy) throws IOException {
		ArrayList<ServiceParam> params = new ArrayList<ServiceParam>();
		params.add(new ServiceParam("id",smProxy.getSmID()));
		params.add(new ServiceParam("host",smProxy.getHost()));
		params.add(new ServiceParam("port",smProxy.getPort()));
		JsonNode response = call("bind", params);
		return response;
		
	}

	@Override
	public JsonNode rebind(String smName, SmProxy smProxy) throws IOException {
		ArrayList<ServiceParam> params = new ArrayList<ServiceParam>();
		params.add(new ServiceParam("id",smProxy.getSmID()));
		params.add(new ServiceParam("host",smProxy.getHost()));
		params.add(new ServiceParam("port",smProxy.getPort()));
		JsonNode response = call("rebind", params);
		return response;
		
	}

	@Override
	public JsonNode unbind(String smName) throws IOException {
		ArrayList<ServiceParam> params = new ArrayList<ServiceParam>();
		params.add(new ServiceParam("id",smName));
		JsonNode response = call("unbind", params);
		return response;
		
	}

	@Override
	public SmProxy lookup(String smName) throws IOException {
		ArrayList<ServiceParam> params = new ArrayList<ServiceParam>();
		params.add(new ServiceParam("id",smName));
		JsonNode response = call("lookup", params);
		
		String host = response.findPath("host").getTextValue();
		String id = response.findPath("smID").getTextValue();
		int port = response.findPath("port").getIntValue();
		
		SmProxy proxy = new SmProxy(host, port, id);
		return proxy;
		
	}

	@Override
	public ArrayList<SmProxy> list() throws IOException {
		ArrayList<SmProxy> proxies = new ArrayList<SmProxy>();
		String host;
		String id;
		int port;
		
		JsonNode response = call("lookup", null);
		
		for (int i = 0; i < response.size(); i++){
			JsonNode pNode = response.get(i);

			host = pNode.findPath("host").getTextValue();
			id = pNode.findPath("smID").getTextValue();
			port = pNode.findPath("port").getIntValue();
		
			proxies.add(new SmProxy(host, port, id));
		}
		return proxies;
	}

}

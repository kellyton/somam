package soma.naming;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;

import soma.clientproxy.SmProxy;

public interface INaming {
	
	public JsonNode bind(String smName, SmProxy smProxy) throws IOException;
	
	public JsonNode rebind(String smName, SmProxy smProxy) throws IOException;
	
	/**
	 * @TODO Resolver problema de segurança
	 * Problemas de segurança: qualquer um pode unBind
	 * Solução: ao bind, retornar um id/chave que será necessário para o unbind
	 * @param smName
	 * @throws IOException
	 * @throws Throwable
	 */
	public JsonNode unbind(String smName) throws IOException;
	
	public SmProxy lookup (String smName) throws IOException;
	
	public ArrayList<SmProxy> list() throws IOException;
	

}

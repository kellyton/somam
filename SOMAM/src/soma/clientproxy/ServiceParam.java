package soma.clientproxy;

public class ServiceParam {
	
	private String name;
	private String value;
	
	public ServiceParam(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public ServiceParam(String name, int value) {
		super();
		this.name = name;
		this.value = String.valueOf(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

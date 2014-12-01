package abd.pr2.model;

public class Noticia {
	private String id;
	private String titular;
	
	public Noticia() {
		this.id = null;
		this.titular = null;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Noticia(String id, String titular) {
		this.id = id;
		this.titular = titular;
	}

	public String getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}
	
	public String toString() {
		return titular;
	}
}

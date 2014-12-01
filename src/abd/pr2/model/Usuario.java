package abd.pr2.model;

public class Usuario {
	
	private String nombresUsr;
	private String nComent;
	
	public Usuario(String nombresUsr, String nComent) {
		super();
		this.nombresUsr = nombresUsr;
		this.nComent = nComent;
	}
	public Usuario() {
		super();
		this.nombresUsr = null;
		this.nComent = null;
	}
	public String getNombresUsr() {
		return nombresUsr;
	}
	public void setNombresUsr(String nombresUsr) {
		this.nombresUsr = nombresUsr;
	}
	public String getnComent() {
		return nComent;
	}
	public void setnComent(String nComent) {
		this.nComent = nComent;
	}
	
	
	
	
}

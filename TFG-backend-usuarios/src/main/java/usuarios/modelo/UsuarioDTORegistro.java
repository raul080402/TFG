package usuarios.modelo;

public class UsuarioDTORegistro {
	
	private String correo;
	private String contrasena;
	private double expertise;
	
	public UsuarioDTORegistro(String correo, String contrasena, double expertise) {
		this.correo = correo;
		this.contrasena = contrasena;
	}

	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public double getExpertise() {
		return expertise;
	}

	public void setExpertise(double expertise) {
		this.expertise = expertise;
	}
	
}

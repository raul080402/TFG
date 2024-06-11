package usuarios.modelo;

public class UsuarioDTO {
	
	private String correo;
	private String contrasena;
	private double expertise;
	
	public UsuarioDTO(String correo, String contrasena, double expertise) {
		this.correo = correo;
		this.contrasena = contrasena;
		this.expertise = expertise;
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

package usuarios.modelo;

import java.util.List;

public class ProyectoDTO {
	
	private String nombre;
	private List<String> usuarios;
	private String admin;
	
	
	public ProyectoDTO(String nombre, List<String> usuarios, String admin) {
		this.nombre = nombre;
		this.usuarios = usuarios;
		this.admin = admin;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<String> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<String> usuarios) {
		this.usuarios = usuarios;
	}
	
}

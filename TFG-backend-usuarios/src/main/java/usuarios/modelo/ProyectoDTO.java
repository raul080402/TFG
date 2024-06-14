package usuarios.modelo;

import java.util.List;

public class ProyectoDTO {
	
	private String nombre;
	private List<String> usuarios;

	
	public ProyectoDTO(String nombre, List<String> usuarios) {
		this.nombre = nombre;
		this.usuarios = usuarios;
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

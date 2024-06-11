package usuarios.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proyectos")
public class Proyecto {
	
	@Id
	private String nombre;
	private List<String> usuarios;
	private String admin;
	
	
	public Proyecto(String nombre, List<String> usuarios, String admin) {
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


	public void add(String idUsuario) {
		if(!this.usuarios.contains(idUsuario)) {
			this.usuarios.add(idUsuario);
			System.out.println("ENTRA");
		}
	}

	@Override
	public String toString() {
		return "Proyecto [nombre=" + nombre + ", usuarios=" + usuarios.size() + "]";
	}
	
	
	
	
}

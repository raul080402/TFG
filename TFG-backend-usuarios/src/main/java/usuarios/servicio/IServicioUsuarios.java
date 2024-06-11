package usuarios.servicio;

import java.util.List;

import repositorio.EntidadNoEncontrada;

public interface IServicioUsuarios {
	
	String crearUsuario(String correo, String contrasena, double expertise);
	
	boolean verificarUsuario(String correo, String contrasena) throws EntidadNoEncontrada;
	
	String crearProyecto(String nombre, List<String> correos);
	
	List<String> getUsuariosProyecto(String idProyecto) throws EntidadNoEncontrada;
	
	void añadirUsuarioAProyecto(String idProyecto, List<String> usuarios) throws EntidadNoEncontrada;
	
	List<String> getAllProyectByUsuario(String idUsuario);

	List<String> getUsuarios();
	
}

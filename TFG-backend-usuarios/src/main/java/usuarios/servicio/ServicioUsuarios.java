package usuarios.servicio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositorio.EntidadNoEncontrada;
import usuarios.modelo.Proyecto;
import usuarios.modelo.Usuario;
import usuarios.repositorio.RepositorioProyectos;
import usuarios.repositorio.RepositorioUsuarios;

@Component
@Transactional
public class ServicioUsuarios implements IServicioUsuarios {

	private RepositorioProyectos repo_proyectos;
	private RepositorioUsuarios repo_usuarios;

	@Autowired
	public ServicioUsuarios(RepositorioProyectos repo_proyectos,
								RepositorioUsuarios repo_usuarios) {
		this.repo_proyectos = repo_proyectos;
		this.repo_usuarios = repo_usuarios;
	}

	@Override
	public String crearUsuario(String correo, String contrasena) {
		
		if(correo == null || correo.isEmpty()) {
			throw new IllegalArgumentException("El correo del usuario no puede ser nulo");
		}
		
		if(contrasena == null || contrasena.isEmpty()) {
			throw new IllegalArgumentException("El nombre del usuario no puede ser nulo");
		}
		
		if(repo_usuarios.existsById(correo)) {
			throw new IllegalArgumentException("Ya existe un usuario con ese correo");
		}
		
		Usuario usuario = new Usuario(correo, contrasena);
		
		repo_usuarios.save(usuario);
		
		return usuario.getCorreo();
		
	}

	@Override
	public boolean verificarUsuario(String correo, String contrasena) throws EntidadNoEncontrada {
		
		if(correo == null || correo.isEmpty()) {
			throw new IllegalArgumentException("El correo del usuario no puede ser nulo");
		}
		
		if(contrasena == null || contrasena.isEmpty()) {
			throw new IllegalArgumentException("El nombre del usuario no puede ser nulo");
		}
		
		Optional<Usuario> usuario_op = repo_usuarios.findById(correo);
		
		if(!usuario_op.isPresent()) {
			throw new EntidadNoEncontrada("Usuario no encontrado");
		}
		
		Usuario usuario = usuario_op.get();
		
		
		if(usuario.getContrasena().equals(contrasena)) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public String crearProyecto(String nombre, List<String> correos) {
		
		if(nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre del proyecto no puede ser nulo");
		}
		
		Proyecto proyecto = new Proyecto(nombre, correos);
		
		repo_proyectos.save(proyecto);
		
		return proyecto.getNombre();
		
	}

	@Override
	public List<String> getUsuariosProyecto(String idProyecto) throws EntidadNoEncontrada {
		if(idProyecto == null || idProyecto.isEmpty()) {
			throw new IllegalArgumentException("El id del proyecto no puede ser nulo");
		}
		
		Optional<Proyecto> proyecto_op = repo_proyectos.findById(idProyecto);
		
		if(!proyecto_op.isPresent()) {
			throw new EntidadNoEncontrada("Proyecto no encontrado");
		}
		
		Proyecto proyecto = proyecto_op.get();
		
		return proyecto.getUsuarios();
		
	}

	@Override
	public void añadirUsuarioAProyecto(String idProyecto, List<String> usuarios) throws EntidadNoEncontrada {
		
		if(idProyecto == null || idProyecto.isEmpty()) {
			throw new IllegalArgumentException("El correo del usuario no puede ser nulo");
		}
		
		
		Optional<Proyecto> proyecto_op = repo_proyectos.findById(idProyecto);
		
		if(!proyecto_op.isPresent()) {
			throw new EntidadNoEncontrada("Proyecto no encontrado");
		}
		
		Proyecto proyecto = proyecto_op.get();
		
		for(String u: usuarios) {
			proyecto.add(u);
			
		}
		
		System.out.println(proyecto.toString());
		
		repo_proyectos.save(proyecto);
		
	}

	@Override
	public List<String> getAllProyectByUsuario(String correo) {
		if(correo == null || correo.isEmpty()) {
			throw new IllegalArgumentException("El id del usuario no puede ser nulo");
		}
		
		List<Proyecto> proyectos = (List<Proyecto>) repo_proyectos.findAll();
		
		List<String> resultado = proyectos.stream()
			    .filter(proyecto -> proyecto.getUsuarios().stream()
			        .anyMatch(c -> c.equals(correo)))
			    .map(Proyecto::getNombre) 
			    .collect(Collectors.toList());
		
		return resultado;
		
	}

	@Override
	public List<String> getUsuarios() {
		List<Usuario> usuarios = (List<Usuario>) repo_usuarios.findAll();
		
		List<String> idUsuarios = usuarios.stream()
								.map(Usuario::getCorreo)
								.collect(Collectors.toList());
		
		return idUsuarios;
	}
	
	
}

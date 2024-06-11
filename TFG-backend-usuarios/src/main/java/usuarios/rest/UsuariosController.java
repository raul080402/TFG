package usuarios.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import repositorio.EntidadNoEncontrada;
import usuarios.modelo.ProyectoDTO;
import usuarios.modelo.UsuarioDTO;
import usuarios.servicio.IServicioUsuarios;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	private IServicioUsuarios servicio;

	@Autowired
	public UsuariosController(IServicioUsuarios servicio) {
		this.servicio = servicio;
	}
	

	
	@PostMapping
	public ResponseEntity<String> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws EntidadNoEncontrada {

		
		servicio.crearUsuario(usuarioDTO.getCorreo(), usuarioDTO.getContrasena(), usuarioDTO.getExpertise());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO.getCorreo());
	
	}

	
	@GetMapping("/verificar/{correo}/{contrasena}")
	public ResponseEntity<String> verificar_credenciales(@PathVariable String correo, @PathVariable String contrasena) throws EntidadNoEncontrada {
		
		
		boolean verificado = servicio.verificarUsuario(correo, contrasena);
		
		if(verificado == true) {
			return ResponseEntity.ok(correo);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("No verificado");

		
	}

	@PostMapping("/proyectos")
	public ResponseEntity<String> crearProyecto(@RequestBody ProyectoDTO proyectoDTO) throws EntidadNoEncontrada {

		
		servicio.crearProyecto(proyectoDTO.getNombre(), proyectoDTO.getUsuarios());
		
		return ResponseEntity.ok(proyectoDTO.getNombre());
	
	}

	
	@PostMapping("/proyectos/{idProyecto}")
	public ResponseEntity<String> anadirUsuariosProyectos(@PathVariable String idProyecto, @RequestBody List<String> usuarios) throws EntidadNoEncontrada {
		
		servicio.añadirUsuarioAProyecto(idProyecto, usuarios);
				
		return ResponseEntity.ok(idProyecto);
	
	}
	
	@GetMapping("/proyectos/{correo}")
	public ResponseEntity<List<String>> getProyectosByusuario(@PathVariable String correo) throws EntidadNoEncontrada {
		
		List<String> proyectos = servicio.getAllProyectByUsuario(correo);
		
		return ResponseEntity.ok(proyectos);
	}
	
	@GetMapping("/proyectos/usuarios/{idProyecto}")
	public ResponseEntity<List<String>> getUsuariosByProyecto(@PathVariable String idProyecto) throws EntidadNoEncontrada {
		
		List<String> usuarios = servicio.getUsuariosProyecto(idProyecto);
		
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/todos")
	public ResponseEntity<List<String>> getUsuarios() throws EntidadNoEncontrada {
		
		
		List<String> usuarios = servicio.getUsuarios();
		
		
		return ResponseEntity.ok(usuarios);
	}
	


}

package expresiones.rest;


import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import expresiones.modelo.Expresion;
import expresiones.modelo.ExpresionDto;
import expresiones.modelo.ExpresionDtoResponse;
import expresiones.modelo.Relation;
import expresiones.modelo.RelationDto;
import expresiones.servicio.IServicioExpresiones;
import repositorio.EntidadNoEncontrada;

@RestController
@RequestMapping("/expresiones")
public class ExpresionController {

	private IServicioExpresiones servicio;

	@Autowired
	public ExpresionController(IServicioExpresiones servicio) {
		this.servicio = servicio;
	}
	
	@PostMapping("crear/{idUsuario}")
	public ResponseEntity<String> crearExpresion(@RequestBody ExpresionDto expresion, @PathVariable String idUsuario) throws EntidadNoEncontrada {
		List<Relation> r = expresion.transformToEntity();
		
		String id = servicio.crearExpresion(expresion.getClinicalTerm(), expresion.getSemanticType(), r, idUsuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@PostMapping("/{idExpresion}")
	public ResponseEntity<Void> añadirRelation(@PathVariable String idExpresion, @Valid @RequestBody RelationDto relation) throws EntidadNoEncontrada {
		
		servicio.añadirExpresion(idExpresion, relation.toEntity());
		
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{clinic_term}")
	public ResponseEntity<ExpresionDto> recuperarExpresionByTerminoClinico(@PathVariable String clinic_term) throws EntidadNoEncontrada {
		ExpresionDto expresion = ExpresionDto.toDTO(servicio.getExpresion(clinic_term));
		
		return ResponseEntity.ok(expresion);
	}
	
	
	@GetMapping("/clinicTerms")
	public ResponseEntity<List<String>> getTerminosClinicos(){
		
		List<String> clinicTerms = servicio.getTerminosClinicos();
	    
		return ResponseEntity.ok(clinicTerms);
		
	}
	
	@GetMapping("/usuarios/{idUsuario}")
	public ResponseEntity<List<ExpresionDtoResponse>> getExpresionesByUsuario(@PathVariable String idUsuario){
		
		List<Expresion> expresiones = servicio.getExpresionesByUsuario(idUsuario);
	    
		List<ExpresionDtoResponse> dtos = new LinkedList<ExpresionDtoResponse>();
		
		for(Expresion e: expresiones) {
			dtos.add(ExpresionDtoResponse.toDTO(e));
		}
		
		return ResponseEntity.ok(dtos);
		
	}
	
	
	@PutMapping("/modificar-tipoSemantico/{idExpresion}/{tipo_semantico}")
	public ResponseEntity<String> getTerminosClinicos(@PathVariable String idExpresion, @PathVariable String tipo_semantico) throws EntidadNoEncontrada{
		
		servicio.modificarTipoSemantico(idExpresion, tipo_semantico);
	    
		return ResponseEntity.ok(tipo_semantico);
		
	}
	
	@PutMapping("/anadir-relacion/{idExpresion}")
	public ResponseEntity<String> anadirRelacion(@PathVariable String idExpresion, @RequestBody RelationDto relation) throws EntidadNoEncontrada{
		
		System.out.println(idExpresion);
		
		Relation r = relation.toEntity();
		
		servicio.anadir_relacion(idExpresion, r);
		
		return ResponseEntity.ok(idExpresion);
		
	}
	
	@PutMapping("/borrar-relacion/{idExpresion}")
	public ResponseEntity<String> borrarRelacion(@PathVariable String idExpresion, @RequestBody RelationDto relation) throws EntidadNoEncontrada{
		
		Relation r = relation.toEntity();
		
		servicio.eliminar_relacion(idExpresion, r);
		
		return ResponseEntity.ok(idExpresion);
		
	}
	
	@PutMapping("/validacion/{idExpresion}")
	public ResponseEntity<String> validacionExpresion(@PathVariable String idExpresion) throws EntidadNoEncontrada{
		
		servicio.validar_expresion(idExpresion);
		
		return ResponseEntity.ok(idExpresion);
		
	}
	
	
}

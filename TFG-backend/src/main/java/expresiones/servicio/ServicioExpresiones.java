package expresiones.servicio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import expresiones.modelo.Estado;
import expresiones.modelo.Expresion;
import expresiones.modelo.Relation;
import expresiones.repositorio.RepositorioExpresiones;
import repositorio.EntidadNoEncontrada;

@Component
@Transactional
public class ServicioExpresiones implements IServicioExpresiones {

	private RepositorioExpresiones repo;

	@Autowired
	public ServicioExpresiones(RepositorioExpresiones repositorio) {
		this.repo = repositorio;
	}

	@Override
	public String crearExpresion(String clinicTerm, String semanticType, List<Relation> relaciones, String idUsuario) throws EntidadNoEncontrada {
		Expresion expresion = new Expresion(clinicTerm, semanticType, relaciones, idUsuario);
		
		this.repo.save(expresion);
		
		return expresion.getId();
		
	}

	@Override
	public Expresion getExpresion(String clinicTerm) throws EntidadNoEncontrada {
		
		if (clinicTerm == null || clinicTerm.isEmpty())
			throw new IllegalArgumentException("El término clínico no puede ser vacio ni nulo");

		List<Expresion> primeraExpresionEncontrada = (List<Expresion>) repo.findAll();
		
		Optional<Expresion> expresion = primeraExpresionEncontrada.stream().
				filter(e -> e.getClinicTerm().equals(clinicTerm))
			    .findFirst();
		
		if(!expresion.isPresent()) {
			throw new EntidadNoEncontrada(clinicTerm);
		}
		
		return expresion.get();
	}

	@Override
	public void añadirExpresion(String idExpresion, Relation relation) throws EntidadNoEncontrada {
		
		if (idExpresion.isEmpty() || idExpresion == null)
			throw new IllegalArgumentException("El id no puede ser vacio ni nulo");

		Optional<Expresion> expresion_o = this.repo.findById(idExpresion);
		
		if(!expresion_o.isPresent()) {
			throw new EntidadNoEncontrada(idExpresion);
		}
		
		Expresion expresion = expresion_o.get();

		expresion.add(relation);
		
		expresion.setEstado(Estado.REVISADA);
		
		repo.save(expresion);
		
	}

	@Override
	public List<String> getTerminosClinicos() {
		List<Expresion> expresiones = (List<Expresion>) this.repo.findAll();
		
		List<String> clinicTerms = expresiones.stream()
        	.map(Expresion::getClinicTerm)
        	.filter(clinicTerm -> clinicTerm != null && !clinicTerm.isEmpty()) 
        	.collect(Collectors.toList());
		
		Collections.reverse(clinicTerms);
		
		return clinicTerms;
	}

	@Override
	public List<Expresion> getExpresionesByUsuario(String idUsuario) {
		
		List<Expresion> expresiones = (List<Expresion>) repo.findAll();
		
		List<Expresion> resultado = expresiones.stream()
							.filter(e -> e.getIdUsuario().equals(idUsuario))
							.collect(Collectors.toList());
		
		return resultado;	
	}

	@Override
	public void modificarTipoSemantico(String idExpresion, String nuevo_tS) throws EntidadNoEncontrada {
		
		if (idExpresion.isEmpty() || idExpresion == null)
			throw new IllegalArgumentException("El id no puede ser vacio ni nulo");

		if (nuevo_tS.isEmpty() || nuevo_tS == null)
			throw new IllegalArgumentException("El nuevo tipo Semantico no puede ser vacio ni nulo");

		
		Optional<Expresion> expresion_o = this.repo.findById(idExpresion);
		
		if(!expresion_o.isPresent()) {
			throw new EntidadNoEncontrada(idExpresion);
		}
		
		Expresion e = expresion_o.get();
		
		e.setSemanticType(nuevo_tS);
		
		e.setEstado(Estado.REVISADA);
		
		repo.save(e);
		
	}

	@Override
	public void eliminar_relacion(String idExpresion, Relation r) throws EntidadNoEncontrada {
		if (idExpresion.isEmpty() || idExpresion == null)
			throw new IllegalArgumentException("El id no puede ser vacio ni nulo");

		Optional<Expresion> expresion_o = this.repo.findById(idExpresion);
		
		if(!expresion_o.isPresent()) {
			throw new EntidadNoEncontrada(idExpresion);
		}
		
		Expresion e = expresion_o.get();
		
		if(e.eliminar_relation(r) == true) {
			e.setEstado(Estado.REVISADA);
			repo.save(e);
		}else {
			throw new IllegalArgumentException("La relacion no se encuentra dentro de la expresion");
		}
		
	}

	@Override
	public void anadir_relacion(String idExpresion, Relation r) throws EntidadNoEncontrada {
		
		Optional<Expresion> expresion_o = this.repo.findById(idExpresion);
		
		if(!expresion_o.isPresent()) {
			throw new EntidadNoEncontrada(idExpresion);
		}
		
		Expresion e = expresion_o.get();
		
		if(e.anadir_relation(r)) {
			e.setEstado(Estado.REVISADA);
			repo.save(e);
		}else {
			throw new IllegalArgumentException("La relacion ya se encuentra dentro de la expresion");
		}
		
	}

	@Override
	public void validar_expresion(String idExpresion) throws EntidadNoEncontrada {
		
		Optional<Expresion> expresion_o = this.repo.findById(idExpresion);
		
		if(!expresion_o.isPresent()) {
			throw new EntidadNoEncontrada(idExpresion);
		}
		
		Expresion e = expresion_o.get();
		
		e.setEstado(Estado.VALIDADA);
		
	}
	
	
	
	
}

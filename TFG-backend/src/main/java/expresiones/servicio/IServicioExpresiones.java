package expresiones.servicio;

import java.util.List;

import expresiones.modelo.Expresion;
import expresiones.modelo.Relation;
import repositorio.EntidadNoEncontrada;

public interface IServicioExpresiones {

	String crearExpresion(String clinicTerm, String semanticType, List<Relation> relaciones, String idUsuario) throws EntidadNoEncontrada;
	
	void añadirExpresion(String idExpresion, Relation relation) throws EntidadNoEncontrada;

	Expresion getExpresion(String clinicTerm) throws EntidadNoEncontrada;
	
	List<String> getTerminosClinicos();
	
	List<Expresion> getExpresionesByUsuario(String idUsuario);
	
	void modificarTipoSemantico(String idExpresion, String nuevo_tS) throws EntidadNoEncontrada;

	void eliminar_relacion(String idExpresion, Relation r) throws EntidadNoEncontrada;

	void anadir_relacion(String idExpresion, Relation r) throws EntidadNoEncontrada;

	void validar_expresion(String idExpresion) throws EntidadNoEncontrada;
}

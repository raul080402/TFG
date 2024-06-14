package expresiones.modelo;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "expresiones")
public class Expresion {
	
	@Id
	private String id;
	private String clinicTerm;
	private String semanticType;
	private List<Relation> relations;
	private String idUsuario;
	private Estado estado;
	
	public Expresion(String clinicTerm,String semanticType, List<Relation> relations,String idUsuario) {
		this.clinicTerm = clinicTerm;
		this.semanticType = semanticType;
		this.relations = relations;
		this.idUsuario = idUsuario;
		this.estado = Estado.EN_REVISION;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClinicTerm() {
		return clinicTerm;
	}

	public void setClinicTerm(String clinicTerm) {
		this.clinicTerm = clinicTerm;
	}

	public String getSemanticType() {
		return semanticType;
	}

	public void setSemanticType(String semanticType) {
		this.semanticType = semanticType;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public void add(Relation relation) {
		
		this.relations.add(relation);
		
	}
	
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean eliminar_relation(Relation r) {
		Iterator<Relation> iterator = this.relations.iterator();
	    
	    while (iterator.hasNext()) {
	        Relation relation = iterator.next();	       
	        if (relation.getConcept_name().equals(r.getConcept_name()) && 
	            relation.getName().equals(r.getName())) {
	            iterator.remove();
	            return true;
	        }
	    }
	    
	    return false;
	}

	public boolean anadir_relation(Relation r) {
		Iterator<Relation> iterator = this.relations.iterator();
	    
	    while (iterator.hasNext()) {
	        Relation relation = iterator.next();	       
	        if (relation.getConcept_name().equals(r.getConcept_name()) && 
	            relation.getName().equals(r.getName())) {
	        	System.out.println("ENTRA");
	            return false;
	        }
	    }
	    System.out.println("NO ENTRA");
	    this.add(r);
	    return true;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}

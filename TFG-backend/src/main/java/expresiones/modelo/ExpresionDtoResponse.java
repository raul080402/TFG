package expresiones.modelo;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ExpresionDtoResponse {
	
	
	private String id;
	@JsonProperty("clinical_term")
    private String clinicalTerm;

	@JsonProperty("relations")
    private List<RelationDto> relations;

    @JsonProperty("semantic_type")
    private String semanticType;
    
    @JsonProperty("id_usuario")
    private String idUsuario;
    
    @JsonProperty("estado")
    private Estado estado;
	
	public ExpresionDtoResponse(String id, String clinicalTerm, List<RelationDto> relations, String semanticType,String idUsuario,
			Estado estado) {
		this.id = id;
		this.clinicalTerm = clinicalTerm;
		this.relations = relations;
		this.semanticType = semanticType;
		this.idUsuario = idUsuario;
		this.estado = estado;
	}
	
	

	public Estado getEstado() {
		return estado;
	}



	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getClinicalTerm() {
		return clinicalTerm;
	}



	public void setClinicalTerm(String clinicalTerm) {
		this.clinicalTerm = clinicalTerm;
	}



	public List<RelationDto> getRelations() {
		return relations;
	}



	public void setRelations(List<RelationDto> relations) {
		this.relations = relations;
	}



	public String getSemanticType() {
		return semanticType;
	}



	public void setSemanticType(String semanticType) {
		this.semanticType = semanticType;
	}



	public List<Relation> transformToEntity() {
		
		List<Relation> relaciones = new LinkedList<Relation>();
		
		List<RelationDto> relaciones_dto = this.getRelations();
		
		for(RelationDto relacion: relaciones_dto) {
			Relation r = relacion.toEntity();
			relaciones.add(r);
		}
		
		return relaciones;
	}

	public static ExpresionDtoResponse toDTO(Expresion expresion) {
		ExpresionDtoResponse dto = new ExpresionDtoResponse(expresion.getId(),expresion.getClinicTerm(),RelationDto.toDTO(expresion.getRelations()), expresion.getSemanticType(), expresion.getIdUsuario(), expresion.getEstado());
		
		return dto;
	}



	@Override
	public String toString() {
		return "ExpresionDto [clinicalTerm=" + clinicalTerm + ", relations=" + relations.toString() + ", semanticType="
				+ semanticType + "]";
	}



	public String getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}

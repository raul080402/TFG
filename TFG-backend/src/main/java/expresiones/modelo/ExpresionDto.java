package expresiones.modelo;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ExpresionDto {
	
	@JsonProperty("clinical_term")
    private String clinicalTerm;

	@JsonProperty("relations")
    private List<RelationDto> relations;

    @JsonProperty("semantic_type")
    private String semanticType;
    
	
	public ExpresionDto(String clinicalTerm, List<RelationDto> relations, String semanticType) {
		this.clinicalTerm = clinicalTerm;
		this.relations = relations;
		this.semanticType = semanticType;
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
			System.out.println(relacion.toString());
		}
		
		return relaciones;
	}

	public static ExpresionDto toDTO(Expresion expresion) {
		ExpresionDto dto = new ExpresionDto(expresion.getClinicTerm(),RelationDto.toDTO(expresion.getRelations()), expresion.getSemanticType());
		
		return dto;
	}



	@Override
	public String toString() {
		return "ExpresionDto [clinicalTerm=" + clinicalTerm + ", relations=" + relations.toString() + ", semanticType="
				+ semanticType + "]";
	}
	
	
}

package expresiones.modelo;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RelationDto {

	@JsonProperty("relation_id")
	private Long relationId;
	@JsonProperty("relation_name")
	private String relationName;
	@JsonProperty("target_concept_id")
	private Long targetConceptId;
	@JsonProperty("target_concept_name")
	private String targetConceptName;
	
	public RelationDto(Long relationId, String relationName, Long targetConceptId, String targetConceptName) {
		this.relationId = relationId;
		this.relationName = relationName;
		this.targetConceptId = targetConceptId;
		this.targetConceptName = targetConceptName;
	}
	
	

	public Long getRelationId() {
		return relationId;
	}



	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}



	public String getRelationName() {
		return relationName;
	}



	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}



	public Long getTargetConceptId() {
		return targetConceptId;
	}



	public void setTargetConceptId(Long targetConceptId) {
		this.targetConceptId = targetConceptId;
	}



	public String getTargetConceptName() {
		return targetConceptName;
	}



	public void setTargetConceptName(String targetConceptName) {
		this.targetConceptName = targetConceptName;
	}



	public static List<RelationDto> toDTO(List<Relation> relations) {
		List<RelationDto> relaciones = new LinkedList<RelationDto>();
		
		for(Relation r: relations) {
			RelationDto nueva_r = new RelationDto(r.getId(), r.getName(), r.getConcept_id(), r.getConcept_name());
			
			relaciones.add(nueva_r);
		}
		
		return relaciones;
	}

	public Relation toEntity() {
		return new Relation(relationId, relationName, targetConceptId, targetConceptName);
	}



	@Override
	public String toString() {
		return "RelationDto [relationId=" + relationId + ", relationName=" + relationName + ", targetConceptId="
				+ targetConceptId + ", targetConceptName=" + targetConceptName + "]";
	}
	
	
}

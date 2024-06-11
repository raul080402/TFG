package expresiones.modelo;

public class Relation {
	
	private Long id;
	private String name;
	private Long concept_id;
	private String concept_name;
	
	
	public Relation(Long id, String name, Long concept_id, String concept_name) {
		this.id = id;
		this.name = name;
		this.concept_id = concept_id;
		this.concept_name = concept_name;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getConcept_id() {
		return concept_id;
	}


	public void setConcept_id(Long concept_id) {
		this.concept_id = concept_id;
	}


	public String getConcept_name() {
		return concept_name;
	}


	public void setConcept_name(String concept_name) {
		this.concept_name = concept_name;
	}


	@Override
	public String toString() {
		return "Relation [id=" + id + ", name=" + name + ", concept_id=" + concept_id + ", concept_name=" + concept_name
				+ "]";
	}
	
	
	
}

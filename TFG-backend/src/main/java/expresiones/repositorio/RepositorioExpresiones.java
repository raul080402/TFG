package expresiones.repositorio;



import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import expresiones.modelo.Expresion;


@NoRepositoryBean
public interface RepositorioExpresiones extends CrudRepository<Expresion, String> {
	
}

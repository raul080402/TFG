package usuarios.repositorio;



import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import usuarios.modelo.Proyecto;

@NoRepositoryBean
public interface RepositorioProyectos extends CrudRepository<Proyecto, String> {
	
	
	
}

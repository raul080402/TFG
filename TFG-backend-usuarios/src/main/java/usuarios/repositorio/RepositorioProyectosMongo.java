package usuarios.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import usuarios.modelo.Proyecto;


public interface RepositorioProyectosMongo extends RepositorioProyectos, MongoRepository<Proyecto, String> {

    
}

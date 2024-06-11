package expresiones.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import expresiones.modelo.Expresion;


public interface RepositorioExpresionesMongo extends RepositorioExpresiones, MongoRepository<Expresion, String> {

    
}

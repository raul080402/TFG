package usuarios.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import usuarios.modelo.Usuario;


public interface RepositorioUsuariosMongo extends RepositorioUsuarios, MongoRepository<Usuario, String> {

    
}

package com.PPOOII.Laboratorio.Repository;

import com.PPOOII.Laboratorio.Entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
    @Query("SELECT usr FROM Usuario usr WHERE usr.login = :login AND usr.persona.id = :id_persona")
    Usuario getUsuarioANDPersona(@Param("login") String login, @Param("id_persona") Long persona);

    @Query("SELECT usr FROM Usuario usr WHERE usr.login = :login")
    Usuario findByUsername(@Param("login") String login);

    @Query("SELECT usr FROM Usuario usr WHERE usr.login = :login AND usr.apikey = :apikey")
    Usuario findByUsernameANDAPIKey(@Param("login") String username, @Param("apikey") String APIKey);
    

    //Ejemplo de coordenadas:
    /*@Query("SELECT coord FROM Coordenadas coord WHERE coord.persona.id = :id_persona")
            public abstract Coordenadas getCoordenadaXPersona(@Param("id_persona") Long persona);*/

}

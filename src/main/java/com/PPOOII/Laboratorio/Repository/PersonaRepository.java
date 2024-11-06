
package com.PPOOII.Laboratorio.Repository;

import com.PPOOII.Laboratorio.Entities.Persona;
import com.PPOOII.Laboratorio.Entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author juand
 */
public interface PersonaRepository extends CrudRepository<Persona, Long>{
    
    Optional<Persona> findByIdentificacion(String identificacion);
    
    Optional<Persona> findByEdad(String edad);
    
    Optional<Persona> findByPnombre(String pnombre);
    
    Optional<Persona> findByPapellido(String papellido);
    
    Optional<Persona> findByUbicacion(String ubicacion);

}

package com.PPOOII.Laboratorio.Repository;

import com.PPOOII.Laboratorio.Entities.Coordenadas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CoordenadasRepository extends CrudRepository<Coordenadas, Long> {

    @Query("SELECT coord FROM Coordenadas coord WHERE coord.persona.id = :id_persona")
    public abstract Coordenadas getCoordenadaXPersona(@Param("id_persona") Long persona);

}

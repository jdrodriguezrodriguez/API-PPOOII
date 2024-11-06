
package com.PPOOII.Laboratorio.Servicio.Interfaces;

import com.PPOOII.Laboratorio.Entities.Persona;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author juand
 */
public interface PersonaServicio {
    
    public List<Persona> listaPersonas();
    
    public void salvar(Persona persona);
    
    public void borrar(Persona persona);
    
    public Persona localizarPersona(Persona persona);

    //BUSQUEDA
    public Optional<Persona> buscarPorIdentificacion(String identificacion);
    
    public Optional<Persona> buscarPorEdad(String edad);
    
    public Optional<Persona> buscarPorPnombre(String pnombre);
    
    public Optional<Persona> buscarPorPapellido(String papellido);
    
    public Optional<Persona> buscarUbicacion(String ubicacion);
    
}

package com.PPOOII.Laboratorio.Servicio;

import com.PPOOII.Laboratorio.Servicio.Interfaces.PersonaServicio;
import com.PPOOII.Laboratorio.Entities.Persona;
import com.PPOOII.Laboratorio.Entities.PersonaFuncion;
import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Repository.PersonaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServicioImp implements PersonaServicio {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listaPersonas() {
        return (List<Persona>) personaRepository.findAll();
    }

    @Transactional
    @Override
    public void salvar(Persona persona) {
        
        //CALCULAR:
        persona.setEdad(PersonaFuncion.calcularEdad(persona.getFechanacimiento()));
        persona.setEdadclinica(PersonaFuncion.calcularEdadclinica(persona.getFechanacimiento()));

        if(persona.getId() != null){
            Optional<Persona> personaExistente = personaRepository.findById(persona.getId());
            
            if (personaExistente.isPresent()) {
            Usuario usuarioExistente = personaExistente.get().getUsuario();
            if (usuarioExistente != null) {
                
                //ACTUALIZA
                usuarioExistente.setLogin(generarLogin(persona));
                //usuarioExistente.setPassword(generarPassword());
                persona.setUsuario(usuarioExistente);
            }
        }
        }else{
            
            //CREA USUARIO
            Usuario usuario = new Usuario();
            personaRepository.save(persona);
            
            //GENERAR LOGIN Y PASSWORD
            usuario.setLogin(generarLogin(persona));
            usuario.setPassword(generarPassword());
            
            //GENERAR APIKEY
            usuario.setApikey(generarApikey());
            
            usuario.setPersona(persona);    // SE ASIGNAN
            persona.setUsuario(usuario);    // SE ASIGNAN
        }
        
        personaRepository.save(persona);
    }

    //GENERAR USUARIO
    private String generarLogin(Persona persona) {
        String login = persona.getPnombre() + persona.getPapellido().charAt(0) + persona.getId();
        return login.toLowerCase();
    }

    //GENERAR CONTRASEÑA
    private String generarPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    
    //GENERAR APIKEY
    private String generarApikey(){
        return UUID.randomUUID().toString();
    }

    @Transactional
    @Override
    public void borrar(Persona persona) {
        personaRepository.delete(persona);
    }

    @Transactional(readOnly = true)
    @Override
    public Persona localizarPersona(Persona persona) {
        return personaRepository.findById(persona.getId()).orElse(null);
    }

    
    //SERVICIOS BUSQUEDA
    @Override
    public Optional<Persona> buscarPorIdentificacion(String identificacion) {
        return personaRepository.findByIdentificacion(identificacion);
    }

    @Override
    public Optional<Persona> buscarPorEdad(String edad) {
        return personaRepository.findByEdad(edad);
    }

    @Override
    public Optional<Persona> buscarPorPnombre(String pnombre) {
        return personaRepository.findByPnombre(pnombre);
    }

    @Override
    public Optional<Persona> buscarPorPapellido(String papellido) {
        return personaRepository.findByPapellido(papellido);
    }

    @Override
    public Optional<Persona> buscarUbicacion(String ubicacion) {
        return personaRepository.findByUbicacion(ubicacion);
    }
}

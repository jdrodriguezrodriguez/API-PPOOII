package com.PPOOII.Laboratorio.Servicio;

// PersonaServicioImp.java
import com.PPOOII.Laboratorio.Servicio.Interfaces.UsuarioServicio;
import com.PPOOII.Laboratorio.Component.ScheduledTasks;
import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Repository.UsuarioRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImp implements UsuarioServicio {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean salvar(Usuario usuario) {
        try {
            if (usuario == null) {
                logger.info("ERROR AGREGAR_USUARIO: EL USUARIO ES NULO!");
                return false;
            } else {
                usuarioRepository.save(usuario);
                return true;
            }
        } catch (Exception e) {
            logger.info("ERROR AGREGAR_USUARIO: EL USUARIO NO SE HA GUARDADO!");
            return false;
        }
    }

    @Override
    public boolean borrar(Usuario id_persona) {
        try {
            if ((id_persona.getIdpersona() == 0) || (id_persona.getLogin().isEmpty()) || id_persona.getLogin() == null) {
                logger.error("ERROR ELIMINAR_PERSONA: EL ID DEL USUARIO ES 0 O NULL!");
                return false;
            } else {
                Usuario usuario = usuarioRepository.getUsuarioANDPersona(id_persona.getLogin(), id_persona.getIdpersona());
                usuarioRepository.delete(usuario);
                return true;
            }
        } catch (Exception e) {
            logger.error("ERROR ELIMINAR_PERSONA: LA PERSONA NO SE HA ELIMINADO!");
            return false;
        }
    }

    @Override
    public Usuario localizarPersona(Usuario usuario) {
        return usuarioRepository.findById(usuario.getIdpersona()).orElse(null);
    }

    @Override
    public List<Usuario> listaUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Usuario findByUsernameANDAPIKey(String username, String APIKey) {
        Usuario user = usuarioRepository.findByUsername(username);

        if (user != null && user.getApikey().equals(APIKey)) {
            return user;
        } else {
            System.out.println("Usuario o APIKey no coinciden.");
            return null;
        }
    }

}


package com.PPOOII.Laboratorio.Config;

import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Servicio.UsuarioServicioImp;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author juand
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioServicioImp usuarioServiceImp;

    public UserDetails loadUserByUsernameAndApiKey(String username, String apiKey) throws UsernameNotFoundException {
        Usuario user = usuarioServiceImp.findByUsernameANDAPIKey(username, apiKey);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con username: " + username + " y APIKey proporcionado.");
        }

        return new User(user.getLogin(), user.getPassword(), new ArrayList<>()); // roles pueden añadirse aquí
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

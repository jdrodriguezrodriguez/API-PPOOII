
package com.PPOOII.Laboratorio.Servicio.Interfaces;

import com.PPOOII.Laboratorio.Entities.Persona;
import com.PPOOII.Laboratorio.Entities.Usuario;
import java.util.List;


public interface UsuarioServicio {
    
    public List<Usuario> listaUsuarios();
    
    public boolean salvar(Usuario usuario);
    
    public boolean borrar(Usuario usuario);
    
    public Usuario localizarPersona(Usuario usuario);
}

package com.PPOOII.Laboratorio.Controller;

//import com.PPOOII.Laboratorio.DTO.PersonaDto;
import com.PPOOII.Laboratorio.Entities.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.PPOOII.Laboratorio.Servicio.Interfaces.PersonaServicio;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;

/*import java.util.Optional;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;*/

//@RestController
@Controller
//@RequestMapping("/")
@Slf4j
public class ControladorRest {

    @Autowired
    private PersonaServicio personaServicio;
    
    @GetMapping("/login")
    public String login (Model model) {
        return "login";
    }
    
    @GetMapping("/inicio")
    public String comienzo(Model model) {

        List<Persona> personas = personaServicio.listaPersonas();

        log.info("Web con spring ejecutando..."); //LOGS
        model.addAttribute("personas", personas);

        return "indice";
    }

    @GetMapping("/agregar")
    public String anexar(Persona persona) {

        return "cambiar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Persona persona, Errors error) {
        if (error.hasErrors()) {
            return "cambiar";
        }
        personaServicio.salvar(persona);
        return "redirect:/inicio";
    }

    @GetMapping("/cambiar/{id}")
    public String cambiar(Persona persona, Model model) {
        persona = personaServicio.localizarPersona(persona);
        model.addAttribute("persona", persona);
        return "cambiar";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(Persona persona) {
        personaServicio.borrar(persona);
        return "redirect:/inicio";
    }
}

package com.PPOOII.Laboratorio.Controller;

import com.PPOOII.Laboratorio.Entities.Persona;
import com.PPOOII.Laboratorio.Servicio.Interfaces.PersonaServicio;
import com.PPOOII.Laboratorio.Servicio.PersonaServicioImp;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author juan
 */
@RestController
@RequestMapping("/api/personas")
public class ControladorApi {

// ==========INYECCION DEL SERVICE==========
    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private PersonaServicioImp personaService;

    // ==========MÉTODOS HTTP====================
    // MÉTODO GET
    @Operation(summary = "Consultar personas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Persona creada",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))}),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @GetMapping("/")
    public List<Persona> listaPersonas(Pageable pageable) {
        return personaService.listaPersonas();
    }

    // METODO POST 
    @Operation(summary = "Crea una nueva persona")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Persona creada",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))}),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping("/persona")
    public ResponseEntity<?> crearPersona(@RequestBody Persona persona) {
        personaService.salvar(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body("Persona creada exitosamente.");
    }

    // Método PUT 
    @Operation(summary = "Edita una persona existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Persona creada",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))}),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) {
        persona.setId(id);
        if (personaService.localizarPersona(persona) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada.");
        }
        personaService.salvar(persona);
        return ResponseEntity.ok("Persona actualizada exitosamente.");
    }

    // Método DELETE 
    @Operation(summary = "Elimine una persona")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Persona creada",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))}),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long id) {
        Persona persona = new Persona();
        persona.setId(id);
        if (personaService.localizarPersona(persona) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada.");
        }
        personaService.borrar(persona);
        return ResponseEntity.ok("Persona eliminada exitosamente.");
    }

    // ==============MÉTODOS HTTP DE BÚSQUEDA =============
    // Buscar por Identificación
    @Operation(summary = "Obtiene los detalles de una persona por su identificación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Persona encontrada",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))}),
        @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content)
    })
    @GetMapping("/buscarid/{identificacion}")
    @ResponseBody
    public ResponseEntity<Persona> buscarPorIdentificacion(@PathVariable String identificacion) {
        Optional<Persona> persona = personaService.buscarPorIdentificacion(identificacion);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar por Edad
    @Operation(summary = "Obtiene los detalles de una persona por su edad")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Persona encontrada",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))}),
        @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content)
    })
    @GetMapping("/buscaredad/{edad}")
    @ResponseBody
    public ResponseEntity<Persona> buscarPorEdad(@PathVariable String edad) {
        Optional<Persona> persona = personaService.buscarPorEdad(edad);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar por Primer Nombre
    @Operation(summary = "Obtiene los detalles de una persona por su primer nombre")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Persona encontrada",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))}),
        @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content)
    })
    @GetMapping("/buscarnombre/{pnombre}")
    @ResponseBody
    public ResponseEntity<Persona> buscarPorPnombre(@PathVariable String pnombre) {
        Optional<Persona> persona = personaService.buscarPorPnombre(pnombre);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar por Primer Apellido
    @Operation(summary = "Obtiene los detalles de una persona por su primer apellido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Persona encontrada",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))}),
        @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content)
    })
    @GetMapping("/buscarapellido/{papellido}")
    @ResponseBody
    public ResponseEntity<Persona> buscarPorPapellido(@PathVariable String papellido) {
        Optional<Persona> persona = personaService.buscarPorPapellido(papellido);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar por Ubicación
    @Operation(summary = "Obtiene los detalles de una persona por su ubicacion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Persona encontrada",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))}),
        @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content)
    })
    @GetMapping("/buscarubicacion/{ubicacion}")
    @ResponseBody
    public ResponseEntity<Persona> buscarUbicacion(@PathVariable String ubicacion) {
        Optional<Persona> persona = personaService.buscarUbicacion(ubicacion);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

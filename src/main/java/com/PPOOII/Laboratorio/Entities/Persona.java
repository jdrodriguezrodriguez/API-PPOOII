package com.PPOOII.Laboratorio.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

/**
 *
 * @author juand
 */
@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre de la persona")
    @NotEmpty
    private String pnombre;

    @NotEmpty
    private String papellido;

    private String snombre;
    private String sapellido;
    private String identificacion;
    private String correo;
    private LocalDate fechanacimiento;
    private String edadclinica;
    private String edad;
    private String ubicacion;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true) //= ON DELETE CASCADE
    @JsonIgnore
    private Usuario usuario;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private Coordenadas coordenadas;

    /*
    public Persona(String nombre, String apellido, String identificacion, String correo, String edad, String ubicacion) {
        this.pnombre = nombre;
        this.papellido = apellido;
        this.identificacion = identificacion;
        this.correo = correo;
        this.edad = edad;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters
    public String getNombre() {
        return pnombre;
    }

    public void setNombre(String nombre) {
        this.pnombre = nombre;
    }

    public String getApellido() {
        return papellido;
    }

    public void setApellido(String apellido) {
        this.papellido = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
    public String getCorreo(){
        return correo;
    }
    
    public void setCorreo(){
        this.correo = correo;
    }
    
    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    public String getUbicacion(){
        return ubicacion;
    }
    
    public void setUbicacion(){
        this.ubicacion = ubicacion;
    }*/
}

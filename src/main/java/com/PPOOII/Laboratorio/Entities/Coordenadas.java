package com.PPOOII.Laboratorio.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author juand
 */
@Data
@Entity
@Table(name = "coordenadas")
public class Coordenadas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_coordenada;
    private Double longitud;
    private Double latitud;
    private String marca_cord;

    @OneToOne
    @JoinColumn(name = "persona_cord", referencedColumnName = "id", nullable = false)
    @JsonIgnore 
    private Persona persona;

    /*
    @ManyToOne
    @JoinColumn(name = "persona_cord", nullable = false)  // Esta es la clave foránea
    private Persona persona;*/
    //GETTERS Y SETTERS
    public Coordenadas() {
    }

    public Coordenadas(String marca_cord, double logitud, double latitud) {
        this.marca_cord = marca_cord;
        this.longitud = logitud;
        this.latitud = latitud;
    }

    public Coordenadas(Long id_coordenada, String marca_cord, double logitud, double latitud) {
        this.id_coordenada = id_coordenada;
        this.marca_cord = marca_cord;
        this.longitud = logitud;
        this.latitud = latitud;
    }

    public Long getId_coordenada() {
        return id_coordenada;
    }

    public void setId_coordenada(Long id_coordenada) {
        this.id_coordenada = id_coordenada;
    }

    public String getMarca_cord() {
        return marca_cord;
    }

    public void setMarca_cord(String marca_cord) {
        this.marca_cord = marca_cord;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}


package com.PPOOII.Laboratorio.Entities;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author juand
 */
public class PersonaFuncion {
    public static String calcularEdad(LocalDate fechanacimiento) {
        LocalDate actual = LocalDate.now();
        int edad = Period.between(fechanacimiento, actual).getYears();
        return String.valueOf(edad);
    }
    
    public static String calcularEdadclinica(LocalDate fechanacimiento){
        
        LocalDate actual = LocalDate.now();
        
        Period periodo = Period.between(fechanacimiento, actual);
        
        return periodo.getYears() + " años, " + periodo.getMonths() + " meses y " + periodo.getDays() + " dias.";
    }
}


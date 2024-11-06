package com.PPOOII.Laboratorio.Component;

import com.PPOOII.Laboratorio.APIs.GoogleMaps.Geocoder;
import com.PPOOII.Laboratorio.Entities.Coordenadas;
import com.PPOOII.Laboratorio.Entities.Persona;
import com.PPOOII.Laboratorio.Repository.CoordenadasRepository;
import com.PPOOII.Laboratorio.Repository.PersonaRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private CoordenadasRepository coordenadasRepository;

    //@Scheduled(cron = "*/50 * * * * ?") 
    //@Scheduled(cron = "0 */5 * * * ?") 
    //@Scheduled(cron = "*/15 * * * * ?") 
    //@Scheduled(cron = "*/30 * * * * ?") 
    @Scheduled(cron = "0 0 */5 * * ?") 



    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        try {
            List<Persona> listPersonas = (List<Persona>) personaRepository.findAll();
            if (listPersonas != null && !listPersonas.isEmpty()) {
                Geocoder geocoder = new Geocoder();

                for (Persona persona : listPersonas) {
                    logger.info("Ubicacion detectada: {}", persona.getUbicacion());
                    String LatLng = geocoder.getLatLng(persona.getUbicacion());
                    String[] coor = LatLng.split(",");

                    logger.info("Latitud y Longitud: {} - Execution Time: {}", LatLng, dateTimeFormatter.format(LocalDateTime.now()));

                    Coordenadas coorXper = coordenadasRepository.getCoordenadaXPersona(persona.getId());

                    if (coorXper == null) {

                        // Crear nuevas coordenadas si no existen para la persona
                        Coordenadas nuevaCoordenada = new Coordenadas();
                        nuevaCoordenada.setPersona(persona); // Establece el objeto Persona aquí
                        nuevaCoordenada.setMarca_cord(persona.getPnombre());
                        nuevaCoordenada.setLatitud(Double.parseDouble(coor[0]));
                        nuevaCoordenada.setLongitud(Double.parseDouble(coor[1]));

                        coordenadasRepository.save(nuevaCoordenada);
                        ;
                    } else {
                        // Actualizar las coordenadas si ya existen
                        coorXper.setLatitud(Double.parseDouble(coor[0]));
                        coorXper.setLongitud(Double.parseDouble(coor[1]));
                        coordenadasRepository.save(coorXper);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error in scheduled task: {}", e.getMessage());
        }
    }
}

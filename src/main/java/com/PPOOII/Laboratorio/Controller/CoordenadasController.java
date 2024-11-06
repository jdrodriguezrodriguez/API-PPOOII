
package com.PPOOII.Laboratorio.Controller;

import com.PPOOII.Laboratorio.Entities.Coordenadas;
import com.PPOOII.Laboratorio.Servicio.CoordenadasServicioImp;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/personas")
public class CoordenadasController {

    @Autowired
    @Qualifier("CoordenadasService")
    private CoordenadasServicioImp coordenadasServicio;

    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping("/coordenadas")
    public ResponseEntity<List<Coordenadas>> consultarTodasCoordenadas() {
        List<Coordenadas> coordenadasList = coordenadasServicio.listaCoordenadas();
        
        if (!coordenadasList.isEmpty()) {
            return ResponseEntity.ok(coordenadasList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}

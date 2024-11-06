
package com.PPOOII.Laboratorio.Servicio;

import com.PPOOII.Laboratorio.Servicio.Interfaces.CoordenadasServicio;
import com.PPOOII.Laboratorio.Entities.Coordenadas;
import com.PPOOII.Laboratorio.Repository.CoordenadasRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("CoordenadasService")
public class CoordenadasServicioImp implements CoordenadasServicio{

    @Autowired
    private CoordenadasRepository coordenadasRepository;    
    
    @Override
    public List<Coordenadas> listaCoordenadas() {
        return (List<Coordenadas>) coordenadasRepository.findAll();
    }
    
    
}

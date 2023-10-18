package com.famisanar.req.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dao.ActividadRepository;
import com.famisanar.req.dto.DatosDto;
import com.famisanar.req.entities.Actividad;

@Service
public class ActividadHelper {
    @Autowired
    ActividadRepository actividadRepository;

    public List<DatosDto> listaActividad(){
        List<DatosDto> actividadDto = new ArrayList<>();
        List<Actividad>actividad = actividadRepository.findAll();
        for (Actividad activ : actividad) {
            DatosDto casosDto = new DatosDto();
            casosDto.setId(activ.getId());
            casosDto.setDesc(activ.getDescripcion());
            actividadDto.add(casosDto);
        }
        return actividadDto;
    }
}

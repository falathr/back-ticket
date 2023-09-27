package com.famisanar.req.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dao.TemaRepository;
import com.famisanar.req.dto.DatosDto;
import com.famisanar.req.entities.Tema;

@Service
public class TemaHelper {
    @Autowired
    TemaRepository temaRepository;

    public List<DatosDto> consultarTemas(){
        List<DatosDto> datosDtos = new ArrayList<>();
        List<Tema> temas = temaRepository.findAll();
        for (Tema tema : temas) {
            DatosDto datosDto = new DatosDto();
            datosDto.setId(tema.getId());
            datosDto.setDesc(tema.getDescripcion());
            datosDtos.add(datosDto);
        }
        return datosDtos;
    }
}

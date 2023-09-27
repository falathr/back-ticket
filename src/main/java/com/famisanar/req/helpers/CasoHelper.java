package com.famisanar.req.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dao.CasoRepository;
import com.famisanar.req.dto.DatosDto;
import com.famisanar.req.entities.Caso;

@Service
public class CasoHelper {
    
    @Autowired
    CasoRepository casoRepository;

    public List<DatosDto> listaCaso(){
        List<DatosDto> casoDto = new ArrayList<>();
        List<Caso>casos = casoRepository.findAll();
        for (Caso caso : casos) {
            DatosDto casosDto = new DatosDto();
            casosDto.setId(caso.getId());
            casosDto.setDesc(caso.getDescripcion());
            casoDto.add(casosDto);
        }
        return casoDto;
    }
}

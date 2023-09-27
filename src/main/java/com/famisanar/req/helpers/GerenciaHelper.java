package com.famisanar.req.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dao.GerenciaRepository;
import com.famisanar.req.dto.DatosDto;
import com.famisanar.req.entities.Gerencia;

@Service
public class GerenciaHelper {
    
    @Autowired
    GerenciaRepository gerenciaRepository;

    public List<DatosDto> consultarGerencia(){
        List<DatosDto> datosDtos = new ArrayList<>();
        List<Gerencia> gerencias = gerenciaRepository.findAll();
        for (Gerencia gerencia : gerencias) {
            DatosDto datosDto = new DatosDto();
            datosDto.setId(gerencia.getId());
            datosDto.setDesc(gerencia.getDescripcion());
            datosDtos.add(datosDto);
        }

        return datosDtos;
    }
}

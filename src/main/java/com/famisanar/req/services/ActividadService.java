package com.famisanar.req.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dto.DatosDto;
import com.famisanar.req.helpers.ActividadHelper;
import com.famisanar.req.response.TicketResponse;

@Service
public class ActividadService {
    @Autowired
    ActividadHelper actividadHelper;

    public TicketResponse actividadLista() {
        TicketResponse response = new TicketResponse();
        try {
            List<DatosDto> casos = actividadHelper.listaActividad();
            if (casos.size() == 0) {
                response.setCodigoRespuesta("001");
                response.setDescripcion("No hay datos");
            } else {
                response.setCodigoRespuesta("000");
                response.setDescripcion("Consulta exitosa");
                response.setDatos(casos);
            }

        } catch (Exception e) {
            response.setCodigoRespuesta("999");
            response.setDescripcion("Fallo la consulta");
        }
        return response;
    }
}

package com.famisanar.req.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.entities.Responsable;
import com.famisanar.req.helpers.ResponsableHelper;
import com.famisanar.req.response.TicketResponse;

@Service
public class ResponsableService {
    
    @Autowired
    ResponsableHelper responsableHelper;

    public TicketResponse listaResponsable(){
        TicketResponse response = new TicketResponse();
        List<Responsable> responsables = responsableHelper.listaResponsable();
        if (responsables.size()==0) {
            response.setCodigoRespuesta("001");
            response.setDescripcion("No hay datos");
        } else {
            response.setCodigoRespuesta("000");
            response.setDescripcion("Consulta exitosa");
            response.setDatos(responsables);
        }
        return response;
    }
}

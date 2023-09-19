package com.famisanar.req.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDatosFiltros {
    
    private List<TicketPorFiltroRequest> datos;

    public TicketDatosFiltros() {
    }    
}

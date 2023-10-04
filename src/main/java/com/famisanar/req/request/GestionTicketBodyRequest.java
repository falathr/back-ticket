package com.famisanar.req.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestionTicketBodyRequest {
    private String fechaDesc;
    private String responsable;
    private String ticket;
    private String desc;
    public GestionTicketBodyRequest() {
    }

    
}

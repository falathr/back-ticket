package com.famisanar.req.helpers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dao.ResponsableRepository;
import com.famisanar.req.entities.Responsable;

@Service
public class ResponsableHelper {
    @Autowired
    ResponsableRepository responsableRepository;

    public List<Responsable> listaResponsable(){
        List<Responsable> responsables = responsableRepository.findAll();
        return responsables;
    }
}

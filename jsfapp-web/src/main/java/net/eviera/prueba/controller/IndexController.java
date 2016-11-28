package net.eviera.prueba.controller;

import net.eviera.prueba.model.Document;
import net.eviera.prueba.service.DocumentService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class IndexController {

    @EJB
    private DocumentService documentService;

    public List<Document> getDocuments() {
        return documentService.getAll();
    }

}

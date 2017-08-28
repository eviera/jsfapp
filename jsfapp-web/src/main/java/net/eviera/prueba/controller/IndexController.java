package net.eviera.prueba.controller;

import net.eviera.prueba.model.Document;
import net.eviera.prueba.service.DocumentService;
import net.viera.prueba.pojo.Resultado;
import org.omnifaces.util.Faces;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "indexController")
@ViewScoped
public class IndexController {

    @EJB
    private DocumentService documentService;

    /**
     * prueba de navegacion y paso de parametros complejos (con OmniFaces.Faces)
     *
     */
    public String ordenar() {
        Faces.setFlashAttribute("resultado", new Resultado(getDocuments(), Resultado.Result.SUCCESSS));
        Faces.setFlashAttribute("resultadoComment", "Un comentario");
        return "resultado";
    }

    public List<Document> getDocuments() {
        return documentService.getAll();
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}

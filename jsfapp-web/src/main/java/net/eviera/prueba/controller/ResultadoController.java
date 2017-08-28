package net.eviera.prueba.controller;

import net.eviera.prueba.model.Document;
import net.viera.prueba.pojo.Resultado;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "resultadoController")
@ViewScoped
public class ResultadoController {

    private List<Document> documents;
    private String comments;

    @PostConstruct
    public void init() {
        Resultado resultado = Faces.getFlashAttribute("resultado");
        documents = resultado.getDocuments();
        comments = Faces.getFlashAttribute("resultadoComment");
    }


    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}

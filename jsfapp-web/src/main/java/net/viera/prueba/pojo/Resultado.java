package net.viera.prueba.pojo;

import net.eviera.prueba.model.Document;

import java.util.List;

/**
 * Clase de prueba de envio de parametros complejos
 */
public class Resultado {

    public enum Result {
        SUCCESSS,
        FAILURE
    }

    private List<Document> documents;
    private Result result;

    public Resultado(List<Document> documents, Result result) {
        this.documents = documents;
        this.result = result;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

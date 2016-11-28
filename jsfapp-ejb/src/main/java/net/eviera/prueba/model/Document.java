package net.eviera.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "Document")
public class Document {

    @Id
    @GeneratedValue
    private Long id;

    @Column (name="legal_doc_ref", length = 255, nullable = false)
    private String legalDocRef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegalDocRef() {
        return legalDocRef;
    }

    public void setLegalDocRef(String legalDocRef) {
        this.legalDocRef = legalDocRef;
    }
}

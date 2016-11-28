package net.eviera.prueba.service;


import net.eviera.prueba.model.Document;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DocumentService {


    @PersistenceContext
    private EntityManager em;

    public List<Document> getAll() {
        Query query = em.createQuery("from Document");
        return query.getResultList();
    }

}

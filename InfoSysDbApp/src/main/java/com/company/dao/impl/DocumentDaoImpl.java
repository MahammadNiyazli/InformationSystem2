package com.company.dao.impl;


import com.company.entity.Audio;
import com.company.entity.Document;
import com.company.dao.inter.DocumentDaoInter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class DocumentDaoImpl implements DocumentDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<Document> getAll() {

           String jpql = "select d from Document d";
            Query q =em.createQuery(jpql, Document.class);
            List<Document> result  = q.getResultList();
            return result;
    }

    @Override
    public boolean addDocument(Document d) {
            em.persist(d);
            return true;
          }


    public boolean removeDocument(int id) {

         Document d = em.find(Document.class, id);
           em.remove(d);
         return true;
    }

    public Document getById(int id) {
        Document d = em.find(Document.class, id);
        return d;
    }

    @Override
    public Document findByName(String name){
        Query query = em.createNativeQuery("select * from document where name = ?", Document.class);
        query.setParameter(1, name);
        List<Document> list = query.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }


}

package com.company.dao.impl;


import com.company.entity.Audio;
import com.company.entity.Journal;
import com.company.dao.inter.JournalDaoInter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class JournalDaoImpl implements JournalDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<Journal> getAll() {

           String jpql = "select j from Journal j";
            Query q =em.createQuery(jpql, Journal.class);
            List<Journal> result  = q.getResultList();
            return result;
    }

    @Override
    public boolean addJournal(Journal j) {
            em.persist(j);
            return true;
          }


    public boolean removeJournal(int id) {

         Journal d = em.find(Journal.class, id);
           em.remove(d);
         return true;
    }

    public Journal getById(int id) {
        Journal d = em.find(Journal.class, id);
        return d;
    }

    @Override
    public Journal findByName(String name){
        Query query = em.createNativeQuery("select * from journal where name = ?", Journal.class);
        query.setParameter(1, name);
        List<Journal> list = query.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }


}

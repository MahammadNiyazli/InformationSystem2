package com.company.dao.impl;


import com.company.dao.inter.StorageDaoInter;
import com.company.entity.Storage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class StorageDaoImpl implements StorageDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<Storage> getAll() {

           String jpql = "select s from Storage s";
            Query q =em.createQuery(jpql, Storage.class);
            List<Storage> result  = q.getResultList();
            return result;
    }


    public Storage getById(int id) {
        Storage s = em.find(Storage.class, id);
        return s;
    }


}

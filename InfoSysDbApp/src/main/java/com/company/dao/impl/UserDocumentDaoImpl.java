package com.company.dao.impl;


import com.company.dao.inter.UserDocumentDaoInter;
import com.company.entity.UserDocument;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDocumentDaoImpl implements UserDocumentDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<UserDocument> getAll() {

           String jpql = "select ud from UserDocument ud";
            Query q =em.createQuery(jpql, UserDocument.class);
            List<UserDocument> result  = q.getResultList();
            return result;
    }

    @Override
    public boolean addUserDocument(UserDocument ud) {
            em.persist(ud);
            return true;
          }


    public boolean removeUserDocument(int id) {

         UserDocument ud = em.find(UserDocument.class, id);
           em.remove(ud);
         return true;
    }

}

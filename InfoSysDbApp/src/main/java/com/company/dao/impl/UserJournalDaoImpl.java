package com.company.dao.impl;


import com.company.entity.UserJournal;
import com.company.dao.inter.UserJournalDaoInter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserJournalDaoImpl implements UserJournalDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<UserJournal> getAll() {

           String jpql = "select ud from UserJournal ud";
            Query q =em.createQuery(jpql, UserJournal.class);
            List<UserJournal> result  = q.getResultList();
            return result;
    }

    @Override
    public boolean addUserJournal(UserJournal ud) {
            em.persist(ud);
            return true;
          }


    public boolean removeUserJournal(int id) {

         UserJournal ud = em.find(UserJournal.class, id);
           em.remove(ud);
         return true;
    }

}

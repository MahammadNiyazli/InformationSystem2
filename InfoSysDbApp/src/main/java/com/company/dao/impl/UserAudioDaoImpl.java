package com.company.dao.impl;


import com.company.dao.inter.UserAudioDaoInter;
import com.company.entity.UserAudio;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserAudioDaoImpl implements UserAudioDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<UserAudio> getAll() {

           String jpql = "select ua from UserAudio ua";
            Query q =em.createQuery(jpql, UserAudio.class);
            List<UserAudio> result  = q.getResultList();
            return result;
    }

    @Override
    public boolean addUserAudio(UserAudio a) {
            em.persist(a);
            return true;
          }


    public boolean removeUserAudio(int id) {

         UserAudio ua = em.find(UserAudio.class, id);
           em.remove(ua);
         return true;
    }

}

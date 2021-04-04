package com.company.dao.impl;


import com.company.dao.inter.UserVideoDaoInter;
import com.company.entity.UserVideo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserVideoDaoImpl implements UserVideoDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<UserVideo> getAll() {

            String jpql = "select ud from UserVideo ud";
            Query q =em.createQuery(jpql, UserVideo.class);
            List<UserVideo> result  = q.getResultList();
            return result;
    }

    @Override
    public boolean addUserVideo(UserVideo ud) {
            em.persist(ud);
            return true;
          }


    public boolean removeUserVideo(int id) {

         UserVideo ud = em.find(UserVideo.class, id);
           em.remove(ud);
         return true;
    }

}

package com.company.dao.impl;


import com.company.dao.inter.UserImageDaoInter;
import com.company.entity.UserImage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserImageDaoImpl implements UserImageDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<UserImage> getAll() {

            String jpql = "select ui from UserImage ui";
            Query q =em.createQuery(jpql, UserImage.class);
            List<UserImage> result  = q.getResultList();
            return result;
    }

    @Override
    public boolean addUserImage(UserImage ui) {
            em.persist(ui);
            return true;
          }


    public boolean removeUserImage(int id) {

         UserImage ui = em.find(UserImage.class, id);
           em.remove(ui);
         return true;
    }

}

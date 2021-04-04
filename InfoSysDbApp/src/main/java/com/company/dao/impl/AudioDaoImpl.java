package com.company.dao.impl;


import com.company.dao.inter.AudioDaoInter;
import com.company.entity.Audio;
import com.company.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class AudioDaoImpl implements AudioDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<Audio> getAll() {

           String jpql = "select a from Audio a";
            Query q =em.createQuery(jpql, Audio.class);
            List<Audio> result  = q.getResultList();
            return result;
    }

    @Override
    public boolean addAudio(Audio a) {
            em.persist(a);
            return true;
          }


    public boolean removeAudio(int id) {

         Audio a = em.find(Audio.class, id);
           em.remove(a);
         return true;
    }

    public Audio getById(int id) {
        Audio a = em.find(Audio.class, id);
        return a;
    }

    @Override
    public Audio findByName(String name){
        Query query = em.createNativeQuery("select * from audio where name = ?", Audio.class);
        query.setParameter(1, name);
        List<Audio> list = query.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }


}

package com.company.dao.impl;


import com.company.dao.inter.VideoDaoInter;
import com.company.entity.Audio;
import com.company.entity.Video;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class VideoDaoImpl implements VideoDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<Video> getAll() {

           String jpql = "select j from Video j";
            Query q =em.createQuery(jpql, Video.class);
            List<Video> result  = q.getResultList();
            return result;
    }

    @Override
    public boolean addVideo(Video v) {
            em.persist(v);
            return true;
          }


    public boolean removeVideo(int id) {

         Video d = em.find(Video.class, id);
           em.remove(d);
         return true;
    }

    public Video getById(int id) {
        Video d = em.find(Video.class, id);
        return d;
    }

    @Override
    public Video findByName(String name){
        Query query = em.createNativeQuery("select * from video where name = ?", Video.class);
        query.setParameter(1, name);
        List<Video> list = query.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }


}

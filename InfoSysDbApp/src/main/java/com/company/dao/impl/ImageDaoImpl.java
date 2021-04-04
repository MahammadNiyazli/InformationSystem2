package com.company.dao.impl;


import com.company.dao.inter.ImageDaoInter;
import com.company.entity.Audio;
import com.company.entity.Image;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ImageDaoImpl implements ImageDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<Image> getAll() {

           String jpql = "select im from Image im";
            Query q =em.createQuery(jpql, Image.class);
            List<Image> result  = q.getResultList();
            return result;
    }

    @Override
    public boolean addImage(Image img) {
            em.persist(img);
            return true;
          }


    public boolean removeImage(int id) {

         Image d = em.find(Image.class, id);
           em.remove(d);
         return true;
    }

    public Image getById(int id) {
        Image d = em.find(Image.class, id);
        return d;
    }

    @Override
    public Image findByName(String name){
        Query query = em.createNativeQuery("select * from image where name = ?", Image.class);
        query.setParameter(1, name);
        List<Image> list = query.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }


}

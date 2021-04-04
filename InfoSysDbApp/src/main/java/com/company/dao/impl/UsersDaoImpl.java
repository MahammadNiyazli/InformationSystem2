package com.company.dao.impl;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.UsersDaoInter;
import com.company.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UsersDaoImpl implements UsersDaoInter {


    @PersistenceContext
    EntityManager em;

    public List<Users> getAll() {

           String jpql = "select u from Users u";
            Query q =em.createQuery(jpql, Users.class);
            List<Users> result  = q.getResultList();
            return result;
    }




    public boolean updateUser(Users u) {
           em.merge(u);
           return true;
    }


   public static BCrypt.Hasher crypt = BCrypt.withDefaults();
    public boolean addUser(Users u) {
         u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
           em.persist(u);
        return true;

    }



    public boolean removeUser(int id) {

         Users u = em.find(Users.class, id);
           em.remove(u);
        return true;
    }

    public Users getById(int userId) {
        Users u = em.find(Users.class, userId);
        return u;
    }

    @Override
    public Users findByEmail(String email){
        Query query = em.createNativeQuery("select * from users where email = ?", Users.class);
        query.setParameter(1, email);
        List<Users> list = query.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }


}

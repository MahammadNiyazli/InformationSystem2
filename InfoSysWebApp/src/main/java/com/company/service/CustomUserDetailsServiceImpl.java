package com.company.service;

import com.company.dao.inter.UsersDaoInter;
import com.company.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersDaoInter usersDao;

    //@Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("geldim");
        Users user = usersDao.findByEmail(username);
        System.out.println("kecdim");
        System.out.println(user.getName());
        if(user!=null){
            System.out.println("bura da girdim");
            System.out.println();
           UserBuilder builder =  org.springframework.security.core.userdetails.User.withUsername(username);
           builder.password(user.getPassword());
           builder.disabled(false);
           String[] arr = new String[]{user.getRole()};
           builder.authorities(arr);
           return builder.build();
        }else{
            throw new UsernameNotFoundException("User nor found");
        }


    }
}

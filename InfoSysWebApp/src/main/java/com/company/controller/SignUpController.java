package com.company.controller;

import com.company.dao.inter.UsersDaoInter;
import com.company.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sigunp")
public class SignUpController {

    @Autowired
    UsersDaoInter usersDao;

    @RequestMapping(method = {RequestMethod.GET})
    public String index(){
        return "signup";
    }

    @RequestMapping(method = {RequestMethod.POST})
    public String index(
            @RequestParam(value = "name" , required = false) String name,
            @RequestParam(value = "surname" , required = false) String surname,
            @RequestParam(value = "email" , required = false) String email,
            @RequestParam(value = "password" , required = false) String password
            ){

        Users user = new Users();
        user.setName(name);
        user.setEmail(email);
        user.setSurname(surname);
        user.setPassword(password);
        user.setRole("USER");
        usersDao.addUser(user);
        return "login";

    }

}

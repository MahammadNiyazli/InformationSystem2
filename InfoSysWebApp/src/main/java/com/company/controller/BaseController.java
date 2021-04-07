package com.company.controller;

import com.company.dao.inter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/base")
public class BaseController {

    @Autowired
    JournalDaoInter journalDao;

    @Autowired
    AudioDaoInter audioDao;
    @Autowired
    VideoDaoInter videoDao;
    @Autowired
    ImageDaoInter imageDao;
    @Autowired
    DocumentDaoInter documentDao;
    @Autowired
    UsersDaoInter usersDao;

    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView index(){

        ModelAndView mv = new ModelAndView("base");
        mv.addObject("journalDao",journalDao);
        mv.addObject("audioDao",audioDao);
        mv.addObject("videoDao",videoDao);
        mv.addObject("imageDao",imageDao);
        mv.addObject("documentDao",documentDao);
        mv.addObject("usersDao",usersDao);

        return mv;
    }

}

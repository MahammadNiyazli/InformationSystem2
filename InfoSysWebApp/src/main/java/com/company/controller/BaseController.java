package com.company.controller;

import com.company.dao.inter.JournalDaoInter;
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

    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView index(){

        ModelAndView mv = new ModelAndView("base");
        mv.addObject("journalDao",journalDao);

        return mv;
    }

}
